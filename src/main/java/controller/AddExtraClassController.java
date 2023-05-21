package controller;

import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.dao.base.DataManager;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class AddExtraClassController extends BasicController implements Initializable {
    @FXML
    public TextField nameField;

    @FXML
    public TextField roleField;

    @FXML
    public TextField descriptionField;

    @FXML
    public DatePicker startDateField;

    @FXML
    public DatePicker endDateField;

    @FXML
    public ChoiceBox typeChoiceBox;

    public static LocalDate Today = LocalDate.now();

    public void exitButtonAction(ActionEvent event) {
        try {
            Button button = (Button) event.getSource();
            changePage("Extra Class", "ExtraClass.fxml", button);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void onAddButtonClicked(ActionEvent actionEvent) throws IOException {
        Date startDate = Date.from(startDateField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(endDateField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        String name = nameField.getText(), roles = roleField.getText(), description = descriptionField.getText();
        if( name.isEmpty() || roles.isEmpty() || description.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("FAIL ADDING EXPERIENCE");
            alert.setContentText("The name, role and description cannot be empty");
            alert.showAndWait();
            return;
        }
        if( startDate.compareTo(endDate) > 0 ) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("FAIL ADDING EXPERIENCE");
            alert.setContentText("The enddate must not be earlier than startdate!");
            alert.showAndWait();
            return;
        }
        Experience experience =  new Experience(startDate, endDate, name, roles, description);
        TotExperience totExperience = DataManager.getInstance().getExperience();
        ExtraClass extraClass = totExperience.getExtraClass();
        Role role = totExperience.getRole();
        Skill skill = totExperience.getSkill();
        String type = (String)typeChoiceBox.getValue();
        switch (type){
            case "Projects":
                extraClass.AddProjectJson(experience);
                break;
            case "Interns":
                extraClass.AddInternJson(experience);
                break;
            case "Class":
                role.AddClassesJson(experience);
                break;
            case "School":
                role.AddSchoolJson(experience);
                break;
            case "Volunteer":
                role.AddVolunteersJson(experience);
                break;
            case "Skills":
                skill.AddSkillJson(experience);
                break;
        }
        DataManager.getInstance().update();
        nameField.setText("");
        roleField.setText("");
        descriptionField.setText("");
        typeChoiceBox.setValue("Projects");
        startDateField.setValue(Today);
        startDateField.setValue(Today);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("OK");
        alert.setHeaderText("Success");
        alert.setContentText("Add Experience with the type: " + type + " successfully!");
        alert.showAndWait();


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> typeOptions = FXCollections.observableArrayList(
                "Projects",
                "Interns",
                "Class",
                "School",
                "Volunteer",
                "Skills"
        );
        typeChoiceBox.setItems(typeOptions);
        typeChoiceBox.setValue("Projects"); // Set the default to Compulsory
        startDateField.setValue(Today);
        endDateField.setValue(Today);
    }
}
