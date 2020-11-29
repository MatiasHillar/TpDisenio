/**
 * 
 */
package logica;

/**
 * @author Pichi
 *
 */
public class ParticipanteDTO {
	private Integer idParticipante;
	private String nombre;
	private String email;
	
	public ParticipanteDTO(Integer idParticipante, String nombre, String email) {
		super();
		this.idParticipante = idParticipante;
		this.nombre = nombre;
		this.email = email;
	}

	public Integer getIdParticipante() {
		return idParticipante;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
