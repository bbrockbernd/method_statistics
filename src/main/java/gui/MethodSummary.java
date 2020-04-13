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

    public MethodSummary(PsiMethod method) {
        this.method = method;
        this.name = method.getName();
        this.params = method.getParameters().length;
        this.CC = 0;
        this.LOC = 0;
        this.returnType = method.getReturnType().getPresentableText();
    }

    /**
     *
     * @return the name of the method
     */
    public String getName() {return method.getName();}

    /**
     *
     * @return method annotations
     */
    public String getAnnotations() {
        String result = "";
        for(PsiAnnotation annotation: method.getAnnotations()) {
            result += annotation.getQualifiedName() + "\n";
        }
        return result;
    }

    /**
     *
     * @return a text summary of the method
     */
    public String createSummary() {
        String result = "";

        result += getName() + ":\n";

        if(!getAnnotations().isEmpty()) {
            result += "Annotations:\n";
            result += getAnnotations();
        }

        return result;

    }

}
