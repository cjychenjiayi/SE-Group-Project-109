package controller;

import entity.Honor;
import entity.HonorController;
import entity.HonorType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import method.CopyPaste;
import method.FormatBox.*;
import model.dao.base.DataManager;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static method.FormatBox.isImage;
import static method.FormatBox.isValidURL;
import static method.CopyPaste.copyFile;

public class AddHonorController extends BasicController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField infoField;

    @FXML
    private TextField picPathField;

    @FXML
    private ComboBox<HonorType> typeField;

    @FXML
    private DatePicker dateField;

    public static LocalDate Today = LocalDate.now();

    private HonorsController honorsController;

    public void setHonorController(HonorsController honorsController) {
        this.honorsController = honorsController;
    }

    @FXML
    public void initialize() {
        // Initialize the type ComboBox with HonorType values
        typeField.getItems().addAll(HonorType.values());
        typeField.setValue(HonorType.CLASS);
        dateField.setValue(Today);
    }

    @FXML
    private void onAddButtonClicked() throws IOException {
        // Convert the local date to util date
        Date date = Date.from(dateField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        String name = nameField.getText(), info = infoField.getText(), picPath = picPathField.getText();
        if( name.isEmpty() || info.isEmpty() || picPath.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Name, description and the path of the picture cannot be empty!!");
            alert.showAndWait();
            return;
        }
        File file = new File(picPath);
        if((!file.exists() && !isValidURL(picPath) ) || !isImage(picPath)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("The picture does not exist!! Either a path in Local or a path from internet!");
            alert.showAndWait();
            return;
        }
        String realPath = picPath;
        if( !isValidURL(picPath) )
            realPath = copyFile(picPath);
        HonorController honorController = DataManager.getInstance().getHonorController();
        honorController.insert( name, info, realPath, typeField.getValue(),  date);
        DataManager.getInstance().update();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("OK");
        alert.setHeaderText("Success");
        alert.setContentText("Add honor successfully!");

        alert.showAndWait();

        nameField.setText("");
        infoField.setText("");
        picPathField.setText("");
        typeField.setValue(HonorType.CLASS); // Set the default to Compulsory
    }

    @FXML
    public void exitButtonAction(ActionEvent event) {
        try {
            Button button = (Button) event.getSource();
            changePage("Honor", "Honor.fxml", button);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
