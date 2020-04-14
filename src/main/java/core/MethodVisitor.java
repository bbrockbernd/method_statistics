package core;

import com.intellij.psi.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;

import static com.intellij.psi.util.PsiTreeUtil.findChildrenOfType;

class MethodVisitor extends PsiRecursiveElementWalkingVisitor {

    private int cc = 1;

    private List<PsiStatement> psiStatements = new ArrayList<>();

    @Override
    public void visitElement(@NotNull PsiElement element) {

        //remember all the statements
        if (element instanceof PsiStatement) {
            psiStatements.add((PsiStatement) element);
        }

        //increase CC by one for each decision point
        if (element instanceof PsiIfStatement) {
            checkCond(((PsiIfStatement) element).getCondition());
        }
        if (element instanceof PsiForStatement) {
            checkCond(((PsiForStatement) element).getCondition());
        }
        if (element instanceof PsiForeachStatement) {
            cc++;
        }
        if (element instanceof PsiWhileStatement) {
            checkCond(((PsiWhileStatement) element).getCondition());
        }
        if (element instanceof PsiConditionalExpression) {
            checkCond(((PsiConditionalExpression) element).getCondition());
        }
        if (element instanceof PsiTryStatement) {
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

    private void checkCond(PsiExpression cond){
        cc++;
        if (cond != null) {
            Collection<PsiBinaryExpression> binExpressions = findChildrenOfType(cond, PsiBinaryExpression.class);
            if (binExpressions != null) {
                binExpressions.forEach((exp) -> {
                    if (exp.getOperationTokenType().equals(JavaTokenType.ANDAND)
                            || exp.getOperationTokenType().equals(JavaTokenType.OROR)) {
                        cc++;
                    }
                });
            }
        }
    }
}