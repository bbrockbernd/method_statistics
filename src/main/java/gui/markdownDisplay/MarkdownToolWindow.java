package gui.markdownDisplay;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.psi.PsiElement;
import com.intellij.ui.JBSplitter;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.table.JBTable;
import com.intellij.util.ui.ListTableModel;
import core.markdownStats.LinkSummary;
import core.markdownStats.MarkdownGlobalSummary;
import core.markdownStats.MarkdownSummary;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;

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
        JBSplitter splitterPane = new JBSplitter(false);
        generateMarkdownTable(summary.getMarkdowns(), splitterPane);

        Content content;
        if ((content = toolWindow.getContentManager()
                .findContent(summary.getProject().getName())) != null) {
            content.setComponent(splitterPane);
        } else {
            ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
            content = contentFactory.createContent(splitterPane,
                    summary.getProject().getName(), false);
            toolWindow.getContentManager().addContent(content);
        }
        toolWindow.getContentManager().setSelectedContent(content);
        toolWindow.show();
    }

    /**
     * Generates JBtable to place in tool window.
     * @param markdowns Found markdown files during analysis.
     */
    private void generateMarkdownTable(List<MarkdownSummary> markdowns, JBSplitter tableSplitter) {
        ListTableModel<MarkdownSummary> model = new ListTableModel<>(
            new MarkdownColumnInfoFactory().getColumnInfos(), markdowns);
        JBTable table = new JBTable(model);
        setMouseAdapter(table, markdowns, tableSplitter);
        tableSplitter.setFirstComponent(new JBScrollPane(table));
        tableSplitter.setSecondComponent(new JLabel("Select a file to see link details",
            JLabel.CENTER));
    }

    /**
     * Makes table clickable.
     * After double clicking on a row shows method definition in editor.
     * @param table Table to make clickable.
     * @param markdowns Found markdown files in during analyse.
     * @param tableSplitter the JBSplitter of the tables
     */
    private void setMouseAdapter(JBTable table, List<MarkdownSummary> markdowns, JBSplitter tableSplitter) {
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = table.convertRowIndexToModel(table.getSelectedRow());
                generateLinksTable(markdowns.get(index).getLinks(), tableSplitter);
            }
        };
        table.addMouseListener(adapter);
    }
    /**
     * Generates JBtable to place in tool window.
     * @param links Found links during analysis.
     */
    private void generateLinksTable(List<LinkSummary> links, JBSplitter tableSplitter) {
        ListTableModel<LinkSummary> model = new ListTableModel<>(
                new LinksColumnInfoFactory().getColumnInfos(), links);
        JBTable table = new JBTable(model);
        setLinksMouseAdapter(table,links);
        tableSplitter.setSecondComponent(new JBScrollPane(table));
    }
    /**
     * Makes table clickable.
     * After double clicking on a row shows method definition in editor.
     * @param table Table to make clickable.
     * @param links Found links in file during analyse.
     */
    private void setLinksMouseAdapter(JBTable table, List<LinkSummary> links) {
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = table.convertRowIndexToModel(table.getSelectedRow());
                LinkSummary link = links.get(index);
                if (e.getClickCount() == 2) {
                    link.navigate(true);
                }
            }
        };
        table.addMouseListener(adapter);
    }

}
