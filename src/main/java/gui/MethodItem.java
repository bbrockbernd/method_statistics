package gui;

import com.intellij.psi.PsiMethod;

public class MethodItem {

    public String name;
    public String description;
    public String CC;
    public String LOC;
    public String signatures;
    public PsiMethod psiMethod;

    public MethodItem(String name, String description, String CC, String LOC,
        String signatures, PsiMethod psiMethod) {
        this.name = name;
        this.description = description;
        this.CC = CC;
        this.LOC = LOC;
        this.signatures = signatures;
        this.psiMethod = psiMethod;
    }
}
