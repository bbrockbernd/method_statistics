package core;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.intellij.psi.PsiFile;
import core.markdownStats.MarkdownSummary;
import core.markdownStats.MarkdownVisitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class MarkdownSummaryTest {

    MarkdownVisitor visitor;
    PsiFile psiFile;

    @BeforeEach
    public void setUp() {
        visitor = mock(MarkdownVisitor.class);
        psiFile = mock(PsiFile.class);
        when(psiFile.getName()).thenReturn("test");
        ArrayList<String> blocks = new ArrayList<>();
        blocks.add("This is a block.");
        when(visitor.getBlockQuotes()).thenReturn(blocks);
        when(visitor.getNumberOfImages()).thenReturn(1);
        when(visitor.getNumberOfParagraphs()).thenReturn(31);
        when(visitor.getNumberOfTables()).thenReturn(3);
        when(visitor.getHeaders()).thenReturn(new ArrayList<>());
        when(visitor.getLinks()).thenReturn(new ArrayList<>());
        when(visitor.getTableHeaders()).thenReturn(new ArrayList<>());

    }

    @Test
    public void testConstructor() {
        MarkdownSummary markdownSummary = new MarkdownSummary(psiFile, visitor);
        assertNotNull(markdownSummary);

    }

    @Test
    public void testGetName() {
        MarkdownSummary markdownSummary = new MarkdownSummary(psiFile, visitor);
        assertEquals("test", markdownSummary.getName());

    }

    @Test
    public void testGetBlockquotes() {
        MarkdownSummary markdownSummary = new MarkdownSummary(psiFile, visitor);
        assertEquals(1, markdownSummary.getBlockQuotes());

    }

    @Test
    public void testGetParagraphs() {
        MarkdownSummary markdownSummary = new MarkdownSummary(psiFile, visitor);
        assertEquals(31, markdownSummary.getNumberOfParagraphs());
        verify(visitor, times(1)).getBlockQuotes();
    }

    @Test
    public void testGetImages() {
        MarkdownSummary markdownSummary = new MarkdownSummary(psiFile, visitor);
        assertEquals(1, markdownSummary.getNumberOfImages());
        verify(visitor, times(1)).getNumberOfImages();
    }

    @Test
    public void testGetHeaders() {
        MarkdownSummary markdownSummary = new MarkdownSummary(psiFile, visitor);
        assertEquals(0, markdownSummary.getHeaders());
        verify(visitor, times(1)).getHeaders();
    }

    @Test
    public void testGetTableHeaders() {
        MarkdownSummary markdownSummary = new MarkdownSummary(psiFile, visitor);
        assertEquals(0, markdownSummary.getTableHeaders());
        verify(visitor, times(1)).getTableHeaders();
    }

    @Test
    public void testGetLinks() {
        MarkdownSummary markdownSummary = new MarkdownSummary(psiFile, visitor);
        assertEquals(0, markdownSummary.getLinks().size());
    }
}
