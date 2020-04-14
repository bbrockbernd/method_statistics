package core.methodStats;

import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiMethod;
import gui.methodDisplay.ChartFactory;

import java.util.Comparator;

import java.util.Arrays;
import java.util.List;
import javax.swing.JPanel;

/**
 * Class that creates a human readable summary of a Java class.
 * For every method in that class, it creates a method summary using the
 * MethodSummary class. Those are used for the charts.
 */
public class ClassSummary {

    private PsiJavaFile file;

    private String name;
    private MethodSummary[] methods;
    private JPanel chartsPanel;

    /**
     * Constructor for the summary.
     * It creates a summary of each method present
     * in this class.
     * @param file current Java file.
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
        removeDuplicates(methods);
        chartsPanel = new ChartFactory(methods).getPanel();
    }

    @Override
    public String toString() {
        return "\nClassSummary{"
                + "name='" + name + '\''
                + ", methods=" + Arrays.toString(methods)
                + '}';
    }

    public JPanel getChartsPanel() {
        return chartsPanel;
    }

    public List<MethodSummary> getMethodsList() {
        return Arrays.asList(methods);
    }

    public void removeDuplicates (MethodSummary[] methods) {
        Arrays.sort(methods, Comparator.comparing(method -> method.getName()));
        int c = 1;
        String name = "";
        for(MethodSummary method: methods) {
            if (name.equals(method.getName())) {
                method.setName(method.getName() + " (" + c + ")");
                c++;
            } else {
                c = 1;
                name = method.getName();
            }
        }
    }

}
