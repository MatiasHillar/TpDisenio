/**
 * 
 */
package excepciones;

/**
 * @author Pichi
 *
 */
public class UsuarioExistenteException extends Exception{
	public UsuarioExistenteException() {
		super("Ya existe un usuario registrado con ese email");
	}
}
