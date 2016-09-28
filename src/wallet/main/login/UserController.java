package wallet.main.login;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by sveto on 9/25/2016.
 */
public class UserController implements Initializable {
    @FXML
    private Label userLbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initialize(String user) {
        userLbl.setText("Hi, " + user);
    }
}
