package wallet.main.login;

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

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        if (connectionModel.isDbConnected()) {
            isConnected.setText("Connected");
        } else {
            isConnected.setText("Not Connected");
        }
    }
    @FXML
    public void RegisterAction(){
        createUser();
    }

    public void createUser() {
        PreparedStatement preparedStatement = null;
        String query = "insert into T_USERS (username,password,email,age) VALUES (?,?,?,?)";

        if (connectionModel.isDbConnected() && regPassword.getText().equals(regPassword2.getText())) {
            try {
                preparedStatement = connectionModel.connection.prepareStatement(query);
                preparedStatement.setString(1, regUsername.getText());
                preparedStatement.setString(2, regPassword.getText());
                preparedStatement.setString(3, regEmail.getText());
                preparedStatement.setString(4, regAge.getText());
                preparedStatement.executeUpdate();
                isConnected.setText("User" + regUsername.getText() + " was created. Please Login.");

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        } else {
            isConnected.setText("Passwords must match.");
        }

    }
}
