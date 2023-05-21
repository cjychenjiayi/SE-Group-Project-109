/**
 * @author :Jiayi Chen
 * @version :
 * @description :
 * @date : 2023-05-09 13:47
 */
package controller;

import entity.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import lombok.SneakyThrows;
import method.FormatBox;
import model.dao.base.DataManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController extends BasicController implements Initializable {
    @FXML
    public Button Login;

    @FXML
    public Button Register;
    @FXML
    public TextField Username;
    @FXML
    public PasswordField Password;
    public Text UsernameException;
    public Text PasswordException;

    public void Register() throws IOException {
        changePage("New User", "Personal.fxml", Register);
    }

    public void Login() throws IOException {

        String username = Username.getText();
        String password = Password.getText();
        UserController userController = DataManager.getInstance().userController;
        Boolean pwdformat = FormatBox.isPassword(password), nameformat = FormatBox.isString(username);
        int curr_uid = -3; // -3 for format error, -2 for no username, -1 for wrong password
        if( nameformat && pwdformat ) {
            curr_uid = userController.getUid(username, password);
        }

        UsernameException.setVisible(false);
        PasswordException.setVisible(false);
        switch (curr_uid){
            case -3:
                if( !nameformat ) {
                    UsernameException.setVisible(true);
                    if( username == "" )
                        UsernameException.setText("Username cannot be empty");
                    else
                        UsernameException.setText("Wrong format, Username accept only digit and character");
                }

                if( !pwdformat ) {
                    PasswordException.setVisible(true);
                    if(password == "" )
                        PasswordException.setText("Password cannot be empty");
                    else
                        PasswordException.setText("Wrong format of password or incorrect length");
                }
                break;
            case -2:
                UsernameException.setVisible(true);
                UsernameException.setText("User not found, please check your User ID");
                new Alert(Alert.AlertType.ERROR,
                        "User not found, please check your User ID.").showAndWait();
                break;
            case -1:
                PasswordException.setVisible(true);
                PasswordException.setText("Wrong password");
                break;
            default:
                DataManager.getInstance().set_uid(curr_uid);
                changePage("Courses", "Course.fxml", Login);
                break;
        }
    }

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        System.out.println(DataManager.getInstance().userController);
    }
}
