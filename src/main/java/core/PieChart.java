package core;

import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;

import java.awt.*;
import java.util.Random;

public class PieChart {

    private org.knowm.xchart.PieChart chart;

    public PieChart(MethodSummary[] methods) {
        chart = new PieChartBuilder().width(800).height(600)
                .title("LOC distribution").build();
        Color[] sliceColors = new Color[methods.length];
        Random r = new Random();
        for(int i = 0; i < methods.length; i++){
            sliceColors[i] = new Color(r.nextInt(256),
                    r.nextInt(256),
                    r.nextInt(256));
            chart.addSeries(methods[i].getName(), methods[i].getLOC());
        }
        chart.getStyler().setSeriesColors(sliceColors);
    }

    public org.knowm.xchart.PieChart getChart() {
        return chart;
    }

    public void display() {
        new SwingWrapper<>(chart).displayChart();
    }
}
