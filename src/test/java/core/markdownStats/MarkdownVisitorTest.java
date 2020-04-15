package core.markdownStats;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.intellij.psi.PsiElement;
import java.util.ArrayList;
import org.intellij.markdown.flavours.gfm.GFMTokenTypes;
import org.intellij.plugins.markdown.lang.MarkdownElementType;
import org.intellij.plugins.markdown.lang.MarkdownElementTypes;
import org.intellij.plugins.markdown.lang.psi.impl.MarkdownLinkDestinationImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

public class MarkdownVisitorTest {

    PsiElement element;
    MarkdownVisitor visitor;

    @BeforeEach
    public void setUp() {
        element = mock(PsiElement.class, Mockito.RETURNS_DEEP_STUBS);
        visitor = new MarkdownVisitor();
    }

    @Test
    public void coveredLinkTest() {
        MarkdownLinkDestinationImpl element = mock(MarkdownLinkDestinationImpl.class, Mockito.RETURNS_DEEP_STUBS);
        when(element.getNode().getElementType()).thenReturn(MarkdownElementTypes.LINK_DESTINATION);
        visitor.visitElement(element);
        assertEquals(element, visitor.getLinks().get(0));
    }

    @Test
    public void simpleLinkTest() {
        when(element.getNode().getElementType())
            .thenReturn(MarkdownElementType.platformType(GFMTokenTypes.GFM_AUTOLINK));
        when(element.getParent().getNode().getElementType())
            .thenReturn(MarkdownElementTypes.BLOCK_QUOTE);
        visitor.visitElement(element);
        assertEquals(element, visitor.getLinks().get(0));
    }

    @Test
    public void numberOfImagesTest() {
        when(element.getNode().getElementType())
            .thenReturn(MarkdownElementTypes.IMAGE);
        visitor.visitElement(element);
        visitor.visitElement(element);
        visitor.visitElement(element);
        assertEquals(3, visitor.getNumberOfImages());
    }

    @Test
    public void numberOfParagraphsTest() {
        when(element.getNode().getElementType())
            .thenReturn(MarkdownElementTypes.PARAGRAPH);
        visitor.visitElement(element);
        visitor.visitElement(element);
        assertEquals(2, visitor.getNumberOfParagraphs());
    }

    @Test
    public void headersTest() {
        PsiElement el1 = mock(PsiElement.class, Mockito.RETURNS_DEEP_STUBS);
        PsiElement el2 = mock(PsiElement.class, Mockito.RETURNS_DEEP_STUBS);
        PsiElement el3 = mock(PsiElement.class, Mockito.RETURNS_DEEP_STUBS);
        PsiElement el4 = mock(PsiElement.class, Mockito.RETURNS_DEEP_STUBS);
        PsiElement el5 = mock(PsiElement.class, Mockito.RETURNS_DEEP_STUBS);
        PsiElement el6 = mock(PsiElement.class, Mockito.RETURNS_DEEP_STUBS);

        when(el1.getNode().getElementType())
            .thenReturn(MarkdownElementTypes.ATX_1);

        when(el2.getNode().getElementType())
            .thenReturn(MarkdownElementTypes.ATX_2);

        when(el3.getNode().getElementType())
            .thenReturn(MarkdownElementTypes.ATX_3);

        when(el4.getNode().getElementType())
            .thenReturn(MarkdownElementTypes.ATX_4);

        when(el5.getNode().getElementType())
            .thenReturn(MarkdownElementTypes.ATX_5);

        when(el6.getNode().getElementType())
            .thenReturn(MarkdownElementTypes.ATX_6);

        when(el1.getText()).thenReturn("1");
        when(el2.getText()).thenReturn("2");
        when(el3.getText()).thenReturn("3");
        when(el4.getText()).thenReturn("4");
        when(el5.getText()).thenReturn("5");
        when(el6.getText()).thenReturn("6");

        visitor.visitElement(el1);
        visitor.visitElement(el2);
        visitor.visitElement(el3);
        visitor.visitElement(el4);
        visitor.visitElement(el5);
        visitor.visitElement(el6);

        ArrayList<String> expected = new ArrayList<>();
        expected.add("1");
        expected.add("2");
        expected.add("3");
        expected.add("4");
        expected.add("5");
        expected.add("6");

        assertEquals(expected, visitor.getHeaders());
    }

    @Test
    public void numberOfTablesTest() {
        when(element.getNode().getElementType())
            .thenReturn(MarkdownElementTypes.TABLE);
        visitor.visitElement(element);
        visitor.visitElement(element);
        visitor.visitElement(element);
        visitor.visitElement(element);
        assertEquals(4, visitor.getNumberOfTables());
    }

    @Test
    public void tableHeadersTest() {
        when(element.getNode().getElementType())
            .thenReturn(MarkdownElementTypes.TABLE_HEADER);
        when(element.getText()).thenReturn("testHeader");
        visitor.visitElement(element);

        ArrayList<String> expected = new ArrayList<>();
        expected.add("testHeader");
        assertEquals(expected, visitor.getTableHeaders());
    }

    @Test
    public void blockQuotesTest() {
        when(element.getNode().getElementType())
            .thenReturn(MarkdownElementTypes.BLOCK_QUOTE);
        when(element.getText()).thenReturn("blockQuote");
        visitor.visitElement(element);

        ArrayList<String> expected = new ArrayList<>();
        expected.add("blockQuote");
        assertEquals(expected, visitor.getBlockQuotes());
    }

}