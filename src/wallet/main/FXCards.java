package wallet.main;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FXCards extends Application {

    private class Card extends StackPane {

        // we declare a property here
        final BooleanProperty hideProperty = new SimpleBooleanProperty();

        public Card(boolean hide) {
            hideProperty.setValue(hide);

            Image cardDP = new Image("https://cache1.24chasa.bg/Images/Cache/145/Image_4583145_126.jpg");
            ImageView iv = new ImageView(cardDP);
            getChildren().add(iv);

            Image cardBack = new Image("https://3.bp.blogspot.com/-W__wiaHUjwI/Vt3Grd8df0I/AAAAAAAAA78/7xqUNj8ujtY/s1600/image02.png");
            ImageView ivBack = new ImageView(cardBack);
            getChildren().add(ivBack);

            // binding to hideProperty
            // card back is visible if hide property is true
            ivBack.visibleProperty().bind(hideProperty);
            // card front is visible if property is false, see "not()" call
            iv.visibleProperty().bind(hideProperty.not());

            setOnMouseClicked((e)-> {
                // click on card to flip it
                hideProperty.setValue(!hideProperty.getValue());
            });
        }

    }


    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();

        root.getChildren().add(new Card(true));

        Scene scene = new Scene(root, 300, 500);
        primaryStage.setTitle("Click to Flip!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}