package acceso;

import java.sql.Connection;

import logica.Fixture;

public interface FixtureDAO {

	public Fixture saveOrUpdate(Connection conn, Fixture f);
	public void delete(Connection conn, int id);
	public Fixture buscarPorId(int id);
}
