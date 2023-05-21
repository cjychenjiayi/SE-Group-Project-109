package controller;// FXMLExampleController.java
import entity.Course;
import entity.CourseType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.dao.base.DataManager;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class AddCourseController extends BasicController implements Initializable {

    @FXML
    TextField name;

    @FXML
    TextField credit;

    @FXML
    TextField score;

    @FXML
    ChoiceBox semester;

    @FXML
    ChoiceBox typeChoiceBox;



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

    @FXML
    private void add(ActionEvent event) {
        String name_str = name.getText();
        String credit_str = credit.getText();
        String score_str = score.getText();
        String semester_str = (String)semester.getValue();
        boolean judge_ok = name_str.isEmpty() || credit_str.isEmpty() || score_str.isEmpty() || semester_str.isEmpty();
        if(judge_ok) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please input all the information!");
            alert.showAndWait();
            return;
        }

        // Judge credit is Double
        try {
            Double.parseDouble(credit_str);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Credit should be Double type!");
            alert.showAndWait();
            return;
        }

        try {
            Integer.parseInt(score_str);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
//            alert.setHeaderText("Look, an Error Dialog");
            alert.setContentText("Score should be Integer type!");

            alert.showAndWait();
            return;
        }

        if(Integer.valueOf(score_str) > 100 ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Score should not be bigger than 100");
            alert.showAndWait();
            return;
        }

        try {
            Integer.parseInt(semester_str);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Semester should be Integer type!");
            alert.showAndWait();
            return;
        }


        try {
            DataManager dataManager = DataManager.getInstance();
            dataManager.getCourseController().addCourse(name_str, Double.valueOf(credit_str), Integer.valueOf(score_str), Integer.valueOf(semester_str), CourseType.valueOf(((String)typeChoiceBox.getValue()).toUpperCase().replace(" ", "_")));
            dataManager.update();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("OK");
            alert.setHeaderText("Success");
            alert.setContentText("Add course successfully!");

            alert.showAndWait();

            name.setText("");
            credit.setText("");
            score.setText("");
            semester.setValue("1");
            typeChoiceBox.setValue("Compulsory"); // Set the default to Compulsory

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<String> typeOptions = FXCollections.observableArrayList(
                "Compulsory",
                "Profession",
                "Public"
        );
        typeChoiceBox.setItems(typeOptions);
        typeChoiceBox.setValue("Compulsory"); // Set the default to Compulsory

        ObservableList<String> semesterOptions = FXCollections.observableArrayList(
                "1",
                "2",
                "3",
                "4",
                "5",
                "6"
        );
        semester.setItems(semesterOptions);
        semester.setValue("1"); // Set the default to Compulsory

    }
}
