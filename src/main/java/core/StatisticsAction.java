package core;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;

import gui.StatisticsToolWindow;
import java.util.ArrayList;
import java.util.List;

public class StatisticsAction extends AnAction {

    private StatisticsToolWindow toolWindow;

    /**
     * Event listener for the method statistics tool.
     * @param event ActionEvent
     */
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
            ClassSummary classSummary = new ClassSummary(currentJavaFile);
            getToolWindow(currentProject)
                .ShowWindow(currentJavaFile.getClasses()[0].getName(), classSummary);
        }
    }

    @Override
    public void update(AnActionEvent e) {
        // Set the availability based on whether a project is open
        Project project = e.getProject();
        e.getPresentation().setEnabledAndVisible(project != null);
        e.getPresentation().setIcon(AllIcons.Ide.Rating);
    }

    /**
     * Makes sure only one instance of the toolwindow is available.
     * Is easier to test than a singleton.
     * @param project CurrentProject
     * @return toolWindow
     */
    StatisticsToolWindow getToolWindow(Project project) {
        if (toolWindow == null)
            toolWindow = new StatisticsToolWindow(project);
        return toolWindow;
    }
}

