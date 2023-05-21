package controller;

import entity.Course;
import entity.CourseController;
import entity.CourseType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.dao.base.DataManager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import static entity.CourseType.COMPULSORY;

public class CoursesController extends BasicController implements Initializable {

    @FXML
    private TableView table;

    @FXML
    private ChoiceBox typeChoiceBox;

    @FXML
    private ChoiceBox semesterChoiceBox;



    @FXML
    private void MarkAction(ActionEvent event) {
        try {
            Button button = (Button) event.getSource();
            changePage("Show GPA", "ShowGPA.fxml", button);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleTypeChoiceBoxAction(ActionEvent event) {
        semesterChoiceBox.setValue("All"); // Set another to All
        table.getItems().clear();
        String selectedValue = (String) typeChoiceBox.getValue();
        try {
            DataManager dataManager = DataManager.getInstance();
            CourseController courseController = dataManager.getCourseController();
            ArrayList<Course> courses;
            switch (selectedValue) {
                case "All":
                    courses = courseController.getCourses();
                    break;
                default:
                    ArrayList<CourseType> list = new ArrayList<>();
                    list.add(CourseType.valueOf(selectedValue.toUpperCase()));
                    courses = courseController.getCourseByTypes(list);
                    break;
            }
            ObservableList<Course> data = FXCollections.observableArrayList(
                    courses
            );
            table.setItems(data);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void handleSemesterChoiceBoxAction(ActionEvent event) {

        typeChoiceBox.setValue("All"); // Set another to All
        table.getItems().clear();
        String selectedValue = (String) semesterChoiceBox.getValue();
        try {
            DataManager dataManager = DataManager.getInstance();
            CourseController courseController = dataManager.getCourseController();
            ArrayList<Course> courses;
            switch (selectedValue) {
                case "All":
                    courses = courseController.getCourses();
                    break;
                default:
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(Integer.valueOf(selectedValue));
                    courses = courseController.getCourseBySemesters(list);
                    break;
            }

            ObservableList<Course> data = FXCollections.observableArrayList(
                    courses
            );
            table.setItems(data);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void InsertAction(ActionEvent event) {
        try {
            Button button = (Button) event.getSource();
            changePage("Add New Course", "AddCourse.fxml", button);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn nameCol = new TableColumn("Course Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));

        TableColumn creditCol = new TableColumn("Credit");
        creditCol.setCellValueFactory(new PropertyValueFactory<Course, String>("credit"));

        TableColumn scoreCol = new TableColumn("Score");
        scoreCol.setCellValueFactory(new PropertyValueFactory<Course, String>("score"));


        TableColumn semesterCol = new TableColumn("Semester");
        semesterCol.setCellValueFactory(new PropertyValueFactory<Course, String>("semester"));

        TableColumn typeCol = new TableColumn("Course Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<Course, String>("type"));


        table.getColumns().addAll(nameCol, creditCol, scoreCol, semesterCol, typeCol);

        try {
            DataManager dataManager = DataManager.getInstance();
            CourseController courseController = dataManager.getCourseController();

            ArrayList<Course> courses = courseController.getCourses();
            ObservableList<Course> data = FXCollections.observableArrayList(
                    courses
            );

            table.setItems(data);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ObservableList<String> typeOptions = FXCollections.observableArrayList(
                "All",
                "Compulsory",
                "Profession",
                "Public"
        );
        typeChoiceBox.setItems(typeOptions);
        typeChoiceBox.setValue("All"); // Set the default to All

        ObservableList<String> semesterOptions = FXCollections.observableArrayList(
                "All",
                "1",
                "2",
                "3",
                "4",
                "5",
                "6"
        );
        semesterChoiceBox.setItems(semesterOptions);
        semesterChoiceBox.setValue("All"); // Set the default to All
    }

    @FXML
    public void goUser(ActionEvent event) {
        try {
            Button button = (Button) event.getSource();
            changePage("Me", "UserInformation.fxml", button);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void goExperience(ActionEvent event) {
        try {
            Button button = (Button) event.getSource();
            changePage("Experience", "ExtraClass.fxml", button);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    @FXML
    public void goHonor(ActionEvent event) {
        try {
            Button button = (Button) event.getSource();
            changePage("Portfolio", "Honor.fxml", button);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
