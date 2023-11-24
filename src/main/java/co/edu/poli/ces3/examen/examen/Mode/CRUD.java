package co.edu.poli.ces3.examen.examen.Mode;

import co.edu.poli.ces3.examen.examen.dto.DtoUser;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CRUD {

    User create(DtoUser user) throws SQLException;

    public ArrayList<User> all();

    public User findById(int id) throws SQLException;

    User update(User user) throws SQLException;

    int delete(int id) throws SQLException;
}
