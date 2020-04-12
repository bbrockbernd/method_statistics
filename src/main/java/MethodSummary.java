import com.intellij.psi.PsiMethod;

public class MethodSummary {

    private PsiMethod method;

    public MethodSummary(PsiMethod method) {
        this.method = method;
    }

    public String getName() {return method.getName();}

    

}
