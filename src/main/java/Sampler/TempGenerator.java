package Sampler;

import DataBase.TempDTO;
import JavaFx.*;

import java.sql.Timestamp;

public class TempGenerator implements TempSampler, Runnable {

    private TempListener observer; //En interface er en slags type, ligesom en klasse. Som et resultat kan du bruge en grænseflade som type for en variabel, parameter eller metodeleverdi.

    public void run() { //run metode igangsættes og danner målinger
        while (true) { //Uendelig while true løkke der generere temperatur målinger
            try {
                TempDTO tempDTO = new TempDTO();
                int max = 200;
                int min = 150;
                double value = (Math.random() * ((max - min) + 1) + min);
                double temperatur = (value * 4 / 50) + 24;
                tempDTO.setTemp(temperatur);
                tempDTO.setTime(new Timestamp(System.currentTimeMillis()));
                if (observer != null) { //Når observeren/variablen ikke er null, kommer der målinger
                    observer.notify(tempDTO); //Der bliver informeret i TempGUIController at der er kommet noget data fra Generator til TempGUIController som registerer sig som
                }
                Thread.sleep(1000); //While true løkken gentages hvert 1 sekund
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void registerObserver(TempGUIController listener) {
        this.observer = listener;
    } //TempGUIController registerer sig som Listener (som er observer) i Generator/Sensor
}
