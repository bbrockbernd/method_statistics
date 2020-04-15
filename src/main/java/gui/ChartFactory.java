package gui;

import com.intellij.ui.JBSplitter;
import core.MethodSummary;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;

import java.awt.Color;
import java.util.Random;

public class ChartFactory {

    private org.knowm.xchart.PieChart LOCchart;
    private org.knowm.xchart.PieChart CCchart;

    public ChartFactory(MethodSummary[] methods) {
        LOCchart = new PieChartBuilder().width(800).height(600)
            .title("LOC distribution").build();
        CCchart = new PieChartBuilder().width(800).height(600)
            .title("CC distribution").build();
        Color[] sliceColors = new Color[methods.length];
        Random r = new Random();
        for (int i = 0; i < methods.length; i++) {
            Color color = new Color(r.nextInt(256),
                    r.nextInt(256),
                    r.nextInt(256));
            sliceColors[i] = color;
            methods[i].setColor(color);
            LOCchart.addSeries(methods[i].getName(), methods[i].getLOC());
            CCchart.addSeries(methods[i].getName(), methods[i].getCC());
        }
        LOCchart.getStyler().setSeriesColors(sliceColors);
        LOCchart.getStyler().setLegendVisible(false);
        CCchart.getStyler().setSeriesColors(sliceColors);
        CCchart.getStyler().setLegendVisible(false);
    }

    public JBSplitter getPanel() {
        JBSplitter chartSplitterPane = new JBSplitter(false, 0.4f);

        chartSplitterPane.setFirstComponent(new XChartPanel(LOCchart));
        chartSplitterPane.setSecondComponent(new XChartPanel(CCchart));
        return chartSplitterPane;
    }

}
