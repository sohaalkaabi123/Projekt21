package DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TempDAOImpl implements TempDAO {

    Connection conn = Connector.getConn();

    public String save(TempDTO tempDTO) {
        try {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO Målinger (Cpr,Temp,Tid) VALUES (?,?,?) ");

            statement.setString(1, tempDTO.getCpr());
            statement.setDouble(2, tempDTO.getTemp());
            statement.setTimestamp(3, tempDTO.getTime());
            System.out.println("okay!!");
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<TempDTO> load(String cpr) {
        List<TempDTO> data = new ArrayList<TempDTO>();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Målinger WHERE Cpr = ? ");
            preparedStatement.setString(1, cpr);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TempDTO tempDTO = new TempDTO();
                tempDTO.setCpr(resultSet.getString("Cpr"));
                tempDTO.setTemp(resultSet.getDouble("Temp"));
                tempDTO.setTime(resultSet.getTimestamp("Tid"));
                data.add(tempDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
