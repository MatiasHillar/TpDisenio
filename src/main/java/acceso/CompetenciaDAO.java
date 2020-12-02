package acceso;

import java.sql.SQLException;
import java.util.List;

import logica.Competencia;
import logica.CompetenciaDTO;

public interface CompetenciaDAO {
	
	public void saveOrUpdate(Competencia c)throws SQLException;
	public void delete (int id);
	public List<Competencia> buscar();
	public List<Competencia> buscarPorUsr(int id_participante);
	Competencia buscarPorId(int id);
}
