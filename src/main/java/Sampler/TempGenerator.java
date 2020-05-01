package Sampler;

import DataBase.TempDTO;
import JavaFx.*;

import java.sql.Timestamp;

public class TempGenerator implements TempSampler, Runnable {

    private TempListener observer;

    public void run() {
        while (true) {
            try {
                TempDTO tempDTO = new TempDTO();
                int max = 200;
                int min = 150;
                double value = (Math.random() * ((max - min) + 1) + min);
                double temperatur = (value * 4 / 50) + 24;
                tempDTO.setTemp(temperatur);
                tempDTO.setTime(new Timestamp(System.currentTimeMillis()));
                if (observer != null) {
                    observer.notify(tempDTO);
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void registerObserver(TempGUIController listener) {
        this.observer = listener;
    }
}
