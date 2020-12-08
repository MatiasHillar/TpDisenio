package acceso;

import java.sql.SQLException;
import java.util.List;

import excepciones.UsuarioExistenteException;
import logica.Usuario;

public interface UsuarioDAO {
	Usuario saveOrUpdate(Usuario u) throws UsuarioExistenteException, SQLException;
	void delete (int id);
	List<Usuario> buscarTodos();
	Usuario buscarPorId(int id);
}
