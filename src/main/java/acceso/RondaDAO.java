package acceso;

import java.sql.Connection;

import logica.Ronda;

public interface RondaDAO {

	public Ronda saveOrUpdate(Connection conn, Ronda r);
	public void delete(Connection conn, int id);
}
