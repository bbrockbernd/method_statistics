package core;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FilenameIndex;

import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;

public class MarkdownAction extends AnAction {

    /**
     * Implement this method to provide your action handler.
     *
     * @param e Carries information on the invocation place
     */
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        StringBuffer dlgMsg = new StringBuffer(e.getPresentation().getText());
        String dlgTitle = e.getPresentation().getDescription();

        if (project != null) {
            ArrayList<VirtualFile> mdFiles = (ArrayList<VirtualFile>)
                    FilenameIndex.getAllFilesByExt(project, "md");
            for (VirtualFile file : mdFiles) {
                MarkdownVisitor visitor = new MarkdownVisitor();
                PsiFile md = PsiManager.getInstance(project).findFile(file);
                md.accept(visitor);
                for (PsiElement link : visitor.links) {
                    LinkSummary linkSummary =
                            new LinkSummary(project, link, file.getName());
                    dlgMsg.append("\n" + linkSummary.toString() + "\n");
                }
                dlgMsg.append(visitor.numberOfImages + " " + visitor.numberOfParagraphs + "\n");
            }
        }

        Messages.showMessageDialog(project, dlgMsg.toString(), dlgTitle,
                Messages.getInformationIcon());
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        e.getPresentation().setEnabledAndVisible(project != null);
        e.getPresentation().setIcon(AllIcons.Ide.Rating);
    }
}
