import com.intellij.psi.*;
import org.apache.commons.lang.StringUtils;

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
        MethodVisitor visitor = new MethodVisitor();
        method.accept(visitor);
        CC = visitor.getCC();
        LOC = computeLOC();
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

    /**
     *
     * @return the lines of code of the actual method.
     */
    public int computeLOC() {
        String body = method.getBody().getText();
        return StringUtils.countMatches(body, "\n") - 1;
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