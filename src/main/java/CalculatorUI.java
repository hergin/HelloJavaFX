import business.Calculator;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Random;

public class CalculatorUI extends Application {

    Calculator theCalculator = new Calculator();

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Calculator");

        GridPane gridPane = new GridPane();


        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Grapefruit", 13),
                        new PieChart.Data("Oranges", 25),
                        new PieChart.Data("Plums", 10),
                        new PieChart.Data("Pears", 22),
                        new PieChart.Data("Apples", 30));
        final PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Imported Fruits");

        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
        //creating the chart
        final LineChart<Number, Number> lineChart =
                new LineChart<Number, Number>(xAxis, yAxis);

        lineChart.setTitle("Stock Monitoring, 2010");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("My portfolio");
        //populating the series with data
        series.getData().add(new XYChart.Data(1, 23));
        series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));
        series.getData().add(new XYChart.Data(6, 36));
        series.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));

        lineChart.getData().add(series);

        gridPane.add(lineChart, 0, 3, 2, 1);
        gridPane.add(pieChart, 0, 4, 2, 1);

        Button plusButton = new Button("Add one more");
        plusButton.setOnAction(actionEvent -> {
            var randomNumber = new Random(System.nanoTime()).nextInt(50);
            series.getData().add(new XYChart.Data(series.getData().size() + 1, randomNumber));
            pieChartData.add(new PieChart.Data("Another fruit", randomNumber));
        });
        plusButton.setPrefSize(100, 50);
        gridPane.add(plusButton, 0, 0, 1, 3);

        Scene mainScene = new Scene(gridPane, 600, 900);
        stage.setScene(mainScene);
        stage.show();
    }

}
