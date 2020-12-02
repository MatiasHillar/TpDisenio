package acceso;

import java.util.List;

import excepciones.UsuarioExistenteException;
import logica.Usuario;

public interface UsuarioDAO {
	Usuario saveOrUpdate(Usuario u) throws UsuarioExistenteException;
	void delete (int id);
	List<Usuario> buscarTodos();
	Usuario buscarPorId(int id);
}
