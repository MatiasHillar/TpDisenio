/**
 * 
 */
package acceso;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import logica.Localidad;

/**
 * @author Pichi
 *
 */
public interface LocalidadDAO {
	public ArrayList<Localidad> buscarPorProvincia(Long id);
	public Localidad saveOrUpdate(Connection conn, Localidad l) throws SQLException;
}
