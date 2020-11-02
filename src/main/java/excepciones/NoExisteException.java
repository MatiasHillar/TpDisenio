package excepciones;


public class NoExisteException extends Exception {

	public NoExisteException() {
		super("No se ha encontrado ningún elemento");
	}
}
