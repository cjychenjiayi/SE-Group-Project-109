package controller;

// Controller.java
import entity.GenderType;
import entity.PortraitType;
import entity.User;
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

public class ModifyUserController extends BasicController implements Initializable {


    @FXML
    private Label uid;
    @FXML
    private Label username;

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
    public void modify(ActionEvent event) {
//        String username = usernameField.getText();
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

       if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            showAlert("Error", "Please enter name, email and phone");
        } else if (!password.equals(confirm)) {
            showAlert("Error", "The two passwords are not equal");
        } else if(!FormatBox.isPassword(password)) {
           showAlert("Error", "The password can only contains digit and character with length 6");
        }  else if(!FormatBox.isEmail(email)) {
           showAlert("Error", "The email must in correct format");
        } else if (!FormatBox.isPhone(phone)) {
            showAlert("Error", "The phone must be all digit with length 11");
        }else {

            try {
                DataManager dataManager = DataManager.getInstance();
                User user = dataManager.getUser();
                user.setPassword(passwordField.getText());
                user.setName(nameField.getText());
                user.setEmail(emailField.getText());
                user.setPhone(phoneField.getText());
                user.setGender(GenderType.valueOf(gender));
                user.setPortrait(PortraitType.valueOf(portrait));
                dataManager.update();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            showAlert("Success", "Modify successfully");
            Button button = (Button) event.getSource();
            try {
                changePage("Me", "UserInformation.fxml", button);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    public void returnMe(ActionEvent event) {
        Button button = (Button) event.getSource();
        try {
            changePage("Me", "UserInformation.fxml", button);
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

        try {
            DataManager dataManager = DataManager.getInstance();
            uid.setText(Integer.valueOf(dataManager.getUser().getUid()).toString());
            username.setText(dataManager.getUser().getUserName());
            nameField.setText(dataManager.getUser().getName());
            emailField.setText(dataManager.getUser().getEmail());
            phoneField.setText(dataManager.getUser().getPhone());
            passwordField.setText(dataManager.getUser().getPassword());
            confirmField.setText(dataManager.getUser().getPassword());

            String genderText = dataManager.getUser().getGender().toString().replace("_", " ");
            genderChoiceBox.setValue(genderText.substring(0, 1).toUpperCase() + genderText.substring(1).toLowerCase());

            String portraitText = dataManager.getUser().getPortrait().toString().replace("_", " ");
            portraitChoiceBox.setValue(portraitText.substring(0, 1).toUpperCase() + portraitText.substring(1).toLowerCase());


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ObservableList<String> genderOptions = FXCollections.observableArrayList(
                "Male",
                "Female",
                "Prefer not to tell"
        );
        genderChoiceBox.setItems(genderOptions);
        ObservableList<String> portraitOptions = FXCollections.observableArrayList(
                "Dog",
                "Rabbit",
                "Pug",
                "Girl",
                "Pig"
        );
        portraitChoiceBox.setItems(portraitOptions);
    }
}
