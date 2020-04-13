import com.intellij.psi.PsiConditionalExpression;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiForStatement;
import com.intellij.psi.PsiForeachStatement;
import com.intellij.psi.PsiIfStatement;
import com.intellij.psi.PsiStatement;
import com.intellij.psi.PsiTryStatement;
import com.intellij.psi.PsiWhileStatement;

import java.util.ArrayList;
import java.util.List;

class MethodVisitor extends PsiElementVisitor {

    private int cc = 1;

    private List<PsiStatement> psiStatements = new ArrayList<PsiStatement>();

    @Override
    public void visitElement(PsiElement element) {

        //remember all the statements
        if (element instanceof PsiStatement) {
            psiStatements.add((PsiStatement) element);
        }

        //increase CC by one for each decision point
        //TODO: consider && and ||
        if (element instanceof PsiIfStatement
                || element instanceof PsiForStatement
                || element instanceof PsiForeachStatement
                || element instanceof PsiWhileStatement
                || element instanceof PsiConditionalExpression
                || element instanceof PsiTryStatement) {
            cc++;
        }

        super.visitElement(element);
    }

    public List<PsiStatement> getPsiStatements() {
        return psiStatements;
    }

    public int getCc() {
        return cc;
    }
}