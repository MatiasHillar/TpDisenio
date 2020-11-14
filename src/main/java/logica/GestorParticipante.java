/**
 * 
 */
package logica;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Pichi
 *
 */
public class GestorParticipante {
	
	
	public static final Pattern patron_email_valido = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	
	public static String saveParticipante(int id_competencia, String nombre_participante, String email_participante ) {
		Participante p = null;
		if(nombre_participante.trim().length() == 0) {
			return "El nombre del participante no puede estar en blanco";
		}
		if(email_participante.trim().length() == 0) {
			return "El email del participante no puede estar en blanco";
		}
		if(!validarEmail(email_participante)) {
			return "El email ingresado no es valido";
		}
		
		p.setNombre(nombre_participante);
		p.setEmail(email_participante);
		
		try {
			(new ParticipanteDAOimpl()).saveOrUpdate(p, id_competencia);
		}
		catch(SQLException e) {
			return "Hubo un error al guardar el participante";
		}
		
		
		return "Exito";
	}
	
	private static boolean validarEmail(String email) {
		Matcher matcher = patron_email_valido.matcher(email);
		return matcher.find();
	}
}
