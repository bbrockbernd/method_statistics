import com.intellij.psi.*;
import org.apache.commons.lang.StringUtils;

public class MethodSummary {

    private PsiMethod method;

    public MethodSummary(PsiMethod method) {
        this.method = method;
    }

    /**
     *
     * @return the name of the method.
     */
    public String getName() {return method.getName();}

    /**
     *
     * @return method's annotations.
     */
    public String getAnnotations() {
        String result = "";
        for(PsiAnnotation annotation: method.getAnnotations()) {
            result += annotation.getText() + "\n";
        }
        return result;
    }

    /**
     *
     * @return the lines of code of the actual method.
     */
    public int getLOC() {
        String body = method.getBody().getText();
        return StringUtils.countMatches(body, "\n") - 1;
    }

    /**
     *
     * @return a text summary of the method.
     */
    public String createSummary() {
        String result = "Method ";

        result += getName() + ":\n";

        if(!getAnnotations().isEmpty()) {
            result += "Annotations:\n";
            result += getAnnotations();
        }

        result += "Lines of code: " + getLOC() + "\n";

        return result;
    }

}