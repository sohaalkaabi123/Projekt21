package DataBase;

import java.sql.Timestamp;

public class EKGDTO {
    private  int EKGPatientID;
    private  double EKGMeasurements;
    private  Timestamp EKGTime;


    public  int getEKGPatientID() {
        return EKGPatientID;
    }

    public void setEKGPatientID(int EKGPatientID) {
        this.EKGPatientID = EKGPatientID;
    }

    public  double getEKGMeasurements() {
        return EKGMeasurements;
    }

    public void setEKGMeasurements(double EKGMeasurements) {
        this.EKGMeasurements = EKGMeasurements;
    }

    public  Timestamp getEKGTime() {
        return EKGTime;
    }

    public void setEKGTime(Timestamp EKGTime) {
        this.EKGTime = EKGTime;
    }
}
