import com.intellij.psi.*;
import com.intellij.psi.javadoc.PsiDocComment;
import org.apache.commons.lang.StringUtils;
import java.util.Arrays;

public class MethodSummary {

    private PsiMethod method;

    private String name;
    private String modifiers;
    private String parameterList;
    private String returnType;
    private String signature;
    private String[] annotations;
    private String documentation;
    private int LOC;
    private int CC;

    public MethodSummary(PsiMethod method) {
        this.method = method;
        name = method.getName();
        extractModifiers();
        extractParameters();
        extractReturnType();
        extractAnnotations();
        buildSignature();
        extractDescription();
        MethodVisitor visitor = new MethodVisitor();
        method.accept(visitor);
        CC = visitor.getCC();
        LOC = computeLOC();
    }

    /**
     * extract method description as single String.
     */
    public void extractDescription() {
        documentation = "no description";
        PsiDocComment doc = method.getDocComment();
        if(doc == null) return;
        documentation = doc.getText();
    }

    /**
     * put all extracted info into a complete method signature.
     */
    public void buildSignature() {
        signature = modifiers + " " + returnType + " " + name + parameterList;
    }

    /**
     * extract returnType as single String.
     */
    public void extractReturnType() {
        returnType = "void";
        PsiType type = method.getReturnType();
        if(type == null) return;
        returnType = type.getPresentableText();
    }

    /**
     * extract method parameter as single String.
     */
    public void extractParameters() {
        parameterList = method.getParameterList().getText();
    }

    /**
     * extract method modifiers as single String.
     */
    public void extractModifiers() {
        modifiers = method.getModifierList().getText();
    }

    /**
     * extract annotations as string array.
     */
    public void extractAnnotations() {
        int i = 0;
        annotations = new String[method.getAnnotations().length];
        for(PsiAnnotation annotation: method.getAnnotations()) {
            annotations[i++] = annotation.getText();
        }
    }

    /**
     * The LOC includes the signature of the method.
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
        return signature +
                ", annotations=" + Arrays.toString(annotations) +
                ", description=" + documentation +
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