package wallet.main.login;/**
 * Created by sveto on 9/25/2016.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Categories extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Category.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("Category.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
