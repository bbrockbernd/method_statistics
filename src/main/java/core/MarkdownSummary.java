package core;

import com.intellij.psi.PsiElement;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.util.indexing.FileBasedIndex;
import org.intellij.plugins.markdown.lang.MarkdownElementType;
import org.intellij.plugins.markdown.lang.MarkdownElementTypes;

import java.nio.file.Files;
import java.nio.file.Paths;

public class MarkdownSummary {

    private String fileName;
    private String linkName;
    private PsiElement link;
    private String type;
    private boolean available;

    public MarkdownSummary(PsiElement link, String fileName) {
        this.link = link;
        this.fileName = fileName;
        this.linkName = link.getText();
        this.type = extractType();
        this.available = extractAvailability();
    }

    private boolean extractAvailability() {
        if (link.getNode().getElementType() == MarkdownElementTypes.LINK_DESTINATION)
                return Files.exists(Paths.get(linkName));
        return true;
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

    @Override
    public String toString() {
        return "MarkdownSummary{" +
                "fileName='" + fileName + '\'' +
                ", linkName='" + linkName + '\'' +
                ", type='" + type + '\'' +
                ", available=" + available +
                '}';
    }
}
