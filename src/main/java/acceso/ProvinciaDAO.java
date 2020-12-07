/**
 * 
 */
package acceso;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import logica.Provincia;

/**
 * @author Pichi
 *
 */
public interface ProvinciaDAO {
	public List<Provincia> buscarProvincias();
	public Provincia saveOrUpdate(Connection conn, Provincia p) throws SQLException;
}
