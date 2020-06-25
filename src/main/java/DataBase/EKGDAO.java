package DataBase;

import java.util.LinkedList;

public interface EKGDAO {
    void saveBatch( LinkedList<EKGDTO>  batch);
    LinkedList<EKGDTO> load(String PatientID);
}

//Dette er en kontrakt, hvor der står at den skal kunne to ting, den skal gemme + indlæse (save + load).