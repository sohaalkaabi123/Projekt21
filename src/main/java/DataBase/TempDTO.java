package DataBase;

import java.sql.Timestamp;

public class TempDTO { //TempDTO oprettes som står for "Data Transfer Object). Den modtager og overfører, derfor der er getter og setters. Get er når værdien hentes og set er hvordan man gør det.

    private String Cpr; //Der oprettes 3 attributter svarende til de 3 objekter der er eksisterende i databasen.
    private double temp;
    private Timestamp time;

    public String getCpr() {
        return Cpr;
    } //get henter værdi til en variabel i en klasse

    public void setCpr(String cpr) {
        Cpr = cpr;
    } //set giver en værdi til en variabel i en klasse

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
