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


    public TextField Felt;
    public Button Søg;
    public TextArea målinger;
    public Button tilbage;

    public void loadData(ActionEvent actionEvent) {
        String cpr = Felt.getText();
        TempDAO tempDAO = new TempDAOImpl();
        List<TempDTO> tempData = tempDAO.load(cpr);
        String text = "";
        for (TempDTO data : tempData) {
            text += "Cpr: " + data.getCpr() + ", Temperature: " + data.getTemp() + " °C" +", Tid: "+data.getTime() +"\r\n";
        }
        målinger.setText(text);
        System.out.println("Virker!!");
    }

    public void Back(ActionEvent actionEvent) throws IOException {

        Parent secondPaneLoader = FXMLLoader.load(getClass().getResource("/TempGUI.fxml"));
        Scene secondScene = new Scene(secondPaneLoader);
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(secondScene);
        primaryStage.show();

    }
}

