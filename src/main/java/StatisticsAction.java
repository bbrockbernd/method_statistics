import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiRecursiveElementVisitor;
import gui.MethodItem;
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
            currentJavaFile.accept(visitor);
            List<MethodItem> methodItems = new ArrayList<>();
            for (PsiMethod m : visitor.getPsiMethods()) {
                methodItems.add(new MethodItem(m.getName(), "d", "c", "l", "sig", m));
            }
            getToolWindow(currentProject).ShowWindow(currentJavaFile.getClasses()[0].getName(), methodItems);
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
        if(toolWindow == null) toolWindow = new StatisticsToolWindow(project);
        return toolWindow;
    }
}

class Visitor extends PsiRecursiveElementVisitor {

    private List<PsiMethod> psiMethods = new ArrayList<PsiMethod>();

    @Override
    public void visitElement(PsiElement element) {

        if (element instanceof PsiMethod) {
            psiMethods.add((PsiMethod) element);
        }

        super.visitElement(element);
    }

    public List<PsiMethod> getPsiMethods() {
        return psiMethods;
    }
}

