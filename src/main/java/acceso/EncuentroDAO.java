package acceso;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import logica.Encuentro;

public interface EncuentroDAO {

	public Encuentro saveOrUpdate(Connection conn, Encuentro e) throws SQLException;
	public void delete(Connection conn, int id);
	public List<Encuentro> buscarEncuentrosPorRonda(int id_ronda, Connection conn);
	
}
