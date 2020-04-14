package core;

import static org.intellij.markdown.flavours.gfm.GFMTokenTypes.GFM_AUTOLINK;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiRecursiveElementVisitor;
import java.util.ArrayList;
import org.intellij.plugins.markdown.lang.MarkdownElementType;
import org.intellij.plugins.markdown.lang.MarkdownElementTypes;
import org.intellij.plugins.markdown.lang.psi.impl.MarkdownLinkDestinationImpl;
import org.jetbrains.annotations.NotNull;


public class MarkdownVisitor extends PsiRecursiveElementVisitor {

    ArrayList<PsiElement> links = new ArrayList<>();
    int numberOfImages = 0;
    int numberOfParagraphs = 0;
    int emails = 0;

    @Override
    public void visitElement(@NotNull PsiElement element) {

        if (element instanceof PsiElement) {

            //get links that are covered by picture or text
            if (element.getClass().equals(MarkdownLinkDestinationImpl.class)
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

        }

        super.visitElement(element);
    }
}
