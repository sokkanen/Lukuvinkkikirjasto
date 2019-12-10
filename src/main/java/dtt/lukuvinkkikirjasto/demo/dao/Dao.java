package dtt.lukuvinkkikirjasto.demo.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {

    void create(T t) throws SQLException;

    List<T> list() throws SQLException;

    boolean update(T t) throws SQLException;
    void delete(T t) throws SQLException;

}
