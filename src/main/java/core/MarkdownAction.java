package core;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FilenameIndex;

import java.util.ArrayList;

import gui.MarkdownToolWindow;
import gui.MethodToolWindow;
import org.jetbrains.annotations.NotNull;


public class MarkdownAction extends AnAction {

    private MarkdownToolWindow toolWindow;

    /**
     * Implement this method to provide your action handler.
     *
     * @param event Carries information on the invocation place
     */
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Project currentProject = event.getProject();

        if (currentProject != null) {
            MarkdownGlobalSummary summary = new MarkdownGlobalSummary(currentProject);
            getToolWindow(currentProject).ShowWindow(summary);
        }
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        e.getPresentation().setEnabledAndVisible(project != null);
        e.getPresentation().setIcon(AllIcons.Ide.Rating);
    }

    /**
     * Makes sure only one instance of the toolwindow is available. Is easier to test than a
     * singleton.
     *
     * @param project CurrentProject
     * @return toolWindow
     */
    MarkdownToolWindow getToolWindow(Project project) {
        if (toolWindow == null) {
            toolWindow = new MarkdownToolWindow(project);
        }
        return toolWindow;
    }
}
