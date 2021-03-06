package core.methodStats;

import com.intellij.psi.PsiMethod;

import java.awt.*;
import java.util.Arrays;

/**
 * Method summary object for storing relevant method data for the plugin.
 */
public class MethodSummary {

    private PsiMethod method;
    private String name;
    private String parameterList;
    private String returnType;
    private int parameterSize;
    private int loc;
    private int cc;
    private Color color;

    /**
     * Constructor for the Summary class.
     * It calculates the statistics for a method.
     * @param method to analyze.
     */
    public MethodSummary(PsiMethod method) {
        this.method = method;
        this.name = method.getName();
        this.parameterList = method.getParameterList().getText();
        this.parameterSize = method.getParameters().length;
        this.returnType = method.getReturnType() == null ? "Constructor"
            : method.getReturnType().getPresentableText();
        MethodVisitor visitor = new MethodVisitor();
        method.accept(visitor);
        cc = visitor.getCc();
        loc = computeLoc();
    }

    /**
     * The LOC includes the signature of the method.
     *
     * @return the lines of code of the actual method.
     */
    public int computeLoc() {
        String text = method.getBody() == null ? "" : method.getBody().getText();
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

    public PsiMethod getMethod() {
        return method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParameterList() {
        return parameterList;
    }

    public String getReturnType() {
        return returnType;
    }

    public int getParameterSize() {
        return parameterSize;
    }

    public int getLOC() {
        return loc;
    }

    public int getCC() {
        return cc;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}

