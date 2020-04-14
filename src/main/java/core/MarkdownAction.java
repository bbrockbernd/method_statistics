package core;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FilenameIndex;
import org.jetbrains.annotations.NotNull;


import java.util.ArrayList;

public class MarkdownAction extends AnAction {
    /**
     * Implement this method to provide your action handler.
     *
     * @param e Carries information on the invocation place
     */
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        if (project != null) {
            ArrayList<VirtualFile> mdFiles = (ArrayList<VirtualFile>)
                    FilenameIndex.getAllFilesByExt(project, "md");
            for(VirtualFile file: mdFiles) {
                MarkdownVisitor visitor = new MarkdownVisitor();
                PsiFile md = PsiManager.getInstance(project).findFile(file);
                md.accept(visitor);
                System.out.println(visitor.links);
            }
        }
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        super.update(e);
    }
}
