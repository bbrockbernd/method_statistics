package core.markdownStats;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.intellij.plugins.markdown.lang.MarkdownElementTypes;

/**
 * Creates a summary of every link in the markdown file.
 */
public class LinkSummary {

    private PsiElement link;
    private String fileName;
    private Project project;

    private String linkName;
    private String type;
    private String text;
    private boolean inThisRepo;

    /**
     * Constructor for the markdown summary class.
     * Creates the statistics for a link in this file.
     * @param project the current project.
     * @param link the parsed link.
     * @param fileName filename where the link was found.
     */
    public LinkSummary(Project project, PsiElement link, String fileName) {
        this.project = project;
        this.link = link;
        this.fileName = fileName;
        this.linkName = link.getText();
        this.type = extractType();
        this.inThisRepo = extractAvailability();
        this.text = extractText();
    }

    private String extractText() {
        if (link.getNode().getElementType()
                == MarkdownElementTypes.LINK_DESTINATION) {
            String parent = link.getParent().getText();
            return parent.substring(parent.indexOf("[") + 1, parent.lastIndexOf("]"));
        }
        return "no cover";
    }

    private boolean extractAvailability() {
        String linkPath = linkName;
        if (linkPath.startsWith("http://") || linkPath.startsWith("https://")) {
            return false;
        }
        if (linkPath.startsWith(".") || linkPath.startsWith("/")) {
            linkPath = linkPath.substring(linkPath.indexOf("/") + 1);
        }
        Path path = Paths.get(project.getBasePath(),
                linkPath);
        return Files.exists(path);
    }

    private String extractType() {
        return link.getNode().getElementType().toString().substring(18);
    }

    public String getFileName() {
        return fileName;
    }

    public String getLinkName() {
        return linkName;
    }

    public String getType() {
        return type;
    }

    public boolean isInThisRepo() {
        return inThisRepo;
    }

    public String getText() {
        return text;
    }

    public PsiElement getLink() {
        return link;
    }

}
