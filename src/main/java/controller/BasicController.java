/**
 * @author :Jiayi Chen
 * @version :
 * @description :
 * @date : 2023-05-09 14:50
 */
package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class BasicController {
    @FXML
    public Button NextBtn;
    @FXML
    public Button BackBtn;
    @FXML
    public Button MainBtn;

    // change pages
    public void changePage(String newTitle, String fxmlName, Button button) throws IOException {

        AnchorPane page = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/" + fxmlName)));
        Stage signUpStage = (Stage)button.getScene().getWindow();
        signUpStage.setTitle(newTitle);
        Scene scene = new Scene(page, 900, 650);
        signUpStage.setScene(scene);
    }

    public void goMain() throws IOException{
        this.changePage("Main Page", "Home.fxml", MainBtn);
    }

    public void Back(String newTitle, String fxmlName) throws IOException {
        this.changePage(newTitle, fxmlName, this.MainBtn);
    }

    public void Next(String newTitle, String fxmlName) throws IOException {
        this.changePage(newTitle, fxmlName, this.MainBtn);
    }

}
