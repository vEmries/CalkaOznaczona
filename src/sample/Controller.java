package sample;

import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;

public class Controller {

    public javafx.scene.control.TextField textFieldA;
    public javafx.scene.control.TextField textFieldB;
    public javafx.scene.control.TextField textFieldFunction;
    public javafx.scene.control.TextField textFieldResult;
    public javafx.scene.control.Button buttonResult;
    public javafx.scene.control.Button buttonClear;
    public javafx.scene.chart.LineChart functionChart;


    private double mathFunction(double x) {
        return Math.sin(x);
    }

    private double calculateIntegral(double A, double B) {
        double res = 0;
        double dx = 0.001;

        functionChart.getXAxis().setAutoRanging(true);
        functionChart.getYAxis().setAutoRanging(true);

        XYChart.Series dataSeries = new XYChart.Series<>();

        while(A < B) {
            res += mathFunction(A) * dx;
            dataSeries.getData().add(new XYChart.Data<String, Double>(Double.toString(A), mathFunction(A)));
            A += dx;
        }

        functionChart.getData().add(dataSeries);
        return res;
    }

    private void fillChart() {

        functionChart.getXAxis().setAutoRanging(true);
        functionChart.getYAxis().setAutoRanging(true);

        XYChart.Series dataSeries = new XYChart.Series<>();
        dataSeries.getData().add(new XYChart.Data<String, Integer>("1", 20));
        dataSeries.getData().add(new XYChart.Data<String, Integer>("2", 50));

        functionChart.getData().add(dataSeries);
    }


    @FXML
    public void setButtonResult(ActionEvent e) {
        double A = Double.parseDouble(textFieldA.getText());
        double B = Double.parseDouble(textFieldB.getText());

        textFieldResult.setText(String.valueOf(calculateIntegral(A, B)));

    }

    @FXML
    public void setButtonClear(ActionEvent e) {
        functionChart.getData().clear();
        textFieldResult.clear();
    }

}
