/**
 * 
 */
package acceso;

import java.util.ArrayList;

import logica.Localidad;

/**
 * @author Pichi
 *
 */
public interface LocalidadDAO {
	public ArrayList<Localidad> buscarPorProvincia(Long id);
}
