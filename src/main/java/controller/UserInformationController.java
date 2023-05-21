/**
 * @author :Jiayi Chen
 * @version :
 * @description :
 * @date : 2023-05-15 15:13
 */
package controller;// FXMLExampleController.java
import entity.CourseType;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.dao.base.DataManager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserInformationController extends BasicController implements Initializable{

    @FXML
    Text name;

    @FXML
    Text phone;

    @FXML
    Text email;

    @FXML
    Text gender;

    @FXML
    ImageView portrait;

    @FXML
    Button ToCourse, ToExperience, ToHonor;

    @FXML
    Button Logout;

    @FXML
    Button Modify;

    @FXML
    Text encourage;

    public ArrayList<String> encourages;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        User user = DataManager.emptyUser;
        try {
            user = DataManager.getInstance().getUser();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if( user.getUid() == -1 )
            return ;
        name.setText("Name: " + user.getName());
        phone.setText("Phone: " + user.getPhone());
        gender.setText("Gender: " + user.getGender().toString());
        email.setText("Email: " + user.getEmail());
//
        Image image = new Image(user.getPortrait().getPicturePath());
        portrait.setImage(image);

        encourages = new ArrayList<String>();
        encourages.add("Today is also a day to work hard!");
        encourages.add("Every setback is an opportunity to learn and grow stronger.");
        encourages.add("I've achieved so much already, and I'm capable of achieving more!");
        encourages.add("Every small step counts towards reaching your goal.");
        encourages.add("Believe in yourself - you have the power to make a difference.");

        int index = (int)(Math.random()*5-0.01);
        encourage.setText(encourages.get(index));
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
    public void setLogout(ActionEvent event) {
        try{
            Button button = (Button) event.getSource();
            changePage("Login", "Login.fxml", button);
        }catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    public void modify(ActionEvent event) {
        try{
            Button button = (Button) event.getSource();
            changePage("Modify", "ModifyUser.fxml", button);
        }catch (IOException ex) {
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

