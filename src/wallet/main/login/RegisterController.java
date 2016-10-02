package wallet.main.login;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCursor;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    RegisterModel connectionModel = new RegisterModel();
    @FXML
    private TextField regUsername;
    @FXML
    private TextField regPassword;
    @FXML
    private TextField regPassword2;
    @FXML
    private TextField regEmail;
    @FXML
    private TextField regAge;
    @FXML
    private Button regButton;
    @FXML
    private Label isConnected;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void RegisterAction() {
        try {
            createUser();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createUser() throws IOException {
        // 1. Create user
        BasicDBObject user_info = new BasicDBObject();
        user_info.put("username", regUsername.getText());
        user_info.put("password", regPassword.getText());
        user_info.put("email", regEmail.getText());
        user_info.put("age", regAge.getText());
        user_info.put("isLoggedIn", false );
        List<DBObject> userInfoList = new ArrayList<>();
        userInfoList.add(user_info);
        connectionModel.getCollection().insertMany(userInfoList);
        closeCurrentWindow();
        openLoginScreen();

    }
    private void closeCurrentWindow(){
        Window window = regUsername.getScene().getWindow();

        if (window instanceof Stage){
            ((Stage) window).close();
        }
    }
    private void openLoginScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Stage primaryStage = new Stage();
        Parent root = fxmlLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
