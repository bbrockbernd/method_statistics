package gui;

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
import core.ClassSummary;
import core.MethodSummary;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JComponent;

/**
 * This is the graphical report of the method statistics plugin.
 */
public class StatisticsToolWindow {

    ToolWindowManager toolWindowManager;
    ToolWindow toolWindow;

    public StatisticsToolWindow(Project project) {
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
        JBSplitter splitterPane = new JBSplitter(false);
        splitterPane.setFirstComponent(generateTable(classItem.getMethodsList()));

        //PieCharts as right component
        splitterPane.setSecondComponent(classItem.getChartsPanel());

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
    private JComponent generateTable(List<MethodSummary> methodItems) {
        ListTableModel<MethodSummary> model = new ListTableModel<>(
            new ColumnInfoFactory().getColumnInfos(), methodItems);
        JBTable table = new JBTable(model);
        setMouseAdapter(table, methodItems);
        return new JBScrollPane(table);
    }

    /**
     * Makes table clickable.
     * After double clicking on a row shows method definition in editor.
     * @param table Table to make clickable.
     * @param methodItems Found methods in during analyse.
     */
    private void setMouseAdapter(JBTable table, List<MethodSummary> methodItems) {
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = table.convertRowIndexToModel(table.getSelectedRow());
                    PsiMethod method = methodItems.get(index).method;
                    method.navigate(true);
                }
            }
        };
        table.addMouseListener(adapter);
    }
}
