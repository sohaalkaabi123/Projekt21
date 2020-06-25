package DataBase;
//Java biblioteker importeres.
import java.sql.*; //Java SQL biblioteket gør så at man kan kalde på metoden connection.
import java.util.LinkedList;

public class EKGDAOImpl implements EKGDAO { //Her laves klassen, der implementerer interfacet TempDAO.

    @Override
    public void saveBatch(LinkedList<EKGDTO> batch) {

    {
            new Thread(new Runnable() {
               @Override
               public void run() {
                  Connection conn = Connector.getConn(); //Interfacet bliver kaldt Conn og er lig med implementering der er lavet i Connector Java klassen.

                   try {
                        PreparedStatement  preparedStatement = conn.prepareStatement("INSERT INTO EKGMeasurements (EKGPatientID,EKGMeasurements,EKGTime) VALUES (?, ?, ?) "); //Først laves der er en SQL statement (PrepareStatement). INSERT INTO er DML, der sætter data ind i databasen. Spørgsmåltegnene ved VALUES er de 3 næste linjer (1, 2, 3). Denne metode saver også.
                         for (EKGDTO ekgdto : batch) {
                         preparedStatement.setInt(1, ekgdto.getEKGPatientID()); //Her vælges den rigtige datatype.
                         preparedStatement.setDouble(2, ekgdto.getEKGMeasurements()); //Her vælges den rigtige datatype.
                         preparedStatement.setTimestamp(3, ekgdto.getEKGTime()); //Her vælges den rigtige datatype.
                         preparedStatement.execute(); //Her executer man statements. De forrige statements udføres.

                         }
                   } catch (SQLException e) {
                       e.printStackTrace();

                   }
               }
            }).start();
    }
    }


    @Override
    public LinkedList<EKGDTO> load(String PatientID) { //Metoden load() i klassen TempDAOImpl opretter en liste, der kan indeholde TempDTO'er
        LinkedList<EKGDTO> data = new LinkedList<>(); //
        Connection conn = Connector.getConn();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Patient WHERE EKGPatientID = ? ");
            preparedStatement.setString(1, PatientID); //PreparedStatement laver DML statement, der henter værdier fra databasen.
            ResultSet resultSet = preparedStatement.executeQuery(); //Det er en pointer, som fortæller når den er klar til at give data.
            while (resultSet.next()) { //Hvis denne giver true betyder det at der kommer data ud fra den.
                EKGDTO ekgDTO = new EKGDTO(); //Her laves der et nyt objekt af klassen DTO.
                ekgDTO.setEKGPatientID(Integer.parseInt(resultSet.getString(PatientID)));
                ekgDTO.setEKGPatientID(resultSet.getInt("EKGPatientID")); //Her tildeles værdierne af resultSet til Setter i TempDTO.
                ekgDTO.setEKGMeasurements(resultSet.getDouble("EKGMeasurements")); //Her tildeles værdierne af resultSet til Setter i TempDTO.
                ekgDTO.setEKGTime(resultSet.getTimestamp("EKGTime")); //Her tildeles værdierne af resultSet til Setter i TempDTO.
                data.add(ekgDTO); //Tingene fra TempDTO proppes ind i en liste
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data; //Listen returneres.
    }
}
