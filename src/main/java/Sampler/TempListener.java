package Sampler;

import DataBase.TempDTO;

public interface TempListener { //
    void notify(TempDTO tempDTO); //her informeres i GUIController at der er kommer TempDTO'er.
}
