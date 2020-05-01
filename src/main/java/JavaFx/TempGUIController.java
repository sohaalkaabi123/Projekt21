package JavaFx;

import DataBase.TempDAO;
import DataBase.TempDAOImpl;
import DataBase.TempDTO;
import Sampler.*;
import javafx.application.Platform;
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

public class TempGUIController implements TempListener {

    public TempDAO tempDAO = new TempDAOImpl();
    public Button søg;
    public Button run;
    public Button loader;
    public TextField cpr;
    public TextArea dataOutput;

    public void temp(ActionEvent actionEvent) {
        System.out.println("du trykkede på knappen");
        TempGenerator generator = new TempGenerator();
        new Thread(generator).start();
        generator.registerObserver(this);
    }

    public void notify(final TempDTO tempDTO) {

        Platform.runLater(new Runnable() {
            public void run() {
                String text = dataOutput.getText();
                text += "New Data! Temp:" + tempDTO.getTemp() + " °C " + ",Time: " + tempDTO.getTime() + "\r\n";
                dataOutput.setText(text);
            }
        });
        TempDTO saveDTO = new TempDTO();
        saveDTO.setCpr(cpr.getText());
        saveDTO.setTemp(tempDTO.getTemp());
        saveDTO.setTime(tempDTO.getTime());
        tempDAO.save(saveDTO);
        tempDAO.save(new TempDTO());
    }

    public void next(ActionEvent actionEvent) throws IOException {
        Parent secondPaneLoader = FXMLLoader.load(getClass().getResource("/loader.fxml"));
        Scene secondScene = new Scene(secondPaneLoader);
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(secondScene);
        primaryStage.show();
    }
}
