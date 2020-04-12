import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiMethod;

public class MethodSummary {

    private PsiMethod method;

    public MethodSummary(PsiMethod method) {
        this.method = method;
    }

    /**
     *
     * @return the name of the method
     */
    public String getName() {return method.getName();}

    /**
     *
     * @return method annotations
     */
    public String getAnnotations() {
        String result = "";
        for(PsiAnnotation annotation: method.getAnnotations()) {
            result += annotation.getQualifiedName() + "\n";
        }
        return result;
    }

    /**
     *
     * @return a text summary of the method
     */
    public String createSummary() {
        String result = "";

        result += getName() + ":\n";

        if(!getAnnotations().isEmpty()) {
            result += "Annotations:\n";
            result += getAnnotations();
        }

        return result;

    }

}
