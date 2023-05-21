/**
 * @author :Jiayi Chen
 * @version :
 * @description :
 * @date : 2023-05-09 18:49
 */
package main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/AddCompetition.fxml")));
        stage.setTitle("group109");
        Scene scene = new Scene(root, 900, 645);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
