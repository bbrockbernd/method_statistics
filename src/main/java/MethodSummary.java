import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiMethod;

public class MethodSummary {

    private PsiMethod method;

    public MethodSummary(PsiMethod method) {
        this.method = method;
    }

    public String getName() {return method.getName();}

    public String getAnnotations() {
        String result = "";
        for(PsiAnnotation annotation: method.getAnnotations()) {
            result += annotation.getQualifiedName() + "\n";
        }
        return result;
    }

}
