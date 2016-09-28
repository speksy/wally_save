package wallet.main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * Sample application that shows examples of the different layout panes
 * provided by the JavaFX layout API.
 * The resulting UI is for demonstration purposes only and is not interactive.
 */
public class WallyHome extends Application {
    TableView<Product> table;
    TextField nameInput, priceInput;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(WallyHome.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception{

// Use a border pane as the root for scene
        BorderPane border = new BorderPane();

//        HBox hbox = addHBox();
//        border.setTop(hbox);
        border.setLeft(addVBox());

//        hbox.setStyle("");
//        addStackPane(hbox);
        border.setCenter(addAnchorPane(addGridPane()));

        Scene scene = new Scene(border);
        stage.setScene(scene);
        stage.setTitle("Wally Save");
        stage.show();
    }

/*
 * Creates an HBox with two buttons for the top region
 */

    public HBox addHBox() {

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(0, 0, 10, 0));
        hbox.setSpacing(0);   // Gap between nodes
//        hbox.getChildren().addAll();

        return hbox;
    }

    /*
     * Creates a VBox with a list of links for the left region
     */
    public VBox addVBox() {

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10)); // Set all sides to 10
        vbox.setSpacing(8);              // Gap between nodes



        Image addCategoryImage = new Image(getClass().getResourceAsStream("images/buttons/Add.png"));

//        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Button addCategoryButton = new Button("", new ImageView(addCategoryImage));
        addCategoryButton.setOnAction(event -> addVBox());
//        vbox.getChildren().addAll(title,addCategoryButton);

//        Hyperlink options[] = new Hyperlink[] {new Hyperlink("FOOD")};
//        for (int i=0; i<1; i++) {
//
//            // Add offset to left side to indent from title
//            VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
//            vbox.getChildren().add(options[i]);
//        }

        return vbox;
    }

    /*
     * Uses a stack pane to create a help icon and adds it to the right side of an HBox
     *
     * @param hb HBox to add the stack to
     */
    public void addStackPane(HBox hb) {
        hb.setStyle("-fx-background-color: #363636");
        StackPane stack = new StackPane();

        InnerShadow is = new InnerShadow();
        is.setOffsetX(1.0f);
        is.setOffsetY(1.0f);

        Text logoText = new Text();
        logoText.setEffect(is);

        logoText.setFill(Color.web("#202020"));
        logoText.setText("Wally Save");
        logoText.setFont(Font.loadFont(WallyHome.class.getResource("ConcertOne-Regular.ttf").toExternalForm(), 50));


        stack.getChildren().add(logoText);
        stack.setAlignment(Pos.CENTER);
        // Add offset to right for question mark to compensate for RIGHT
        // alignment of all nodes


        hb.getChildren().add(stack);
        HBox.setHgrow(stack, Priority.ALWAYS);

    }

    /*
     * Creates a grid for the center region with four columns and three rows
     */
    public VBox addGridPane() {

        //Name column
        TableColumn<Product, String> nameColumn = new TableColumn<>("Description");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //Price column
        TableColumn<Product, Double> priceColumn = new TableColumn<>("Amount");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Name input
        nameInput = new TextField();
        nameInput.setPromptText("Description: ");
        nameInput.setMinWidth(100);

        //Price input
        priceInput = new TextField();
        priceInput.setPromptText("Amount :");


        //Button
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addButtonClicked());
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteButtonClicked());

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(nameInput, priceInput, addButton, deleteButton);

        table = new TableView<>();
        table.setItems(getProduct());
        table.getColumns().addAll(nameColumn, priceColumn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, hBox);

        return vBox;
    }

    /*
     * Creates an anchor pane using the provided grid and an HBox with buttons
     *
     * @param grid Grid to anchor to the top of the anchor pane
     */
    public AnchorPane addAnchorPane(VBox vb) {

        AnchorPane anchorpane = new AnchorPane();


        HBox hb = new HBox();
        hb.setPadding(new Insets(0, 10, 10, 10));
        hb.setSpacing(10);

        anchorpane.getChildren().addAll(vb,hb);
        // Anchor buttons to bottom right, anchor grid to top
        AnchorPane.setBottomAnchor(hb, 8.0);
        AnchorPane.setRightAnchor(hb, 5.0);
        AnchorPane.setTopAnchor(vb, 10.0);

        return anchorpane;
    }

    //Add button clicked
    public void addButtonClicked(){
        Product product = new Product();
        product.setName(nameInput.getText());
        product.setPrice(Double.parseDouble(priceInput.getText()));
        table.getItems().add(product);
        nameInput.clear();
        priceInput.clear();
    }

    //Delete button clicked
    public void deleteButtonClicked(){
        ObservableList<Product> productSelected, allProducts;
        allProducts = table.getItems();
        productSelected = table.getSelectionModel().getSelectedItems();

        productSelected.forEach(allProducts::remove);
    }

    //Get all of the products
    public ObservableList<Product> getProduct(){
        ObservableList<Product> products = FXCollections.observableArrayList();
        products.add(new Product("Laptop", 859.00, 20));
        products.add(new Product("Bouncy Ball", 2.49, 198));
        products.add(new Product("Toilet", 99.00, 74));
        products.add(new Product("The Notebook DVD", 19.99, 12));
        products.add(new Product("Corn", 1.49, 856));
        return products;
    }
}
