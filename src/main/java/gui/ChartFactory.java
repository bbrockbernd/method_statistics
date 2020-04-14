package gui;

import com.intellij.ui.JBSplitter;
import core.MethodSummary;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;

import java.awt.Color;
import java.util.Random;

public class ChartFactory {

    private final org.knowm.xchart.PieChart locChart;
    private final org.knowm.xchart.PieChart ccChart;

    /**
     * Constructor for the CartFactory.
     * @param methods array for the statistics.
     */
    public ChartFactory(MethodSummary[] methods) {
        locChart = new PieChartBuilder().title("LOC distribution").build();
        ccChart = new PieChartBuilder().title("CC distribution").build();
        Color[] sliceColors = new Color[methods.length];
        Random r = new Random();
        for (int i = 0; i < methods.length; i++) {
            sliceColors[i] = new Color(r.nextInt(256),
                    r.nextInt(256),
                    r.nextInt(256));
            locChart.addSeries(methods[i].getName()
                    + methods[i].getParameterList(), methods[i].getLOC());
            ccChart.addSeries(methods[i].getName()
                    + methods[i].getParameterList(), methods[i].getCC());
        }
        locChart.getStyler().setSeriesColors(sliceColors);
        ccChart.getStyler().setSeriesColors(sliceColors);
    }

    /**
     * Create a JPanel with the two pie charts.
     * @return the JBSplitter created.
     */
    public JBSplitter getPanel() {
        JBSplitter chartSplitterPane = new JBSplitter(false);
        chartSplitterPane.setFirstComponent(new XChartPanel(locChart));
        chartSplitterPane.setSecondComponent(new XChartPanel(ccChart));
        return chartSplitterPane;
    }

}
