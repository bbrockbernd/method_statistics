import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiMethod;

import java.util.Arrays;

public class MethodSummary {

    private PsiMethod method;
    private String name;
    private String[] annotations;
    private int loc;
    private int cc;

    public MethodSummary(PsiMethod method) {
        this.method = method;
        name = method.getName();
        extractAnnotations();
        MethodVisitor visitor = new MethodVisitor();
        method.accept(visitor);
        cc = visitor.getCc();
        loc = computeLoc();
    }

    /**
     * Extract annotations as string array.
     */
    public void extractAnnotations() {
        int i = 0;
        annotations = new String[method.getAnnotations().length];
        for (PsiAnnotation annotation: method.getAnnotations()) {
            annotations[i++] = annotation.getText();
        }
    }

    /**
     * The LOC includes the signature of the method.
     * @return the lines of code of the actual method.
     */
    public int computeLoc() {
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
        return "name='" + name + '\''
                + ", annotations=" + Arrays.toString(annotations)
                + ", LOC=" + loc
                + ", CC=" + cc;
    }

    public String getName() {
        return name;
    }

    public String[] getAnnotations() {
        return annotations;
    }

    public int getLoc() {
        return loc;
    }

    public int getCc() {
        return cc;
    }
}