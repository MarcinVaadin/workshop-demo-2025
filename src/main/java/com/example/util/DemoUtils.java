package com.example.util;

import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;

public class DemoUtils {

    public static void setupChart(Chart chart, DataSeries series) {
        chart.getConfiguration().addSeries(series);
        String[] categories = series.getData().stream()
                .map(DataSeriesItem::getName)
                .toArray(String[]::new);
        chart.getConfiguration().getxAxis().setCategories(categories);
    }

}
