package core;

import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiElementFactory;
import com.intellij.psi.PsiMethod;
import com.intellij.testFramework.fixtures.BasePlatformTestCase;
import org.junit.Before;
import org.junit.jupiter.api.Test;


public class MethodSummaryTest extends BasePlatformTestCase {

    private Project project;
    private PsiElementFactory factory;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        project = myFixture.getProject();
        PsiElementFactory factory = JavaPsiFacade.getInstance(project).getElementFactory();

    }

    @Test
    public void testNameExtraction() {
        PsiMethod method = factory.createMethod("test",
                factory.createPrimitiveType("int"));
        MethodSummary summary = new MethodSummary(method);
        assertEquals(summary.name, "test");
    }

}
