package acceso;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;	
import logica.DisponiblePara;

public interface DisponibilidadDAO {
	
	public DisponiblePara saveOrUpdate (DisponiblePara d, Connection conn)throws SQLException;
	public void delete (int id);
	public List<DisponiblePara> buscarTodos();
	public int buscarDisponibilidad(int id);
	public List<DisponiblePara> buscarConIdCompe(int id_compe, Connection conn);
}
