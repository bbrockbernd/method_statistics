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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JLabel;

public class StatisticsToolWindow {

    ToolWindowManager toolWindowManager;
    ToolWindow toolWindow;

    public StatisticsToolWindow(Project project) {
        toolWindowManager = ToolWindowManager.getInstance(project);
        toolWindow = toolWindowManager
            .registerToolWindow("Method Statistics", true, ToolWindowAnchor.BOTTOM);
    }

    public void ShowWindow(String className, List<MethodSummary> methodItems) {
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        ListTableModel<MethodSummary> model = new ListTableModel<>(
            new ColumnInfoFactory().getColumnInfos(), methodItems);
        JBTable table = new JBTable(model);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = table.convertRowIndexToModel(table.getSelectedRow());
                    PsiMethod method = methodItems.get(index).method;
                    method.navigate(true);
                }
            }
        });
        JBScrollPane scrollPane = new JBScrollPane(table);
        JBSplitter splitterPane = new JBSplitter(false);
        splitterPane.setFirstComponent(scrollPane);
        JLabel temp = new JLabel("Extended method info");
        splitterPane.setSecondComponent(temp);

        Content content;
        if((content = toolWindow.getContentManager().findContent(className)) != null){
            content.setComponent(splitterPane);
        } else {
            content = contentFactory.createContent(splitterPane, className, false);
            toolWindow.getContentManager().addContent(content);
        }
        toolWindow.getContentManager().setSelectedContent(content);
        toolWindow.show();
    }
}
