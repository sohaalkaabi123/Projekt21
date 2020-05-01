package DataBase;

import java.util.List;

public interface TempDAO {
    void save(TempDTO tempDTO );
    List<TempDTO> load(String cpr);
}
