package gui.methodDisplay;

import com.intellij.codeInsight.documentation.DocumentationComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.psi.PsiMethod;
import com.intellij.ui.JBSplitter;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.table.JBTable;
import com.intellij.util.ui.ListTableModel;
import core.methodStats.ClassSummary;
import core.methodStats.MethodSummary;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * This is the graphical report of the method statistics plugin.
 */
public class MethodToolWindow {

    ToolWindowManager toolWindowManager;
    ToolWindow toolWindow;

    public MethodToolWindow(Project project) {
        toolWindowManager = ToolWindowManager.getInstance(project);
        toolWindow = toolWindowManager
            .registerToolWindow("Method Statistics", true, ToolWindowAnchor.BOTTOM);
    }

    /**
     * Add content to the tool window.
     * @param className The name of the analyzed java class.
     * @param classItem The class summary found of the analyzed java class.
     */
    public void ShowWindow(String className, ClassSummary classItem) {
        //Splitter pane splits the tool window
        JBSplitter splitterPane = new JBSplitter(false, 0.6f);
        JBSplitter leftSplitterPane = new JBSplitter(false, 0.5f);
        generateTable(classItem.getMethodsList(), leftSplitterPane);

        //PieCharts as right component
        splitterPane.setSecondComponent(classItem.getChartsPanel());
        splitterPane.setFirstComponent(leftSplitterPane);

        Content content;
        if ((content = toolWindow.getContentManager().findContent(className)) != null) {
            content.setComponent(splitterPane);
        } else {
            ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
            content = contentFactory.createContent(splitterPane, className, false);
            toolWindow.getContentManager().addContent(content);
        }
        toolWindow.getContentManager().setSelectedContent(content);
        toolWindow.show();
    }

    /**
     * Generates JBtable to place in tool window.
     * @param methodItems Found methods during analsye.
     * @return returns ui component
     */
    private void generateTable(List<MethodSummary> methodItems, JBSplitter tableSplitter) {
        ListTableModel<MethodSummary> model = new ListTableModel<>(
            new MethodColumnInfoFactory().getColumnInfos(), methodItems);
        JBTable table = new JBTable(model);
        setMouseAdapter(table, methodItems, tableSplitter);
        tableSplitter.setFirstComponent(new JBScrollPane(table));
        tableSplitter.setSecondComponent(new JLabel("Select a method to show documentation",
            JLabel.CENTER));
    }

    /**
     * Makes table clickable.
     * After double clicking on a row shows method definition in editor.
     * @param table Table to make clickable.
     * @param methodItems Found methods in during analyse.
     * @param tableSplitter the JBSplitter of the tables
     */
    private void setMouseAdapter(JBTable table, List<MethodSummary> methodItems, JBSplitter tableSplitter) {
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = table.convertRowIndexToModel(table.getSelectedRow());
                PsiMethod method = methodItems.get(index).getMethod();
                JComponent component = (JComponent) DocumentationComponent.createAndFetch(method.getProject(), method, () -> {});
                tableSplitter.setSecondComponent(component);
                if (e.getClickCount() == 2) {
                    method.navigate(true);
                }
            }
        };
        table.addMouseListener(adapter);
    }
}
