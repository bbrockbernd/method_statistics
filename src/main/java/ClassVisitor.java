import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiRecursiveElementVisitor;

import java.util.ArrayList;
import java.util.List;

class ClassVisitor extends PsiRecursiveElementVisitor {

    private List<PsiMethod> psiMethods = new ArrayList<PsiMethod>();

    @Override
    public void visitElement(PsiElement element) {

        if (element instanceof PsiMethod) {
            psiMethods.add((PsiMethod) element);
        }

        super.visitElement(element);
    }

    public List<PsiMethod> getPsiMethods() {
        return psiMethods;
    }
}