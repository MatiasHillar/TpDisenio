/**
 * 
 */
package acceso;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import logica.Competencia;
import logica.Participante;

/**
 * @author Pichi
 *
 */
public interface ParticipanteDAO {
	public void saveOrUpdate(Participante p)throws SQLException;
	public void delete (int id);
	public void deleteByFilters(String nombre, String email);
	public List<Participante> buscar(int id, Connection conn);
	Participante buscarPorId(int id, Connection conn);
}
