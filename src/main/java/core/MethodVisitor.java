package core;
import com.intellij.psi.*;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

class MethodVisitor extends PsiRecursiveElementWalkingVisitor {

    private int CC = 1;

    private List<PsiStatement> psiStatements = new ArrayList<>();

    @Override
    public void visitElement(@NotNull PsiElement element) {

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
            CC++;
        }

        super.visitElement(element);
    }

    public List<PsiStatement> getPsiStatements() {
        return psiStatements;
    }

    public int getCC() {
        return CC;
    }
}