/**
 * 
 */
package acceso;

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
	public List<Participante> buscar(int id);
	Participante buscarPorId(int id);
}
