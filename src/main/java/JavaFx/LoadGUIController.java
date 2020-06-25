package JavaFx;


import DataBase.EKGDAO;
import DataBase.EKGDAOImpl;
import DataBase.EKGDTO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class LoadGUIController {
    public TextField Felt;//Reference til Controls fra FXML-filen
    public Button Søg;
    public TextArea målinger;
    public Button tilbage;
    public Polyline ekgLinje;


    public void loadData(ActionEvent actionEvent) {
        Platform.runLater(()->{
            EKGDAO EKGDAO = new EKGDAOImpl();
            List<EKGDTO> EKGDTO = EKGDAO.load(Felt.getText());
            LinkedList<Double> EKG = new LinkedList<>();
            ekgLinje.getPoints().clear();
            for (int i = 0; i <EKGDTO.size(); i++) {
                EKG.add(Double.valueOf(i));
                EKG.add(EKGDTO.get(i).getEKGMeasurements());

            }
            ekgLinje.getPoints().addAll(EKG);
        });
    }


}

