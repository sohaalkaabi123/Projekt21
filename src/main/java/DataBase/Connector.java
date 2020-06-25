package DataBase;
//Henter MySQL bibliotek
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private static Connection conn; //En interface som er en slags type, ligesom en klasse. Som et resultat kan du bruge en grænseflade som type for en variabel, parameter eller metodeleverdi.

    public static Connection getConn(){ //Her implementeres interfacet Connection for at oprette forbindelse til databasen.
        try { //Der kan opstå fejl, da der sker I/O fra databasen til Java. Derfor bruges der en Try Catch for at detekterer fejl der opstår ved forsøg af oprette forbindelse til databasen.
            if (conn==null || conn.isClosed()){ //Dette er en branch, der siger hvis der ikke kan oprettes forbindelse / hvis databasen er lukket
                conn = DriverManager.getConnection("jdbc:mysql://db.diplomportal.dk/s195507?" +
                        "user=s195507&password=FIbNHHN9T4J6gmyPymIv6"); //DriveManager er en bestemt metode, der opretter forbindelse til databasen. 3 parameter er udfyldt.
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn; //Den returnerer forbindelse.
    }
}
