package core;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import gui.MarkdownToolWindow;
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
