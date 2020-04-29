package DataBase;

public interface TempDAO {
    String save(TempDTO tempDTO );
    TempDTO load(TempDTO tempDTO);
}
