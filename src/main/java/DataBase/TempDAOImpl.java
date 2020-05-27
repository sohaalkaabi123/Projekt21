package DataBase;
//Java biblioteker importeres.
import java.sql.*; //Java SQL biblioteket gør så at man kan kalde på metoden connection.
import java.util.ArrayList;
import java.util.List;

public class TempDAOImpl implements TempDAO { //Her laves klassen, der implementerer interfacet TempDAO.

    public void save(TempDTO tempDTO) { //
        Connection conn = Connector.getConn(); //Interfacet bliver kaldt Conn og er lig med implementering der er lavet i Connector Java klassen.
        try {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO Målinger (Cpr,Temp,Tid) VALUES (?,?,?) "); //Først laves der er en SQL statement (PrepareStatement). INSERT INTO er DML, der sætter data ind i databasen. Spørgsmåltegnene ved VALUES er de 3 næste linjer (1, 2, 3). Denne metode saver også.
            statement.setString(1, tempDTO.getCpr()); //Her vælges den rigtige datatype.
            statement.setDouble(2, tempDTO.getTemp()); //Her vælges den rigtige datatype.
            statement.setTimestamp(3, tempDTO.getTime()); //Her vælges den rigtige datatype.
            statement.execute(); //Her executer man statements. De forrige statements udføres.
            System.out.println("save is done!!");
        } catch (Exception e) { //Fanger fejl, hvis data ikke bliver gemt. I/O.
            e.printStackTrace();
        }
    }

    public List<TempDTO> load(String cpr) { //Metoden load() i klassen TempDAOImpl opretter en liste, der kan indeholde TempDTO'er
        List<TempDTO> data = new ArrayList<TempDTO>(); //
        Connection conn = Connector.getConn();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Målinger WHERE Cpr = ? ");
            preparedStatement.setString(1, cpr); //PreparedStatement laver DML statement, der henter værdier fra databasen.
            ResultSet resultSet = preparedStatement.executeQuery(); //Det er en pointer, som fortæller når den er klar til at give data.
            while (resultSet.next()) { //Hvis denne giver true betyder det at der kommer data ud fra den.
                TempDTO tempDTO = new TempDTO(); //Her laves der et nyt objekt af klassen DTO.
                tempDTO.setCpr(resultSet.getString("Cpr")); //Her tildeles værdierne af resultSet til Setter i TempDTO.
                tempDTO.setTemp(resultSet.getDouble("Temp")); //Her tildeles værdierne af resultSet til Setter i TempDTO.
                tempDTO.setTime(resultSet.getTimestamp("Tid")); //Her tildeles værdierne af resultSet til Setter i TempDTO.
                data.add(tempDTO); //Tingene fra TempDTO proppes ind i en liste
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data; //Listen returneres.
    }
}
