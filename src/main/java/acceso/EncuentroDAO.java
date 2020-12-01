package acceso;

import java.sql.Connection;

import logica.Encuentro;

public interface EncuentroDAO {

	public Encuentro saveOrUpdate(Connection conn, Encuentro e);
	public void delete(Connection conn, int id);
	
}
