package acceso;

import java.sql.Connection;
import java.sql.SQLException;

import logica.Fixture;

public interface FixtureDAO {

	public Fixture saveOrUpdate(Connection conn, Fixture f) throws SQLException;
	public void delete(Connection conn, int id);
	public Fixture buscarPorId(int id);
}
