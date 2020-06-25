package DataBase;

import java.util.LinkedList;

public interface EKGListener {
    void notifyEKG(LinkedList<EKGDTO>ekgDTO);
}
