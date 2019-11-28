package dtt.lukuvinkkikirjasto.demo.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Dao interface
 *
 * @author milla
 */
public interface Dao<T, K> {

    void create(T t) throws SQLException;

    List<T> list() throws SQLException;

    void delete(T t) throws SQLException;

    //void update(T t);
    
    
}
