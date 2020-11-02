package acceso;

import java.util.List;	
import logica.Usuario;

public interface UsuarioDAO {
	Usuario saveOrUpdate(Usuario u);
	void delete (int id);
	List<Usuario> buscarTodos();
	Usuario buscarPorId(int id);
}
