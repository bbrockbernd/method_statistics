package core.methodStats;

import com.intellij.psi.*;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;

import static com.intellij.psi.util.PsiTreeUtil.findChildrenOfType;

class MethodVisitor extends PsiRecursiveElementWalkingVisitor {

    private int cc = 1;

    @Override
    public void visitElement(@NotNull PsiElement element) {

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

    public int getCc() {
        return cc;
    }

    private void checkCond(PsiExpression cond){
        cc++;
        if (cond != null) {
            Collection<PsiJavaToken> binExpressions = findChildrenOfType(cond, PsiJavaToken.class);
            binExpressions.forEach((exp) -> {
                if (exp.getTokenType().equals(JavaTokenType.ANDAND)
                        || exp.getTokenType().equals(JavaTokenType.OROR)) {
                    cc++;
                }
            });
        }
    }
}