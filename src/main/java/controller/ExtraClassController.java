package controller;

import entity.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.dao.base.DataManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExtraClassController extends BasicController{

    @FXML
    private TableView<Competition> table1;
    @FXML
    private TableView<Experience> table2;
    @FXML
    private TableView<Experience> table3;
    @FXML
    private TableView<Experience> table11;
    @FXML
    private TableView<Experience> table12;
    @FXML
    private TableView<Experience> table13;
    @FXML
    private TableView<Experience> table21;

    @FXML
    private Text typeText;

    @FXML
    private ChoiceBox<String> typeChoiceBox;

    @FXML
    private Button ToCourse, ToUser, ToHonor;

    @FXML
    public void initialize() throws IOException {
//         Initialize data for demonstration
        DataManager dataManager = DataManager.getInstance();
        TotExperience totExperience = dataManager.getExperience();


        ArrayList<Competition> competitions = totExperience.getExtraClass().getCompetitions();
        ArrayList<Experience> projects = totExperience.getExtraClass().getProjects();
        ArrayList<Experience> interns = totExperience.getExtraClass().getInterns();
        ArrayList<Experience> classes = totExperience.getRole().getClasses();
        ArrayList<Experience> school = totExperience.getRole().getSchool();
        ArrayList<Experience> volunteer = totExperience.getRole().getVolunteer();
        ArrayList<Experience> skill = totExperience.getSkill().getSkills();


        // fill tables with data
        table1.setItems(FXCollections.observableArrayList(competitions));
        table2.setItems(FXCollections.observableArrayList(projects));
        table3.setItems(FXCollections.observableArrayList(interns));
        table11.setItems(FXCollections.observableArrayList(classes));
        table12.setItems(FXCollections.observableArrayList(school));
        table13.setItems(FXCollections.observableArrayList(volunteer));
        table21.setItems(FXCollections.observableArrayList(skill));


        table1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table2.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table3.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table11.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table12.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table13.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table21.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Competition, String> nameColumn = new TableColumn<>("Competition");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Competition, String> awardColumn = new TableColumn<>("award");
        awardColumn.setCellValueFactory(new PropertyValueFactory<>("award"));
        table1.getColumns().add(nameColumn);
        table1.getColumns().add(awardColumn);

        TableColumn<Experience, String> nameColumn2 = new TableColumn<>("Project");
        nameColumn2.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Experience, String> desColumn = new TableColumn<>("Description");
        desColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        table2.getColumns().add(nameColumn2);
        table2.getColumns().add(desColumn);


        TableColumn<Experience, String> nameColumn3 = new TableColumn<>("Intern");
        nameColumn3.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Experience, String> desColumn2 = new TableColumn<>("Description");
        desColumn2.setCellValueFactory(new PropertyValueFactory<>("description"));
        table3.getColumns().add(nameColumn3);
        table3.getColumns().add(desColumn2);

        TableColumn<Experience, String> nameColumn11 = new TableColumn<>("Class");
        nameColumn11.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Experience, String> desColumn11 = new TableColumn<>("Description");
        desColumn11.setCellValueFactory(new PropertyValueFactory<>("description"));
        table11.getColumns().add(nameColumn11);
        table11.getColumns().add(desColumn11);

        TableColumn<Experience, String> nameColumn12 = new TableColumn<>("School");
        nameColumn12.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Experience, String> desColumn12 = new TableColumn<>("Description");
        desColumn12.setCellValueFactory(new PropertyValueFactory<>("description"));
        table12.getColumns().add(nameColumn12);
        table12.getColumns().add(desColumn12);

        TableColumn<Experience, String> nameColumn13 = new TableColumn<>("Volunteer");
        nameColumn13.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Experience, String> desColumn13 = new TableColumn<>("Description");
        desColumn13.setCellValueFactory(new PropertyValueFactory<>("description"));
        table13.getColumns().add(nameColumn13);
        table13.getColumns().add(desColumn13);

        TableColumn<Experience, String> nameColumn21 = new TableColumn<>("Skills");
        nameColumn21.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Experience, String> desColumn21 = new TableColumn<>("Description");
        desColumn21.setCellValueFactory(new PropertyValueFactory<>("description"));
        table21.getColumns().add(nameColumn21);
        table21.getColumns().add(desColumn21);
    }



    @FXML
    private void ExperienceAction(ActionEvent event) {
        try {
            Button button = (Button) event.getSource();
            changePage("Add Experience", "AddExtraClass.fxml", button);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void CompetitionAction(ActionEvent event) {
        try {
            Button button = (Button) event.getSource();
            changePage("Add Competition", "AddCompetition.fxml", button);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void onTable1Clicked(MouseEvent event) {
        clearTable(table1);
        if (event.getClickCount() == 2) { // Double-click detected
            Competition competition = table1.getSelectionModel().getSelectedItem();
            if (competition != null) {
                showDialog(competition);
            }
        }
    }
    private void clearTable(TableView clickedTable) {
        // List of all tables
        List<TableView> allTables = Arrays.asList(table1, table2, table3, table11, table12, table13, table21);

        // Clear selection for all tables except the clicked one
        for (TableView table : allTables) {
            if (table != clickedTable) {
                table.getSelectionModel().clearSelection();
            }
        }
    }

    private void showDialog(Competition competition) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Competition Details");
        alert.setHeaderText(competition.getName());

        StringBuilder content = new StringBuilder();
        content.append("Year: ").append(competition.getYear()).append("\n");
        content.append("Name: ").append(competition.getName()).append("\n");
        content.append("Award: ").append(competition.getAward());

        alert.setContentText(content.toString());

        alert.showAndWait();
    }

    public TableView<Experience> lastSelected;
    public void onTable2Clicked(MouseEvent event) {
        TableView<Experience> table = (TableView<Experience>) event.getSource();
        clearTable(table);
        if (event.getClickCount() == 2) { // Double-click detected
            Experience project = table.getSelectionModel().getSelectedItem();
            if (project != null) {
                showDialog(project);
            }
        }
    }

    private void showDialog(Experience experience) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Experience Details");
        alert.setHeaderText(experience.getName());

        StringBuilder content = new StringBuilder();
        content.append("Start Date: ").append(experience.getStartDate()).append("\n");
        content.append("End Date: ").append(experience.getEndDate()).append("\n");
        content.append("Name: ").append(experience.getName()).append("\n");
        content.append("Role: ").append(experience.getRole()).append("\n");
        content.append("Description: ").append(experience.getDescription());

        alert.setContentText(content.toString());

        alert.showAndWait();
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
    public void goCourse(ActionEvent event) {
        try {
            Button button = (Button) event.getSource();
            changePage("Course", "Course.fxml", button);
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
