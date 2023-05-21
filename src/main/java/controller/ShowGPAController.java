package controller;

import entity.Statistics;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.dao.base.DataManager;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ShowGPAController extends BasicController implements Initializable {

    @FXML
    private Text gpa_text;

    @FXML
    private Text credit_text;

    @FXML
    private Text avg_score_text;

    @FXML
    private Text degree_text;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis numberYAxis;

    @FXML
    private LineChart lineChart;

    @FXML
    private void exitButtonAction(ActionEvent event) {
        try {
            Button button = (Button) event.getSource();
            changePage("Courses", "Course.fxml", button);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // set axis label
        xAxis.setLabel("Semester");
        numberYAxis.setLabel("GPA");

        try {
            DataManager dataManager = DataManager.getInstance();

            Statistics statistics = dataManager.getCourseController().getStatistics();
            gpa_text.setText(String.format("%.2f", statistics.getGpa()) + "/4.0");
            credit_text.setText(String.format("%.2f", statistics.getTotalCredit()));
            avg_score_text.setText(String.format("%.2f", statistics.getAvgScore()));
            degree_text.setText("First Class");
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("GPA");
            for(int i = 1; i <= 6; ++i) {
                Double gpa = dataManager.getCourseController().calculate(i).getGpa();
                if(gpa.isNaN()) {
                    gpa = 0.0;
                }
                series.getData().add(new XYChart.Data<>(Integer.valueOf(i).toString(), gpa));
            }
            lineChart.getData().add(series);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}