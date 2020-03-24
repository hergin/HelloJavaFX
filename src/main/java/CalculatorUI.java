import business.Calculator;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CalculatorUI extends Application implements EventHandler<ActionEvent> {

    Calculator theCalculator = new Calculator();

    Label displayLabel = new Label(theCalculator.getMainNumber());

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Calculator");

        GridPane gridPane = new GridPane();


        displayLabel.setFont(new Font(20));
        displayLabel.setPadding(new Insets(20));
        displayLabel.setPrefSize(200, 100);
        gridPane.add(displayLabel, 0, 0, 2, 1);

        Button button0 = new Button("0");
        button0.setOnAction(value -> {
            theCalculator.appendToMainNumber("0");
            displayLabel.setText(theCalculator.getMainNumber());
        });
        button0.setPrefSize(100, 100);
        gridPane.add(button0, 0, 1, 1, 1);

        Button button1 = new Button("1");
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                theCalculator.appendToMainNumber("1");
                displayLabel.setText(theCalculator.getMainNumber());
            }
        });
        button1.setPrefSize(100, 100);
        gridPane.add(button1, 1, 1, 1, 1);

        Button plusButton = new Button("+");
        plusButton.setOnAction(this);
        plusButton.setPrefSize(100, 100);
        gridPane.add(plusButton, 0, 2, 1, 1);

        Button equalButton = new Button("=");
        equalButton.setOnAction(this);
        equalButton.setPrefSize(100, 100);
        gridPane.add(equalButton, 1, 2, 1, 1);

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Grapefruit", 13),
                        new PieChart.Data("Oranges", 25),
                        new PieChart.Data("Plums", 10),
                        new PieChart.Data("Pears", 22),
                        new PieChart.Data("Apples", 30));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Imported Fruits");
        gridPane.add(chart,0,3,2,1);

        Scene mainScene = new Scene(gridPane, 300, 600);
        stage.setScene(mainScene);
        stage.show();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (((Button) actionEvent.getSource()).getText().equals("=")) {
            theCalculator.add(displayLabel.getText());
            displayLabel.setText(theCalculator.getMainNumber());
        } else {
            theCalculator.storeAndResetMainNumber();
            displayLabel.setText(theCalculator.getMainNumber());
        }
    }
}
