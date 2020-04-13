import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiMethod;

import java.util.Arrays;

public class ClassSummary {

    private PsiJavaFile file;

    private String name;
    private MethodSummary[] methods;
    private PieChart chart;

    public ClassSummary(PsiJavaFile file) {
        this.file = file;
        name = file.getName();
        ClassVisitor visitor = new ClassVisitor();
        file.accept(visitor);
        methods = new MethodSummary[visitor.getPsiMethods().size()];
        int i = 0;
        for(PsiMethod m : visitor.getPsiMethods()) {
            MethodSummary methodSummary = new MethodSummary(m);
            methods[i++] = methodSummary;
        }
        chart = new PieChart(methods);
    }

    @Override
    public String toString() {
        return "\nClassSummary{" +
                "name='" + name + '\'' +
                ", methods=" + Arrays.toString(methods) +
                '}';
    }
}
