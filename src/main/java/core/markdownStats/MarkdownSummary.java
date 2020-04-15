package core.markdownStats;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;

import java.util.Arrays;
import java.util.List;

/**
 * Method summary object for storing relevant method data for the plugin.
 */
public class MarkdownSummary {

    public PsiFile file;

    private String name;
    private int numberOfImages;
    private int numberOfParagraphs;
    private int headers;
    private int tableHeaders;
    private int blockQuotes;
    private LinkSummary[] links;

    /**
     * Constructor for the MarkdownSummary class.
     * It calculates the statistics for a markdown file.
     * @param file markdown file to analyze.
     */
    public MarkdownSummary(PsiFile file, MarkdownVisitor visitor) {
        this.file = file;
        name = file.getName();
        file.accept(visitor);
        numberOfImages = visitor.getNumberOfImages();
        numberOfParagraphs = visitor.getNumberOfParagraphs();
        headers = visitor.getHeaders().size();
        tableHeaders = visitor.getTableHeaders().size();
        blockQuotes = visitor.getBlockQuotes().size();
        links = new LinkSummary[visitor.getLinks().size()];
        int i = 0;
        for (PsiElement link : visitor.getLinks()) {
            links[i++] = new LinkSummary(file.getProject(), link, file.getName());
        }

    }

    public String getName() {
        return name;
    }

    public int getHeaders() {
        return headers;
    }

    public int getTableHeaders() {
        return tableHeaders;
    }

    public int getBlockQuotes() {
        return blockQuotes;
    }

    public int getNumberOfImages() {
        return numberOfImages;
    }

    public int getNumberOfParagraphs() {
        return numberOfParagraphs;
    }

    public List<LinkSummary> getLinks() {
        return Arrays.asList(links);
    }
}

