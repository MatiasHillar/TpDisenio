package acceso;

import java.sql.Connection;
import java.sql.SQLException;

import logica.Encuentro;

public interface EncuentroDAO {

	public Encuentro saveOrUpdate(Connection conn, Encuentro e) throws SQLException;
	public void delete(Connection conn, int id);
	
}
