package acceso;

import java.sql.Connection;

import logica.Resultado;

public interface ResultadoDAO {

	public Resultado saveOrUpdate(Connection conn, Resultado res);
	public void delete(Connection conn, int id);
}
