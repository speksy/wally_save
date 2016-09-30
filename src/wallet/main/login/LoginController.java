package wallet.main.login;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by sveto on 9/25/2016.
 */
public class LoginController implements Initializable {
    public LoginModel loginModel = new LoginModel();
    public CategoriesModel categoriesModel = new CategoriesModel();
    @FXML
    private Label isConnected;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (loginModel.isDbConnected()) {
            isConnected.setText("Please enter username and password:");
        } else {
            isConnected.setText("Not Connected");
        }
    }

    @FXML
    public void LoginAction() throws IOException {
        try {
            if (loginModel.isLogin(txtUsername.getText(), txtPassword.getText())) {
                setTxtUsername(txtUsername);
                Parent root = null;
                FXMLLoader loader2 = new FXMLLoader(getClass().getResource("Category.fxml"));
                try {
                    // TODO - this load the Category.fxml and next set user name cannot be set because init phase is completed
                    root = loader2.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                CategoriesController categoriesController = loader2.getController();
                categoriesController.setUserLbl(getTxtUsername());
                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("Category.css").toExternalForm());
                Stage primaryStage = new Stage();
                primaryStage.setScene(scene);
                primaryStage.show();
            } else {
                isConnected.setText("Incorrect username or password. Try again: ");
            }
        } catch (SQLException e) {
            isConnected.setText("username and password is NOT correct");
            e.printStackTrace();
        }

    }
    public void setTxtUsername(TextField txtUsername) {
        this.txtUsername = txtUsername;
    }

    public String getTxtUsername() {
        return txtUsername.getText();
    }
    @FXML
    public void OpenAccountCreation() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Stage primaryStage = new Stage();
        Parent root = fxmlLoader.load(getClass().getResource("Register.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("Register.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
