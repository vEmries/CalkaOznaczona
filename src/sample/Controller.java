package sample;

import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;

public class Controller {

    public javafx.scene.control.TextField textFieldA;
    public javafx.scene.control.TextField textFieldB;
    public javafx.scene.control.TextField textFieldN;
    public javafx.scene.control.TextField textFieldFunction;
    public javafx.scene.control.TextField textFieldResult;
    public javafx.scene.control.Button buttonResult;
    public javafx.scene.control.Button buttonClear;
    public javafx.scene.chart.LineChart functionChart;


    private double mathFunction(double x) {
        return 2;
    }

    private double calculateIntegral(double A, double B, int N) {
        functionChart.getXAxis().setAutoRanging(true);
        functionChart.getYAxis().setAutoRanging(true);
        XYChart.Series dataSeries = new XYChart.Series<>();

        double H = (B - A) / N;
        double res = 0.5 * (mathFunction(A) + mathFunction(B));

        for (int i = 1; i < N; i++) {
            double X = A + H * i;
            res += mathFunction(X);
            dataSeries.getData().add(new XYChart.Data<String, Double>(Double.toString(X), mathFunction(X)));
        }

        functionChart.getData().add(dataSeries);
        return res * H;
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
        int N = Integer.valueOf(textFieldN.getText());

        textFieldResult.setText(String.valueOf(calculateIntegral(A, B, N)));

    }

    @FXML
    public void setButtonClear(ActionEvent e) {
        functionChart.getData().clear();
        textFieldResult.clear();
    }

}
