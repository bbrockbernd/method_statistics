package core;

import static org.intellij.markdown.flavours.gfm.GFMTokenTypes.GFM_AUTOLINK;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiRecursiveElementVisitor;
import java.util.ArrayList;
import org.intellij.plugins.markdown.lang.MarkdownElementType;
import org.intellij.plugins.markdown.lang.MarkdownElementTypes;
import org.jetbrains.annotations.NotNull;


public class MarkdownVisitor extends PsiRecursiveElementVisitor {

    ArrayList<PsiElement> links = new ArrayList<>();

    @Override
    public void visitElement(@NotNull PsiElement element) {

        if (element instanceof PsiElement) {

            if (element.getNode().getElementType() == MarkdownElementTypes.AUTOLINK
                    || element.getNode().getElementType() == MarkdownElementTypes.LINK_DESTINATION
                    || element.getNode().getElementType() ==
                    MarkdownElementType.platformType(GFM_AUTOLINK)) {
                links.add(element);
            }
        }

        super.visitElement(element);
    }
}
