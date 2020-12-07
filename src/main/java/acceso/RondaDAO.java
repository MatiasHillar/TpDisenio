package acceso;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import logica.Ronda;

public interface RondaDAO {

	public Ronda saveOrUpdate(Connection conn, Ronda r);
	public void delete(Connection conn, int id);
	public List<Ronda> buscarPorIdFixture(int id_fixture, Connection conn) throws SQLException;
}
