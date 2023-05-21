package controller;

import entity.Award;
import entity.Competition;
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

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class AddCompetitionController extends BasicController implements Initializable {
    @FXML
    public TextField yearField;
    public TextField nameField;
    public ChoiceBox awardField;

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
        String year = yearField.getText(), name = nameField.getText();
        Award award = Award.valueOf((String)  awardField.getValue());

        if( year.isEmpty()||name.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("FAIL ADDING COMPETITION");
            alert.setContentText("The name and year cannot be empty");
            alert.showAndWait();
            return;
        }

        int intyear;
        try{
            intyear = Integer.valueOf(year);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("FAIL ADDING COMPETITION");
            alert.setContentText("The year must be an integer");
            alert.showAndWait();
            return;
        }
        yearField.setText("");
        nameField.setText("");
        Competition competition = new Competition(intyear, name, award);
        DataManager.getInstance().getExperience().getExtraClass().AddCompetitionJson(competition);
        DataManager.getInstance().update();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("OK");
        alert.setHeaderText("Success");
        alert.setContentText("Add competition successfully!");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> typeOptions = FXCollections.observableArrayList(
                "GRAND",
                "FIRST",
                "SECOND",
                "THIRD"
        );
        awardField.setItems(typeOptions);
        awardField.setValue("GRAND"); // Set the default to Compulsory
    }
}
