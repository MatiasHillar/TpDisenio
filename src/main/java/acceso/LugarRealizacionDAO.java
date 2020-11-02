package acceso;

import java.util.List;

import logica.LugarRealizacion;


public interface LugarRealizacionDAO {
	public LugarRealizacion saveOrUpdate(LugarRealizacion l);
	public void delete(int id);
	public List<LugarRealizacion> buscarTodos();
	public LugarRealizacion buscarPorId(Integer id);
//	List<LugarRealizacion> buscarLugaresPorDeporte(String deporte, String usuario);
}
