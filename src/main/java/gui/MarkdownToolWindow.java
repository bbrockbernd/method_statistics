package gui;

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
import core.ClassSummary;
import core.MarkdownGlobalSummary;
import core.MarkdownSummary;
import core.MethodSummary;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * This is the graphical report of the method statistics plugin.
 */
public class MarkdownToolWindow {

    ToolWindowManager toolWindowManager;
    ToolWindow toolWindow;

    public MarkdownToolWindow(Project project) {
        toolWindowManager = ToolWindowManager.getInstance(project);
        toolWindow = toolWindowManager
            .registerToolWindow("Markdown Statistics", true, ToolWindowAnchor.BOTTOM);
    }

    /**
     * Add content to the tool window.
     * @param summary The markdown global summary found of the analyzed project.
     */
    public void ShowWindow(MarkdownGlobalSummary summary) {
        //Splitter pane splits the tool window
        JBSplitter splitterPane = new JBSplitter(false, 0.6f);
        JBSplitter leftSplitterPane = new JBSplitter(false, 0.5f);
        generateTable(summary.getMarkdowns(), leftSplitterPane);

        //PieCharts as right component
        splitterPane.setSecondComponent(new JLabel("Select a method to show documentation",
            JLabel.CENTER));
        splitterPane.setFirstComponent(leftSplitterPane);

        Content content;
        if ((content = toolWindow.getContentManager().findContent("???")) != null) {
            content.setComponent(splitterPane);
        } else {
            ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
            content = contentFactory.createContent(splitterPane, "???", false);
            toolWindow.getContentManager().addContent(content);
        }
        toolWindow.getContentManager().setSelectedContent(content);
        toolWindow.show();
    }

    /**
     * Generates JBtable to place in tool window.
     * @param markdowns Found markdown files during analysis.
     * @return returns ui component
     */
    private void generateTable(List<MarkdownSummary> markdowns, JBSplitter tableSplitter) {
        ListTableModel<MarkdownSummary> model = new ListTableModel<>(
            new MarkdownColumnInfoFactory().getColumnInfos(), markdowns);
        JBTable table = new JBTable(model);
//        setMouseAdapter(table, markdowns, tableSplitter);
        tableSplitter.setFirstComponent(new JBScrollPane(table));
        tableSplitter.setSecondComponent(new JLabel("Select a method to show documentation",
            JLabel.CENTER));
    }

    /**
     * Makes table clickable.
     * After double clicking on a row shows method definition in editor.
     * @param table Table to make clickable.
     * @param methodItems Found methods in during analyse.
     */
//    private void setMouseAdapter(JBTable table, List<MarkdownSummary> markdowns, JBSplitter tableSplitter) {
//        MouseAdapter adapter = new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                int index = table.convertRowIndexToModel(table.getSelectedRow());
//                PsiMethod method = markdowns.get(index).method;
//                JComponent component = (JComponent) DocumentationComponent.createAndFetch(method.getProject(), method, () -> {});
//                tableSplitter.setSecondComponent(component);
//                if (e.getClickCount() == 2) {
//                    method.navigate(true);
//                }
//            }
//        };
//        table.addMouseListener(adapter);
//    }
}