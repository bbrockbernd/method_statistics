import com.intellij.psi.*;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
package gui;

import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiMethod;

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
        if (method.getBody().isEmpty()) return 0;
        String body = method.getBody().getText();
        System.out.println(body);
        int newLines = StringUtils.countMatches(body, "\n");
        if (newLines <= 1) return 1;
        return newLines;
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

    }

}