package core.markdownStats;

import static org.intellij.markdown.flavours.gfm.GFMTokenTypes.GFM_AUTOLINK;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiRecursiveElementVisitor;
import java.util.ArrayList;
import org.intellij.plugins.markdown.lang.MarkdownElementType;
import org.intellij.plugins.markdown.lang.MarkdownElementTypes;
import org.intellij.plugins.markdown.lang.psi.impl.MarkdownLinkDestinationImpl;
import org.jetbrains.annotations.NotNull;

public class MarkdownVisitor extends PsiRecursiveElementVisitor {

    private ArrayList<PsiElement> links = new ArrayList<>();
    private int numberOfImages = 0;
    private int numberOfParagraphs = 0;
    private int numberOfTables = 0;
    private ArrayList<String> headers = new ArrayList<>();
    private ArrayList<String> tableHeaders = new ArrayList<>();

    private ArrayList<String> blockQuotes = new ArrayList<>();

    @Override
    public void visitElement(@NotNull PsiElement element) {

        //get links that are covered by picture or text
        if (element instanceof MarkdownLinkDestinationImpl
                && element.getNode().getElementType()
                == MarkdownElementTypes.LINK_DESTINATION) {
            links.add(element);
        }

        //get simple links that are not covered by picture or text
        if (element.getNode().getElementType()
                == MarkdownElementType.platformType(GFM_AUTOLINK)
                && element.getParent().getNode().getElementType()
                != MarkdownElementTypes.LINK_DESTINATION) {
            links.add(element);
        }

        //get number of images in this markdown file
        if (element.getNode().getElementType() == MarkdownElementTypes.IMAGE) {
            numberOfImages++;
        }

        //get number of paragraphs in this markdown file
        if (element.getNode().getElementType() == MarkdownElementTypes.PARAGRAPH) {
            numberOfParagraphs++;
        }

        //get all the headers from the markdown file
        if (element.getNode().getElementType() == MarkdownElementTypes.ATX_1
                || element.getNode().getElementType() == MarkdownElementTypes.ATX_2
                || element.getNode().getElementType() == MarkdownElementTypes.ATX_3
                || element.getNode().getElementType() == MarkdownElementTypes.ATX_4
                || element.getNode().getElementType() == MarkdownElementTypes.ATX_5
                || element.getNode().getElementType() == MarkdownElementTypes.ATX_6) {
            headers.add(element.getText());
        }

        //get the number of tables in this markdown file
        if (element.getNode().getElementType() == MarkdownElementTypes.TABLE) {
            numberOfTables++;
        }

        //get the tables' headers
        if (element.getNode().getElementType() == MarkdownElementTypes.TABLE_HEADER) {
            tableHeaders.add(element.getText());
        }

        //get the block-quotes
        if (element.getNode().getElementType() == MarkdownElementTypes.BLOCK_QUOTE) {
            blockQuotes.add(element.getText());
        }

        super.visitElement(element);
    }

    public ArrayList<PsiElement> getLinks() {
        return links;
    }

    public int getNumberOfImages() {
        return numberOfImages;
    }

    public int getNumberOfParagraphs() {
        return numberOfParagraphs;
    }

    public int getNumberOfTables() {
        return numberOfTables;
    }

    public ArrayList<String> getHeaders() {
        return headers;
    }

    public ArrayList<String> getTableHeaders() {
        return tableHeaders;
    }

    public ArrayList<String> getBlockQuotes() {
        return blockQuotes;
    }

}
