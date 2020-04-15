package core.markdownStats;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FilenameIndex;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Class that creates a human readable summary of a Java class.
 * For every method in that class, it creates a method summary using the
 * MethodSummary class. Those are used for the charts.
 */
public class MarkdownGlobalSummary {

    private Project project;

    private MarkdownSummary[] markdowns;

    /**
     * Constructor for the global markdown summary.
     * It creates a summary of each markdown file present
     * in this project.
     * @param project current project.
     */
    public MarkdownGlobalSummary(Project project) {
        this.project = project;
        Collection<VirtualFile> mdFiles = FilenameIndex.getAllFilesByExt(project, "md");
        markdowns = new MarkdownSummary[mdFiles.size()];
        int i = 0;
        for (VirtualFile file : mdFiles) {
            PsiFile md = PsiManager.getInstance(project).findFile(file);
            MarkdownVisitor visitor = new MarkdownVisitor();
            markdowns[i++] = new MarkdownSummary(md, visitor);
        }
    }

    public List<MarkdownSummary> getMarkdowns() {
        return Arrays.asList(markdowns);
    }

    public Project getProject() {
        return project;
    }
}
