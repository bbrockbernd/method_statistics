package core;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import core.markdownStats.LinkSummary;
import org.intellij.plugins.markdown.lang.MarkdownElementTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LinkSummaryTest {

    Project project;
    PsiElement element;

    @BeforeEach
    public void setUp() {
        project = mock(Project.class);
        element = mock(PsiElement.class);
        ASTNode node = mock(ASTNode.class);
        when(element.getText()).thenReturn("../data/file.txt");
        when(project.getBasePath()).thenReturn("/Users/test/");
        when(element.getNode()).thenReturn(node);
        when(node.getElementType()).thenReturn(MarkdownElementTypes.LINK_DESTINATION);
        PsiElement parent = mock(PsiElement.class);
        when(element.getParent()).thenReturn(parent);
        when(parent.getText()).thenReturn("[here is the link]");
    }

    @Test
    public void testGetFilename() {
        LinkSummary summary = new LinkSummary(project, element, "filename");
        assertEquals(summary.getFileName(), "filename");
    }

    @Test
    public void testGetLinkname() {
        LinkSummary summary = new LinkSummary(project, element, "filename");
        assertEquals(summary.getLinkName(), "../data/file.txt");
    }

    @Test
    public void testGetText() {
        LinkSummary summary = new LinkSummary(project, element, "filename");
        assertEquals(summary.getText(), "here is the link");
    }

    @Test
    public void testGetType() {
        LinkSummary summary = new LinkSummary(project, element, "filename");
        assertEquals(summary.getType(), "LINK_DESTINATION");
    }

    @Test
    public void testInThisRepo() {
        LinkSummary summary = new LinkSummary(project, element, "filename");
        assertFalse(summary.isInThisRepo());
    }
}
