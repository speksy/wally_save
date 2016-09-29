package wallet.main.login;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addButton.setOnMouseClicked(event -> categoriesModel.createParentCategory(addCategoryName.getText(), categoriesListView));
        deleteButton.setOnMouseClicked(event -> categoriesModel.deleteParentCategory(categoriesListView));
        categoriesModel.showCategories(categoriesListView);
        createTableWithColumns();
    }

    private void createTableWithColumns() {
        tableView.setEditable(true);
        Callback<TableColumn<Category, String>, TableCell<Category, String>> cellFactory =
                p -> new EditingCell();
        Callback<TableColumn<Category, String>, TableCell<Category, String>> cellFactoryDouble =
                p -> new EditingCell();
        // second column init
        columnAmount = new TableColumn<>("Amount");
        columnAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        columnAmount.setCellFactory(cellFactoryDouble);
        columnAmount.setOnEditCommit(
                t -> (t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setName(t.getNewValue().toString())
        );
        columnAmount.setEditable(true);
        //first column init
        columnDescription = new TableColumn("Description");
        columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnDescription.setCellFactory(cellFactory);
        columnDescription.setOnEditCommit(
                t -> (t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setName(t.getNewValue())
        );
        columnDescription.setEditable(true);

        categoriesListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Your action here
            tableView.setItems(categoriesModel.getCategories(newValue));
        });

        tableView.getColumns().removeAll(columnAmount, columnDescription);
        tableView.getColumns().addAll(columnAmount, columnDescription);
    }
}
