package core;

import com.intellij.ui.JBSplitter;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;

import java.awt.*;
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
        for(int i = 0; i < methods.length; i++){
            sliceColors[i] = new Color(r.nextInt(256),
                    r.nextInt(256),
                    r.nextInt(256));
            LOCchart.addSeries(methods[i].getName(), methods[i].getLOC());
            CCchart.addSeries(methods[i].getName(), methods[i].getCC());
        }
        LOCchart.getStyler().setSeriesColors(sliceColors);
        CCchart.getStyler().setSeriesColors(sliceColors);
    }

    public JBSplitter getPanel() {
        JBSplitter chartSplitterPane = new JBSplitter(false);
        chartSplitterPane.setFirstComponent(new XChartPanel(LOCchart));
        chartSplitterPane.setSecondComponent(new XChartPanel(CCchart));
        return chartSplitterPane;
    }

}
