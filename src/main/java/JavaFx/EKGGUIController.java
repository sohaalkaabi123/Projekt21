package JavaFx;

import DataBase.EKGDAO;
import DataBase.EKGDAOImpl;
import DataBase.EKGDTO;
import DataBase.EKGListener;
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

public class EKGGUIController implements EKGListener {

    public EKGDAO EKGDAO = new EKGDAOImpl();
    public Button run;
    public Button loadEkg;
    public TextField PatientID;
    public Polyline ekgLinje;
    public TextField patientID;
    double x = 0;


    public void loadEkg(ActionEvent actionEvent) throws IOException {
        Parent fourthPaneLoader = FXMLLoader.load(getClass().getResource("/EKGLoad.fxml"));
        Scene fourthScene = new Scene(fourthPaneLoader);
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(fourthScene);
        primaryStage.setTitle("EKG Data Base");
        primaryStage.show();
    }

    @Override
    public void notifyEKG(LinkedList<EKGDTO> ekgdtos) {

        Platform.runLater(() -> {
            LinkedList<Double> EKG = new LinkedList<>();
            for (int i = 0; i < ekgdtos.size(); i++) {
                EKGDTO ekgDTO = ekgdtos.get(i);
                EKG.add(x);
                EKG.add(ekgDTO.getEKGMeasurements());
                ekgDTO.setEKGPatientID(Integer.parseInt(PatientID.getText()));
                x++;
            }
            if (x > 600){
                x = 0;
                ekgLinje.getPoints().clear();

            }
            ekgLinje.getPoints().addAll(EKG);

        });
        new Thread(()->{
            EKGDAO.saveBatch(ekgdtos);
        }).start();
    }
}



    /*@Override
    public void notifyEKG(LinkedList<EKGDTO> ekgdtos) {

        Platform.runLater(() -> {
            LinkedList<Double> EKG = new LinkedList<>();
            for (int i = 0; i < ekgdtos.size(); i++) {
                EKGDTO ekgDTO = ekgdtos.get(i);
                EKG.add(x);
                EKG.add(ekgDTO.getEKGMeasurements());
                ekgDTO.setEKGPatientID(Integer.parseInt(PatientID.getText()));
                x++;
            }
            if (x>600){
                x = 0;
                ekgLinje.getPoints().clear();

            }
            ekgLinje.getPoints().addAll(EKG);

        });
        new Thread(()->{
            EKGDAO.saveBatch(ekgdtos);
        }).start();
    }*/



}
