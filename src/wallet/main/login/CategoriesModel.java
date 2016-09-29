package wallet.main.login;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sveto on 9/26/2016.
 */
public class CategoriesModel {
    Connection connection;
    public CategoriesModel() {
        connection = SqliteConnection.Connector();
        if (connection == null) {
            System.out.print("No db connection");
            System.exit(1);
        }
    }

    public void showUserParentCategories(ListView<String> categoriesListView) {
        ObservableList<String> data = FXCollections.observableArrayList();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "SELECT * from t_categories";//where user = ?
        try {
            preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1, user);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String substringRow;
                List row = new ArrayList();
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    row.add(resultSet.getString(i));
                }
                substringRow = row.toString().replaceAll("[\\[\\]]", "").replaceAll(",", " ");
                data.add(substringRow);
            }
            categoriesListView.getItems().addAll(data);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public boolean hasCategory(String category) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM t_categories where category_name = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, category);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //Add new category
    public void createParentCategory(String entry, ListView<String> categoriesListView) {
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;
        String superCategoryQuery = "INSERT INTO t_categories (category_name) VALUES (?)";
        String subCategoryQuery = "INSERT INTO t_cat_reg (name) VALUES (?)";
        try {
            preparedStatement = connection.prepareStatement(superCategoryQuery);
            preparedStatement2 = connection.prepareStatement(subCategoryQuery);
            if (!hasCategory(entry)) {
                if (!entry.equals("")) {
                    preparedStatement.setString(1, entry);
                    preparedStatement2.setString(1, entry);
                } else {
                    System.out.print("Empty category.");
                }
                preparedStatement.executeUpdate();
                preparedStatement2.executeUpdate();


                categoriesListView.getItems().clear();
                showUserParentCategories(categoriesListView);
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
    public void deleteParentCategory(ListView<String> categoriesListView) {
        PreparedStatement preparedStatement;
        String query = "DELETE FROM t_categories where category_name = ? ";
        String query2 = "DELETE FROM t_cat_reg where name = ? ";
        String selected = categoriesListView.getSelectionModel().getSelectedItem().toString();
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, selected);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(query2);
            preparedStatement.setString(1, selected);
            System.out.println("Selected" + selected);
            preparedStatement.executeUpdate();

            //Clear the list
            categoriesListView.getItems().clear();
            showUserParentCategories(categoriesListView);
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
            preparedStatement = connection.prepareStatement(query);
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
                String description = parts[2];
                String amount = parts[1];
                Category category = null;

                switch (parts.length) {
                    case 2:
                        category = new Category(name, amount);
                        break;
                    case 3:
                        category = new Category(name, amount, description);
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

    public void createTableWithColumns(TableView<Category> tableView, TableColumn<Category, String> columnAmount, TableColumn<Category, String> columnDescription, ListView<String> categoriesListView) {
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
            tableView.setItems(getCategories(newValue));
        });

        tableView.getColumns().removeAll(columnAmount, columnDescription);
        tableView.getColumns().addAll(columnAmount, columnDescription);
    }
}
