package acceso;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;		
import logica.FormaPuntuacion;


public interface FormaDePuntuacionDAO {
	public Integer saveOrUpdate (FormaPuntuacion f, Connection conn)throws SQLException;
	public void delete (int id);
	public List<FormaPuntuacion> buscarTodos();
	public FormaPuntuacion buscarPorId(int id, Connection conn);
}
