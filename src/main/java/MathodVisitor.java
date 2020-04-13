import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiRecursiveElementVisitor;
import com.intellij.psi.PsiStatement;

import java.util.ArrayList;
import java.util.List;

class MethodVisitor extends PsiRecursiveElementVisitor {

    private List<PsiStatement> psiStatements = new ArrayList<PsiStatement>();

    @Override
    public void visitElement(PsiElement element) {

        if (element instanceof PsiStatement) {
            psiStatements.add((PsiStatement) element);
        }

        super.visitElement(element);
    }

    public List<PsiStatement> getPsiStatements() {
        return psiStatements;
    }
}