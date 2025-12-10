package com.example.util;

import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.combobox.ComboBox;

public class DemoUtils {

    public static void setupChart(Chart chart, DataSeries series) {
        chart.getConfiguration().addSeries(series);
        String[] categories = series.getData().stream()
                .map(DataSeriesItem::getName)
                .toArray(String[]::new);
        chart.getConfiguration().getxAxis().setCategories(categories);
    }

    public static void addIssueComboBoxValueChangeListener(ComboBox<String> comboBox, String key) {
        comboBox.addValueChangeListener(event -> {
            comboBox.getUI().ifPresent(ui -> {
                var qp = ui.getActiveViewLocation().getQueryParameters();
                if (event.getValue() == null) {
                    qp = qp.excluding(key);
                } else {
                    qp = qp.merging(key, event.getValue());
                }
                ui.navigate("issues", qp);
            });
        });
    }

    /**
     * @Override
 *     public void beforeEnter(BeforeEnterEvent event) {
 *         event.getLocation().getQueryParameters().getSingleParameter("severity")
 *                 .ifPresent(severityComboBox::setValue);
 *         event.getLocation().getQueryParameters().getSingleParameter("impact")
 *                 .ifPresent(impactComboBox::setValue);
 *         List<String> filter = new ArrayList<>();
 *         if (severityComboBox.getValue() != null) {
 *             filter.add(severityComboBox.getValue());
 *         }
 *         if (impactComboBox.getValue() != null) {
 *             filter.add(impactComboBox.getValue());
 *         }
 *         grid.setItems(issueService.findIssues(filter));
 *     }
     */

}
