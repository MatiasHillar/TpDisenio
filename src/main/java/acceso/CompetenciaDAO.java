package acceso;

import java.sql.SQLException;
import java.util.List;

import logica.Competencia;

public interface CompetenciaDAO {
	
	public void saveOrUpdate(Competencia c)throws SQLException;
	public void delete (int id);
	public List<Competencia> buscar();
	Competencia buscarPorId(int id);
}
