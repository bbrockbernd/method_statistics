package core;

import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiType;
import com.intellij.psi.javadoc.PsiDocComment;

import java.util.Arrays;

/**
 * Method summary object for storing relevant method data for the plugin.
 */
public class MethodSummary {

    public PsiMethod method;

    private String name;
    private String modifiers;
    private String parameterList;
    private String returnType;
    private String signature;
    private String[] annotations;
    private String documentation;
    private int parameterSize;
    private int loc;
    private int cc;

    /**
     * Constructor for the Summary class.
     * It calculates the statistics for a method.
     * @param method to analyze.
     */
    public MethodSummary(PsiMethod method) {
        this.method = method;
        name = method.getName();
        extractModifiers();
        extractParameters();
        extractReturnType();
        extractAnnotations();
        buildSignature();
        extractDescription();
        extractParametersSize();
        MethodVisitor visitor = new MethodVisitor();
        method.accept(visitor);
        cc = visitor.getCc();
        loc = computeLoc();
    }

    /**
     * Extract method description as single String.
     */
    public void extractDescription() {
        documentation = "no description";
        PsiDocComment doc = method.getDocComment();
        if (doc == null) {
            return;
        }
        documentation = doc.getText();
    }

    /**
     * Put all extracted info into a complete method signature.
     */
    public void buildSignature() {
        signature = modifiers + " " + returnType + " " + name + parameterList;
    }

    /**
     * Extract returnType as single String.
     */
    public void extractReturnType() {
        returnType = "void";
        PsiType type = method.getReturnType();
        if (type == null) {
            return;
        }
        returnType = type.getPresentableText();
    }

    /**
     * Extract method parameter as single String.
     */
    public void extractParameters() {
        parameterList = method.getParameterList().getText();
    }

    /**
     * Extract method parameters size as single int.
     */
    public void extractParametersSize() {
        parameterSize = method.getParameters().length;
    }

    /**
     * Extract method modifiers as single String.
     */
    public void extractModifiers() {
        modifiers = method.getModifierList().getText();
    }

    /**
     * Extract annotations as string array.
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
        return signature
                + ", annotations=" + Arrays.toString(annotations)
                + ", description=" + documentation
                + ", LOC=" + loc
                + ", CC=" + cc;
    }

    public String getName() {
        return name;
    }

    public int getLOC() {
        return loc;
    }


    public int getCC() {
        return cc;
    }

    public String getSignature() {
        return signature;
    }

    public String getParameterList() {
        return parameterList;
    }

    public int getParameterSize() {
        return parameterSize;
    }

    public String getReturnType() {
        return returnType;
    }
}

