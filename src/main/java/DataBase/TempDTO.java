package DataBase;

public class TempDTO {

    private String Cpr;
    private double temp;
    private String time;

    public TempDTO() {

    }

    public TempDTO(double temp, String time) {
        this.temp = temp;
        this.time = time;
    }

    public String getCpr() {
        return Cpr;
    }

    public void setCpr(String cpr) {
        Cpr = cpr;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
