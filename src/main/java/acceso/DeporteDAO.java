package acceso;

import java.util.List;

import logica.Deporte;

public interface DeporteDAO {
	Deporte saveOrUpdate (Deporte d);
	void delete (Integer id);
	List<Deporte> buscarTodos();
	Deporte buscarPorNombre(String nombre);
}
