package DataBase;

import java.sql.*;

public class TempDAOImpl implements TempDAO {

    Connection conn = Connector.getConn();

    public String save(TempDTO tempDTO) {
        try {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO Patienter (Temperatur) VALUES (?) ");
            statement.setDouble(1, tempDTO.getTemp());
            System.out.println("okay!!");
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public TempDTO load(TempDTO tempDTO) {
        try {
            Statement statement = conn.createStatement();
            ResultSet show_tabels = statement.executeQuery("SELECT Temperatur FROM Patienter");
            TempDTO TempDTO = new TempDTO();
            while (show_tabels.next()) {
                TempDTO.setTemp(show_tabels.getDouble("Temperatur"));
            }
            return TempDTO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
