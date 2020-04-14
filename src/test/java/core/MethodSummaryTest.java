package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.intellij.lang.jvm.JvmParameter;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiType;
import com.intellij.psi.javadoc.PsiDocComment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


public class MethodSummaryTest {

    private PsiMethod method;

    @BeforeEach
    public void setUp() {
        method = mock(PsiMethod.class, Mockito.RETURNS_DEEP_STUBS);
        when(method.getName()).thenReturn("Name");
        PsiDocComment doc = mock(PsiDocComment.class);
        when(method.getDocComment()).thenReturn(doc);
        when(method.getParameterList().getText()).thenReturn("(parameters)");
        when(method.getBody().getText()).thenReturn("\t a\n a\r");
        JvmParameter[] dummy = new JvmParameter[5];
        when(method.getParameters()).thenReturn(dummy);
    }

    @Test
    public void testName() {
        MethodSummary summary = new MethodSummary(method);
        assertEquals("Name", summary.name);
    }

    @Test
    public void testParamList() {
        MethodSummary summary = new MethodSummary(method);
        assertEquals("(parameters)", summary.parameterList);
    }

    @Test
    public void testParamListSize() {
        MethodSummary summary = new MethodSummary(method);
        assertEquals(5, summary.parameterSize);
    }

    @Test
    public void testReturnTypeNonConstructor() {
        PsiType type = mock(PsiType.class);
        when(method.getReturnType()).thenReturn(type);
        when(type.getPresentableText()).thenReturn("void");
        MethodSummary summary = new MethodSummary(method);
        assertEquals("void", summary.returnType);
    }

    @Test
    public void testReturnTypeConstructor() {
        when(method.getReturnType()).thenReturn(null);
        MethodSummary summary = new MethodSummary(method);
        assertEquals("Constructor", summary.returnType);
    }

    @Test
    public void testLoc() {
        MethodSummary summary = new MethodSummary(method);
        assertEquals(2, summary.loc);
    }

    @Test
    public void testCC() {
        MethodSummary summary = new MethodSummary(method);
        assertEquals(1, summary.cc);
    }
}
