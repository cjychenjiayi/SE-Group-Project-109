package controller;

// Controller.java
import entity.GenderType;
import entity.PortraitType;
import entity.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import method.FormatBox;
import model.dao.base.DataManager;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class PersonalController extends BasicController implements Initializable {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private ChoiceBox genderChoiceBox;

    @FXML
    private ChoiceBox portraitChoiceBox;


    @FXML
    private Button registerButton;

    @FXML
    public void register(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirm = confirmField.getText();
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String gender = (String) genderChoiceBox.getValue();
        String portrait = (String) portraitChoiceBox.getValue();
        gender = gender.toUpperCase(Locale.ROOT);
        if( gender.matches("PREFER NOT TO TELL"))
            gender = "PREFER_NOT_TO_TELL";
        if (username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            showAlert("Error", "Please enter username and password");
        } else if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            showAlert("Error", "Please enter name, email and phone");
        } else if (!password.equals(confirm)) {
            showAlert("Error", "The two passwords are not equal");
        } else if (!FormatBox.isPhone(phone)) {
            showAlert("Error", "The phone must be all digit with length 11");
        } else if(!FormatBox.isPassword(password)) {
            showAlert("Error", "The password can only contains digit and character with length 6");
        } else if(!FormatBox.isEmail(email)) {
            showAlert("Error", "The email must in correct format");
        }else {

            try {
               UserController userController = DataManager.getInstance().userController;
               userController.insert(username, password, name, email, phone, GenderType.valueOf(gender), PortraitType.valueOf(portrait));
               DataManager.getInstance().update();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            showAlert("Success", "Register successfully");
            Button button = (Button) event.getSource();
            try {
                changePage("Login", "Login.fxml", button);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    public void returnLogin(ActionEvent event) {

        Button button = (Button) event.getSource();
        try {
            changePage("Login", "Login.fxml", button);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> genderOptions = FXCollections.observableArrayList(
                "Male",
                "Female",
                "Prefer not to tell"
        );
        genderChoiceBox.setItems(genderOptions);
        genderChoiceBox.setValue("Male"); // Set the default

        ObservableList<String> portraitOptions = FXCollections.observableArrayList(
                "Dog",
                "Rabbit",
                "Pug",
                "Girl",
                "Pig"
        );
        portraitChoiceBox.setItems(portraitOptions);
        portraitChoiceBox.setValue("Dog"); // Set the default

    }
}
