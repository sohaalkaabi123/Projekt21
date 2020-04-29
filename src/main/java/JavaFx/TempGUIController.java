package JavaFx;

import DataBase.TempDAO;
import DataBase.TempDAOImpl;
import DataBase.TempDTO;
import Sampler.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TempGUIController implements TempListener {

    public TempDAO tempDAO = new TempDAOImpl();
    public Label tempLabel;
    public Button søg;
    public Button run;

    public void temp(ActionEvent actionEvent) {
        System.out.println("du trykkede på knappen");
        TempGenerator generator = new TempGenerator();
        new Thread(generator).start();
        generator.registerObserver(this);
    }

    public void notify(final double temp) {
        tempDAO.save(new TempDTO());
        Platform.runLater(new Runnable() {
            public void run() {
                tempLabel.setText(String.valueOf(temp));
            }
        });

    }

    public void search(ActionEvent actionEvent) {

    }
}
