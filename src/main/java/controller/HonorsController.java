package controller;

import entity.Honor;
import entity.HonorController;
import entity.HonorType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.dao.base.DataManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static method.FormatBox.isValidURL;

public class HonorsController extends BasicController{

    @FXML
    private TextField searchField;

    @FXML
    private ListView<HBox> listView;

    // Create this as a member variable of HonorController
    private Map<HBox, Honor> honorMap = new HashMap<>();
    @FXML private ToggleButton classFilter;
    @FXML private ToggleButton competitionFilter;
    @FXML private ToggleButton videoFilter;
    @FXML private ToggleButton examFilter;
    @FXML private ToggleButton dailyFilter;
    @FXML private Button searchButton;
    @FXML private Button insertButton;
    private ArrayList<HonorType> filteredHonorTypes = new ArrayList<>();

    @FXML
    private void handleFilterAction() throws IOException {
        filteredHonorTypes.clear();

        if (classFilter.isSelected()) {
            filteredHonorTypes.add(HonorType.CLASS);
            classFilter.setStyle("-fx-background-color: skyblue;");
        } else {
            classFilter.setStyle("-fx-background-color: white;");
        }

        if (competitionFilter.isSelected()) {
            filteredHonorTypes.add(HonorType.COMPETITION);
            competitionFilter.setStyle("-fx-background-color: skyblue;");
        } else {
            competitionFilter.setStyle("-fx-background-color: white;");
        }

        if (videoFilter.isSelected()) {
            filteredHonorTypes.add(HonorType.VIDEO);
            videoFilter.setStyle("-fx-background-color: skyblue;");
        } else {
            videoFilter.setStyle("-fx-background-color: white;");
        }

        if (examFilter.isSelected()) {
            filteredHonorTypes.add(HonorType.EXAM);
            examFilter.setStyle("-fx-background-color: skyblue;");
        } else {
            examFilter.setStyle("-fx-background-color: white;");
        }

        if (dailyFilter.isSelected()) {
            filteredHonorTypes.add(HonorType.DAILY);
            dailyFilter.setStyle("-fx-background-color: skyblue;");
        } else {
            dailyFilter.setStyle("-fx-background-color: white;");
        }

        // Update the table based on the selected filter(s)
        DataManager dataManager = DataManager.getInstance();
        HonorController honorController = dataManager.getHonorController();
        ArrayList<Honor> filteredHonors = honorController.getHonorByTypes(filteredHonorTypes);
        updateTable(filteredHonors);
    }

    @FXML
    private void handleSearchAction() throws IOException {
        String pattern = searchField.getText();
        DataManager dataManager = DataManager.getInstance();
        HonorController honorController = dataManager.getHonorController();
        // Perform search based on the input pattern
        ArrayList<Honor> searchedHonors = honorController.searchByTypesandText(pattern,filteredHonorTypes);
        updateTable(searchedHonors);
    }

    private void updateTable(ArrayList<Honor> honors) {
        listView.getItems().clear();
        for (Honor honor : honors) {
            listView.getItems().add(createHonorBox(honor));
        }
    }


    private void handleInsertAction() {
        // your insert logic here
    }
    @FXML
    public void initialize() throws IOException {
        classFilter.setOnAction(e -> {
            try {
                handleFilterAction();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        competitionFilter.setOnAction(e -> {
            try {
                handleFilterAction();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        videoFilter.setOnAction(e -> {
            try {
                handleFilterAction();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        examFilter.setOnAction(e -> {
            try {
                handleFilterAction();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        dailyFilter.setOnAction(e -> {
            try {
                handleFilterAction();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        searchButton.setOnAction(e -> {
            try {
                handleSearchAction();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        classFilter.setSelected(true);
        competitionFilter.setSelected(true);
        videoFilter.setSelected(true);
        examFilter.setSelected(true);
        dailyFilter.setSelected(true);

        classFilter.setStyle("-fx-background-color: skyblue;");
        competitionFilter.setStyle("-fx-background-color: skyblue;");
        videoFilter.setStyle("-fx-background-color: skyblue;");
        examFilter.setStyle("-fx-background-color: skyblue;");
        dailyFilter.setStyle("-fx-background-color: skyblue;");

        // Wrap the ObservableList in a FilteredList
        DataManager dataManager = DataManager.getInstance();
        HonorController honorController = dataManager.getHonorController();
        ArrayList<Honor> honors = honorController.getHonors();
        updateTable(honors);

        filteredHonorTypes.add(HonorType.COMPETITION);
        filteredHonorTypes.add(HonorType.CLASS);
        filteredHonorTypes.add(HonorType.DAILY);
        filteredHonorTypes.add(HonorType.EXAM);
        filteredHonorTypes.add(HonorType.EXAM);

        // Set the filter Predicate whenever the filter changes

    }

    private HBox createHonorBox(Honor honor) {
        String picturePath = honor.getPicpath();
        File file = new File("src/main/resources/");
        String path = file.getAbsolutePath() + "\\";
        if( isValidURL(honor.getPicpath()))
            path = honor.getPicpath();
        else path = "file:" + (path + honor.getPicpath()).replace("/", "\\");
        ImageView imageView = new ImageView(new Image(path));
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(200); // Adjust as needed
        imageView.setFitWidth(200); // Adjust as needed

        VBox vbox = new VBox();

        Label nameLabel = new Label(honor.getName());
        nameLabel.setFont(new Font("Arial", 30)); // set font name and size
        nameLabel.setStyle("-fx-font-weight: bold"); // make it bold
        vbox.getChildren().add(nameLabel);

        Label infoLabel = new Label(honor.getInfo());
        infoLabel.setFont(new Font("Arial", 20)); // set font name and size
        vbox.getChildren().add(infoLabel);

        HBox hbox = new HBox();
        hbox.getChildren().addAll(imageView, vbox);
        honorMap.put(hbox, honor);
        return hbox;
    }


    @FXML
    private void onSearchButtonClicked() {
        // The filtering is done automatically, so no need to implement anything here
    }



    @FXML
    private void onAddButtonClicked(ActionEvent event) {
        try {
            Button button = (Button) event.getSource();
            changePage("Add New Honor", "AddHonor.fxml", button);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void onListViewClicked() {
        HBox selectedHonorBox = listView.getSelectionModel().getSelectedItem();
        if (selectedHonorBox != null) {
            Honor selectedHonor = honorMap.get(selectedHonorBox);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HonorDetails.fxml"));
                Parent root = loader.load();

                HonorDetailsController controller = loader.getController();
                controller.setHonor(selectedHonor);

                Stage stage = new Stage();
                stage.setTitle("Honor Details");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
    public void goExperience(ActionEvent event) {
        try {
            Button button = (Button) event.getSource();
            changePage("Experience", "ExtraClass.fxml", button);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
