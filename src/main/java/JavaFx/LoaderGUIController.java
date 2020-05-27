package JavaFx;


import DataBase.TempDAO;
import DataBase.TempDAOImpl;
import DataBase.TempDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class LoaderGUIController {


    public TextField Felt;//Reference til Controls fra FXML-filen
    public Button Søg;
    public TextArea målinger;
    public Button tilbage;

    public void loadData(ActionEvent actionEvent) { //Event Driven, dvs. her trykkes ved en knap
        String cpr = Felt.getText(); //Der laves en variabel og tildeles værdien fra TextArea
        TempDAO tempDAO = new TempDAOImpl(); //Ny objekt af klassen TempDAOImpl
        List<TempDTO> tempData = tempDAO.load(cpr);//List TempDTO der indeholder TempDTO udfyldes af Resultset fra load
        String text = ""; //Der laves en variabel af typen streng
        for (TempDTO data : tempData) { //for hvert element i listen tempData tilføjes elements data til stringen text
            text += "Cpr: " + data.getCpr() + ", Temperature: " + data.getTemp() + " °C" + ", Time: " + data.getTime() + "\r\n";
        }
        målinger.setText(text);//målingerne vises i TextArea
        System.out.println("Load is done!!");
    }

    public void Back(ActionEvent actionEvent) throws IOException {

        Parent secondPaneLoader = FXMLLoader.load(getClass().getResource("/TempGUI.fxml"));
        Scene secondScene = new Scene(secondPaneLoader);
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(secondScene);
        primaryStage.show();

    }
}

