package wallet.main.login;


import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.client.MongoCursor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import org.bson.Document;
import org.sqlite.SQLiteConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by sveto on 9/26/2016.
 */
public class CategoriesController implements Initializable {
    private CategoriesModel categoriesModel = new CategoriesModel();
    private TableColumn<Category, String> columnDescription;
    private TableColumn<Category, String> columnAmount;

    private String username;
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
    private Label userLbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getUser();
        userLbl.setText("Hi " + username);
        showUserParentCategories(username,categoriesListView);

        addButton.setOnMouseClicked(event -> createParentCategory(addCategoryName.getText()));
        deleteButton.setOnMouseClicked(event -> deleteParentCategory());
        createTableWithColumns();
    }

    public void getUser(){
        BasicDBObject query;
        query = new BasicDBObject("isLoggedIn", true);
        BasicDBObject query2 = new BasicDBObject("username", 1);
        MongoCursor cursor = categoriesModel.getCollection().find(query).projection(new Document(query2)).iterator();
        while (cursor.hasNext()) {
            BasicDBObject userValue = (BasicDBObject) cursor.next();
            username = userValue.getString("username");
            System.out.println(username);
        }
    }
    public void setUserLbl(String username) {
        this.userLbl.setText(username);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void showUserParentCategories(String user, ListView<String> categoriesListView) {
//        ObservableList<String> data = FXCollections.observableArrayList();
//        PreparedStatement preparedStatement;
//        ResultSet resultSet;
//        String query = "SELECT category_name from t_categories where user = ?";
//        try {
//            preparedStatement = categoriesModel.connection.prepareStatement(query);
//            preparedStatement.setString(1, user);
//            resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                String substringRow;
//                List row = new ArrayList();
//                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
//                    row.add(resultSet.getString(i));
//                }
//                substringRow = row.toString().replaceAll("[\\[\\]]", "").replaceAll(",", " ");
//                data.add(substringRow);
//            }
//            categoriesListView.getItems().clear();
//            categoriesListView.getItems().addAll(data);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
    //Add new category
    public void createParentCategory(String entry) {
//        PreparedStatement preparedStatement = null;
//        PreparedStatement preparedStatement2 = null;
//        String superCategoryQuery = "INSERT INTO t_categories (category_name, USER) VALUES (?,?)";
//        String subCategoryQuery = "INSERT INTO t_cat_reg (name,user) VALUES (?,?)";
//        try {
//            preparedStatement = categoriesModel.connection.prepareStatement(superCategoryQuery);
//            preparedStatement2 = categoriesModel.connection.prepareStatement(subCategoryQuery);
//            if (!categoriesModel.hasCategory(entry)) {
//                if (!entry.equals("")) {
//                    preparedStatement.setString(1, entry);
//                    preparedStatement.setString(2, getLoggedUsername());
//                    preparedStatement2.setString(1, entry);
//                    preparedStatement2.setString(2, getLoggedUsername());
//                } else {
//                    System.out.print("Empty category.");
//                }
//                preparedStatement.executeUpdate();
//                preparedStatement2.executeUpdate();
//
//
//                categoriesListView.getItems().clear();
//                showUserParentCategories(username,categoriesListView);
//            } else {
//                System.out.println("Already exists.");
//            }
//        } catch (SQLException e) {
//            try {
//                preparedStatement.close();
//            } catch (SQLException e1) {
//                e1.printStackTrace();
//            }
//        }
    }

    //Delete category button clicked
    public void deleteParentCategory() {
//        PreparedStatement preparedStatement;
//        String query = "DELETE category_name FROM t_categories where category_name = ? and user = ?";
//        String query2 = "DELETE name FROM t_cat_reg where name = ? and user = ?";
//        String selected = categoriesListView.getSelectionModel().getSelectedItem();
//        try {
//            preparedStatement = categoriesModel.connection.prepareStatement(query);
//            preparedStatement.setString(1, selected);
//            preparedStatement.setString(2, getLoggedUsername());
//            preparedStatement.executeUpdate();
//            preparedStatement = categoriesModel.connection.prepareStatement(query2);
//            preparedStatement.setString(1, selected);
//            preparedStatement.setString(2, getLoggedUsername());
//
//            System.out.println("Selected" + selected);
//            preparedStatement.executeUpdate();
//
//            //Clear the list
//            categoriesListView.getItems().clear();
//            showUserParentCategories(username, categoriesListView);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public ObservableList<Category> getCategories(String value) {
        ObservableList<Category> categories = FXCollections.observableArrayList();
//        ObservableList<String> data = FXCollections.observableArrayList();
//        PreparedStatement preparedStatement;
//        ResultSet rs;
//        String query = "SELECT name,amount,desc from t_cat_reg where name = ? AND amount IS NOT NULL and user = ?";
//        String selected = value;
//        try {
//            preparedStatement = categoriesModel.connection.prepareStatement(query);
//            preparedStatement.setString(1, selected);
//            preparedStatement.setString(2, getLoggedUsername());
//            rs = preparedStatement.executeQuery();
//
//            while (rs.next()) {
//                //Iterate Row
//                ObservableList<String> row = FXCollections.observableArrayList();
//                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
//                    //Iterate Column
//                    row.add(rs.getString(i));
//                }
//                data.add(row.toString());
//
//            }
//
//            List items = data;
//
//            items.forEach(item -> {
//                System.out.println(item);
//                String parts[] = item.toString().replaceAll("[\\[\\]]", "").replaceAll(",", "").split("\\s+");
//                String name = selected;
//                String description = parts[2];
//                String amount = parts[1];
//                Category category = null;
//
//                switch (parts.length) {
//                    case 2:
//                        category = new Category(name, amount);
//                        break;
//                    case 3:
//                        category = new Category(name, amount, description);
//                        break;
//                    default:
//                        break;
//                }
//
//                if (category != null) {
//                    categories.add(category);
//                }
//            });
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Error on Building Data");
//        }
        return categories;
    }

    public void createTableWithColumns() {
//        tableView.setEditable(true);
//        Callback<TableColumn<Category, String>, TableCell<Category, String>> cellFactory =
//                p -> new EditingCell();
//        Callback<TableColumn<Category, String>, TableCell<Category, String>> cellFactoryDouble =
//                p -> new EditingCell();
//        // second column init
//        columnAmount = new TableColumn<>("Amount");
//        columnAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
//        columnAmount.setCellFactory(cellFactoryDouble);
//        columnAmount.setOnEditCommit(
//                t -> (t.getTableView().getItems().get(
//                        t.getTablePosition().getRow())
//                ).setName(t.getNewValue().toString())
//        );
//        columnAmount.setEditable(true);
//        //first column init
//        columnDescription = new TableColumn("Description");
//        columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
//        columnDescription.setCellFactory(cellFactory);
//        columnDescription.setOnEditCommit(
//                t -> (t.getTableView().getItems().get(
//                        t.getTablePosition().getRow())
//                ).setName(t.getNewValue())
//        );
//        columnDescription.setEditable(true);
//
//        categoriesListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            // Your action here
//            tableView.setItems(getCategories(newValue));
//        });
//
//        tableView.getColumns().addAll(columnAmount, columnDescription);
    }
}
