package sample;

import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.scene.chart.XYChart;

public class Controller {

    public javafx.scene.control.TextField textFieldA;
    public javafx.scene.control.TextField textFieldB;
    public javafx.scene.control.TextField textFieldN;
    public javafx.scene.control.TextField textFieldResult;
    public javafx.scene.control.Button buttonResult;
    public javafx.scene.control.Button buttonClear;
    public javafx.scene.chart.LineChart functionChart;


    private double mathFunction(double x) {
        return Math.sin(x);
    }

    private double calculateIntegral(double A, double B, int N) {
        functionChart.getXAxis().setAutoRanging(true);
        functionChart.getYAxis().setAutoRanging(true);
        XYChart.Series dataSeries = new XYChart.Series<>();

        double result = 0;
        double H = (B - A) / N;

        for (int i = 0; i < N; i++) {
            double X = A + H * i;
            result += mathFunction(X);
            dataSeries.getData().add(new XYChart.Data<String, Double>(Double.toString(X), mathFunction(X)));
        }

        dataSeries.setName("Wykres funkcji w przedziale " + A + " : " + B);

        functionChart.getData().add(dataSeries);
        return result * H;

    }

    @FXML
    public void setButtonResult(ActionEvent e) {
        double A = Double.parseDouble(textFieldA.getText());
        double B = Double.parseDouble(textFieldB.getText());
        int N = Integer.valueOf(textFieldN.getText());

        functionChart.getData().clear();
        textFieldResult.setText(String.valueOf(calculateIntegral(A, B, N)));

    }

    @FXML
    public void setButtonClear(ActionEvent e) {
        functionChart.getData().clear();
        textFieldResult.clear();
    }

}
