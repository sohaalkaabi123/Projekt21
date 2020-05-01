package DataBase;

import java.util.List;

public interface TempDAO {
    String save(TempDTO tempDTO );
    List<TempDTO> load(String cpr);
}
