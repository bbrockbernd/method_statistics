import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiMethod;

import java.util.Arrays;

public class MethodSummary {

    private PsiMethod method;

    private String name;
    private String[] annotations;
    private int LOC;
    private int CC;

    public MethodSummary(PsiMethod method) {
        this.method = method;
        name = method.getName();
        extractAnnotations();
        computeLOC();
        computeCC();
    }

    //TODO
    private void computeCC() {
        CC = 0;
    }

    //TODO
    private void computeLOC() {
        LOC = 0;
    }

    /**
     *
     * extract annotations as string array
     */
    public void extractAnnotations() {
        int i = 0;
        annotations = new String[method.getAnnotations().length];
        for(PsiAnnotation annotation: method.getAnnotations()) {
            annotations[i++] = annotation.getText();
        }
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", annotations=" + Arrays.toString(annotations) +
                ", LOC=" + LOC +
                ", CC=" + CC;
    }

    public String getName() {
        return name;
    }

    public String[] getAnnotations() {
        return annotations;
    }

    public int getLOC() {
        return LOC;
    }

    public int getCC() {
        return CC;
    }
}
