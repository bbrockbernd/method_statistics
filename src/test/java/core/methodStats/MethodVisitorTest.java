package core.methodStats;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.intellij.psi.PsiConditionalExpression;
import com.intellij.psi.PsiForStatement;
import com.intellij.psi.PsiForeachStatement;
import com.intellij.psi.PsiIfStatement;
import com.intellij.psi.PsiTryStatement;
import com.intellij.psi.PsiWhileStatement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MethodVisitorTest {

    MethodVisitor visitor;

    @BeforeEach
    public void setUp() {
        visitor = new MethodVisitor();
    }

    @Test
    public void ifStatementTest() {
        PsiIfStatement el = mock(PsiIfStatement.class);
        when(el.getCondition()).thenReturn(null);
        visitor.visitElement(el);
        visitor.visitElement(el);
        assertEquals(3, visitor.getCc());
    }

    @Test
    public void forStatementTest() {
        PsiForStatement el = mock(PsiForStatement.class);
        when(el.getCondition()).thenReturn(null);
        visitor.visitElement(el);
        visitor.visitElement(el);
        visitor.visitElement(el);
        assertEquals(4, visitor.getCc());
    }

    @Test
    public void forEachStatementTest() {
        PsiForeachStatement el = mock(PsiForeachStatement.class);
        visitor.visitElement(el);
        visitor.visitElement(el);
        visitor.visitElement(el);
        visitor.visitElement(el);
        assertEquals(5, visitor.getCc());
    }

    @Test
    public void whileStatementTest() {
        PsiWhileStatement el = mock(PsiWhileStatement.class);
        when(el.getCondition()).thenReturn(null);
        visitor.visitElement(el);
        assertEquals(2, visitor.getCc());
    }

    @Test
    public void conditionalExpressionTest() {
        PsiConditionalExpression el = mock(PsiConditionalExpression.class);
        when(el.getCondition()).thenReturn(null);
        visitor.visitElement(el);
        assertEquals(2, visitor.getCc());
    }

    @Test
    public void tryStatementTest() {
        PsiTryStatement el = mock(PsiTryStatement.class);
        visitor.visitElement(el);
        visitor.visitElement(el);
        assertEquals(3, visitor.getCc());
    }
}