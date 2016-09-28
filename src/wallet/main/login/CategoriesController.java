package wallet.main.login;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by sveto on 9/26/2016.
 */
public class CategoriesController implements Initializable {
    private CategoriesModel categoriesModel = new CategoriesModel();

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

    TableColumn<Category, String> columnDescription;

    TableColumn<Category, Double> columnAmount;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // second column init
        columnAmount = new TableColumn<>("Amount");
        columnAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        //first column init
        columnDescription = new TableColumn<>("Description");
        columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        //Fill Table


        addButton.setOnMouseClicked(event -> addButtonClicked(addCategoryName.getText()));
        deleteButton.setOnMouseClicked(event -> deleteButtonClicked());
        showCategories();

        categoriesListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Your action here
            tableView.setItems(getCategories(newValue));
        });
        tableView.getColumns().addAll(columnAmount, columnDescription);
    }

    //Add new category
    public void addButtonClicked(String entry) {
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;
        String superCategoryQuery = "INSERT INTO t_categories (category_name) VALUES (?)";
        String subCategoryQuery = "INSERT INTO t_cat_reg (name,user) VALUES (?)";
        try {
            preparedStatement = categoriesModel.connection.prepareStatement(superCategoryQuery);
            preparedStatement2 = categoriesModel.connection.prepareStatement(subCategoryQuery);
            if (!categoriesModel.hasCategory(entry)) {
                if (!entry.equals("")) {
                    preparedStatement.setString(1, entry);
                    preparedStatement2.setString(1, entry);
                } else {
                    System.out.print("Empty category.");
                }
                preparedStatement.executeUpdate();
                preparedStatement2.executeUpdate();


                categoriesListView.getItems().clear();
                showCategories();
            } else {
                System.out.println("Already exists.");
            }
        } catch (SQLException e) {
            try {
                preparedStatement.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    //Delete category button clicked
    public void deleteButtonClicked() {
        PreparedStatement preparedStatement;
        String query = "DELETE FROM t_categories where category_name = ? ";
        String query2 = "DELETE FROM t_cat_reg where name = ? ";
        String selected = categoriesListView.getSelectionModel().getSelectedItem().toString();
        try {
            preparedStatement = categoriesModel.connection.prepareStatement(query);
            preparedStatement.setString(1, selected);
            preparedStatement.executeUpdate();
            preparedStatement = categoriesModel.connection.prepareStatement(query2);
            preparedStatement.setString(1, selected);
            System.out.println("Selected" + selected);
            preparedStatement.executeUpdate();

            //Clear the list
            categoriesListView.getItems().clear();
            showCategories();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Category> getCategories(String value) {
        ObservableList<Category> categories = FXCollections.observableArrayList();
        ObservableList<String> data = FXCollections.observableArrayList();
        PreparedStatement preparedStatement;
        ResultSet rs;
        String query = "SELECT * from t_cat_reg where name = ? AND amount IS NOT NULL";
        String selected = value;
        try {
            preparedStatement = categoriesModel.connection.prepareStatement(query);
            preparedStatement.setString(1, selected);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                data.add(row.toString());

            }

            List items = data;

            items.forEach(item -> {
                System.out.println(item);
                String parts[] = item.toString().replaceAll("[\\[\\]]", "").replaceAll(",", "").split("\\s+");
                String name = selected;
                double amount = Double.parseDouble(parts[1]);
                Category category = null;

                switch (parts.length) {

                    case 2:
                        category = new Category(name, amount);
                        break;
                    case 3:
                        category = new Category(name, amount, parts[2]);
                        break;
                    default:
                        break;
                }

                if (category != null) {
                    categories.add(category);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    return categories;
    }


    private void showCategories() {
        ObservableList<String> data = FXCollections.observableArrayList();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT * from t_categories";
        try {
            preparedStatement = categoriesModel.connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String substringRow;
                List row = new ArrayList();
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    row.add(resultSet.getString(i).toString());
                }
                substringRow = row.toString().replaceAll("[\\[\\]]", "").replaceAll(",", " ");
                data.add(substringRow);
            }
            categoriesListView.getItems().addAll(data);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
