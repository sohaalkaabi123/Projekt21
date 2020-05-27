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

    public TempDAO tempDAO = new TempDAOImpl();//Oprettes en objekt TempDAO
   //Reference til Controls fra FXML-filen
    public Button run;
    public Button loader;
    public TextField cpr;
    public TextArea dataOutput;

    public void temp(ActionEvent actionEvent) {
        System.out.println("du trykkede på knappen");
        TempGenerator generator = new TempGenerator();//Der laves en ny objekt af klassen generator
        new Thread(generator).start(); //Ny tråd sættes igang
        generator.registerObserver(this); //TempGUIController registerer sig som Listener (som er observer) i Generator/Sensor
    }

    public void notify(final TempDTO tempDTO) { //her implementeres interfaces metode notify

        Platform.runLater(new Runnable() { //her bliver der udskrevet løbende af temperatur og tid til UI (grafisk brugergrænseflade) hvis der er værdier fra sensoreren
            //+ gem data i databasen
            public void run() {
                String text = dataOutput.getText();
                text += "New Data! Temp:" + tempDTO.getTemp() + " °C " + ",Time: " + tempDTO.getTime() + "\r\n"; //"\r\n" skift linje
                dataOutput.setText(text);//outputtet kommer ud som tekstfil
                //Her oprettes en variabel/text, som tildeles en værdi som skal stå i tekstfil (dataOutput)
            }
        });
        TempDTO saveDTO = new TempDTO();//Der laves en ny objekt klasse for TempDTO
        saveDTO.setCpr(cpr.getText()); //SetCpr tildeles værdien fra TextField
        saveDTO.setTemp(tempDTO.getTemp()); //SetTemp tildeles værdien fra Temperatur
        saveDTO.setTime(tempDTO.getTime());//SetTime tildeles værdien fra tiden
        tempDAO.save(saveDTO); //Her bliver værdierne fra TempDTO gemt

    }

    public void next(ActionEvent actionEvent) throws IOException { //Her vises Scenen ved brug af ActionsEvent, Event Driven
        Parent secondPaneLoader = FXMLLoader.load(getClass().getResource("/loader.fxml")); //
        Scene secondScene = new Scene(secondPaneLoader);
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(secondScene);
        primaryStage.show();
    }
}
