package core;

import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiMethod;
import gui.ChartFactory;

import javax.swing.JPanel;
import java.util.Arrays;
import java.util.List;

public class ClassSummary {

    private PsiJavaFile file;

    private String name;
    private MethodSummary[] methods;
    private JPanel chartsPanel;

    /**
     * Constructor of the class, which extracts all the info about methods.
     * @param file the current java file.
     */
    public ClassSummary(PsiJavaFile file) {
        this.file = file;
        name = file.getName();
        ClassVisitor visitor = new ClassVisitor();
        file.accept(visitor);
        methods = new MethodSummary[visitor.getPsiMethods().size()];
        int i = 0;
        for (PsiMethod m : visitor.getPsiMethods()) {
            MethodSummary methodSummary = new MethodSummary(m);
            methods[i++] = methodSummary;
        }
        chartsPanel = new ChartFactory(methods).getPanel();
    }

    @Override
    public String toString() {
        return "\nClassSummary{" +
                "name='" + name + '\'' +
                ", methods=" + Arrays.toString(methods) +
                '}';
    }

    /**
     *
     * @return the panel with pie charts
     */
    public JPanel getChartsPanel() {
        return chartsPanel;
    }

    /**
     *
     * @return list of method summaries in this class.
     */
    public List<MethodSummary> getMethodsList() {
        return Arrays.asList(methods);
    }
}
