package DataBase;

import java.util.List;

public interface TempDAO {
    void save(TempDTO tempDTO );
    List<TempDTO> load(String cpr); //Man loader fra databasen og sætter det i TemmpDTO.
}

//Dette er en kontrakt, hvor der står at den skal kunne to ting, den skal gemme + indlæse (save + load).