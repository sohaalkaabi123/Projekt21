package Sampler;

import JavaFx.*;

public class TempGenerator implements TempSampler, Runnable {

    private TempListener observer;

    public void run() {
        while (true) {
            try {
                Thread.sleep(10000);

                int max = 200;
                int min = 150;
                double value = (Math.random() * ((max - min) + 1) + min);

                double temperatur = (value * 4 / 50) + 24;
                if (observer != null) {
                    observer.notify(temperatur);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void registerObserver(TempGUIController listener) {
        this.observer = listener;
    }
}
