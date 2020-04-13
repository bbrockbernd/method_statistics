package core;

import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiMethod;
import java.util.Arrays;

/**
 * Method data object for storing relevant method data for the plugin.
 */
public class MethodSummary {

    public String name;
    public int CC;
    public int LOC;
    public String returnType;
    public PsiMethod method;
    public int params;
    public String[] annotations;

    public MethodSummary(PsiMethod method) {
        this.method = method;
        this.name = method.getName();
        this.params = method.getParameters().length;
        this.returnType = method.getReturnType().getPresentableText();
        extractAnnotations();
        MethodVisitor visitor = new MethodVisitor();
        method.accept(visitor);
        CC = visitor.getCC();
        LOC = computeLOC();
    }

    /**
     * extract annotations as string array
     */
    public void extractAnnotations() {
        int i = 0;
        annotations = new String[method.getAnnotations().length];
        for (PsiAnnotation annotation : method.getAnnotations()) {
            annotations[i++] = annotation.getText();
        }
    }

    /**
     * The LOC includes the signature of the method.
     *
     * @return the lines of code of the actual method.
     */
    public int computeLOC() {
        String text = method.getBody().getText();
        int lines = 0;
        boolean emptyLine = true;
        final char[] chars = text.toCharArray();

        for (final char c : chars) {
            if (c == '\n' || c == '\r') {
                if (!emptyLine) {
                    lines++;
                    emptyLine = true;
                }
            } else if (c != ' ' && c != '\t') {
                emptyLine = false;
            }
        }
        if (!emptyLine) {
            lines++;
        }
        return lines;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
            ", annotations=" + Arrays.toString(annotations) +
            ", LOC=" + LOC +
            ", CC=" + CC;
    }
}

