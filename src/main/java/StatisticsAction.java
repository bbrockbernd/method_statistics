import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;

import gui.MethodSummary;
import gui.StatisticsToolWindow;
import java.util.ArrayList;
import java.util.List;

public class StatisticsAction extends AnAction {

    private StatisticsToolWindow toolWindow;

    @Override
    public void actionPerformed(AnActionEvent event) {
        // Using the event, create and show a dialog
        Project currentProject = event.getProject();

        //get opened file in the editor
        VirtualFile[] files = FileEditorManager.getInstance(currentProject).getSelectedFiles();

        PsiFile currentFile = PsiManager.getInstance(currentProject).findFile(files[0]);

        //display methods name if the opened file is a Java file
        if (currentFile instanceof PsiJavaFile) {
            PsiJavaFile currentJavaFile = (PsiJavaFile) currentFile;
            Visitor visitor = new Visitor();
            currentFile.accept(visitor);
            List<MethodSummary> methodList = new ArrayList<>();
            for (PsiMethod m : visitor.getPsiMethods()) {
                methodList.add(new MethodSummary(m));
            }

            getToolWindow(currentProject).ShowWindow(currentJavaFile.getClasses()[0].getName(), methodList);
        }

    }

    @Override
    public void update(AnActionEvent e) {
        // Set the availability based on whether a project is open
        Project project = e.getProject();
        e.getPresentation().setEnabledAndVisible(project != null);
        e.getPresentation().setIcon(AllIcons.Ide.Rating);
    }

    StatisticsToolWindow getToolWindow(Project project) {
        if (toolWindow == null)
            toolWindow = new StatisticsToolWindow(project);
        return toolWindow;
    }

