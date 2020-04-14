package core;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiRecursiveElementVisitor;

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * Visitor for the methods of a class.
 */
class ClassVisitor extends PsiRecursiveElementVisitor {

    private List<PsiMethod> psiMethods = new ArrayList<>();

    @Override
    public void visitElement(@NotNull PsiElement element) {

        if (element instanceof PsiMethod) {
            psiMethods.add((PsiMethod) element);
        }

        super.visitElement(element);
    }

    public List<PsiMethod> getPsiMethods() {
        return psiMethods;
    }
}