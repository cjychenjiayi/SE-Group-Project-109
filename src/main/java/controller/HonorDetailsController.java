package controller;

import entity.Honor;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HonorDetailsController {
    @FXML
    private Label honorName;

    @FXML
    private Label honorInfo;

    @FXML
    private ImageView honorImage;

    public void setHonor(Honor honor) {
        honorName.setText(honor.getName());
        honorInfo.setText(honor.getInfo());
        honorImage.setImage(new Image(honor.getPicpath()));
    }
}
