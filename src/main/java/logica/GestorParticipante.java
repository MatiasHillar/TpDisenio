/**
 * 
 */
package logica;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import acceso.ParticipanteDAOimpl;

/**
 * @author Pichi
 *
 */
public class GestorParticipante {
	
	
	public static final Pattern patron_email_valido = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	
	public static String saveParticipante(int id_competencia, String nombre_participante, String email_participante ) {
		Participante p = new Participante();
		boolean emailNoValido = false;
		boolean nombreVacio = false;
		boolean emailVacio = false;
		if(nombre_participante.trim().length() == 0) {
			nombreVacio = true;
		}
		if(email_participante.trim().length() == 0) {
			emailVacio = true;
		}
		if(!validarEmail(email_participante)) {
			emailNoValido = true;
		}
		String response = "";
		if(nombreVacio)
			response += "n";
		if(emailVacio)
			response += "e";
		if(emailNoValido)
			response += "v";
		
		if(nombreVacio || emailVacio || emailNoValido)
			return response;
		
		
		p.setNombre(nombre_participante);
		p.setEmail(email_participante);
		p.setCompetencia(new Competencia(id_competencia));
		try {
			(new ParticipanteDAOimpl()).saveOrUpdate(p);
		}
		catch(SQLException e) {
			return "Hubo un error al guardar el participante";
		}
		
		
		return "Exito";
	}
	
	public static boolean validarEmail(String email) {
		Matcher matcher = patron_email_valido.matcher(email);
		return matcher.find();
	}
	
	
	
	public static void deleteParticipante(String id) {
		(new ParticipanteDAOimpl()).delete(Integer.valueOf(id));
		
	}
}
