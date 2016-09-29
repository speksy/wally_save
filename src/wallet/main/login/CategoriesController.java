package wallet.main.login;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by sveto on 9/26/2016.
 */
public class CategoriesController implements Initializable {
    private CategoriesModel categoriesModel = new CategoriesModel();
    private TableColumn<Category, String> columnDescription;
    private TableColumn<Category, String> columnAmount;

    @FXML
    private ListView<String> categoriesListView;
    @FXML
    private TableView<Category> tableView = new TableView<>();
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TextField addCategoryName;
    @FXML
    private static Label userLbl;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addButton.setOnMouseClicked(event -> categoriesModel.createParentCategory(addCategoryName.getText(), categoriesListView));
        deleteButton.setOnMouseClicked(event -> categoriesModel.deleteParentCategory(categoriesListView));
        categoriesModel.showUserParentCategories(categoriesListView);
        categoriesModel.createTableWithColumns(tableView, columnAmount, columnDescription, categoriesListView);
    }

    public void GetUsername(String user) {
       userLbl.setText("Hi " + user);
    }
}
