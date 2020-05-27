package JavaFx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TempApp extends Application { //Her oprettes en klasse der arver fra applikation klassen.

    public static void main(String[] args) {
        launch(args); //Metoden bliver kørt i main. Launch starter den. Første tråd.
    }

    @Override //Omskrivning af Factory metode Start
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/TempGUI.fxml"));//laves en loader der henter FXML-filen
        AnchorPane anchorPane = fxmlLoader.load(); //Der tildeles Pane fra FXML-filen
        primaryStage.setScene(new Scene(anchorPane)); //Sætter Pane inde i Scene og Scene ind i Stage
        primaryStage.show(); //vises på skærm

    }
}
