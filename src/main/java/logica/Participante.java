package logica;

public class Participante {
private Integer idParticipante;
private String nombre;
private String email;
private Competencia competencia;




public Participante(Integer idParticipante, String nombre, String email, Competencia competencia) {
	this.idParticipante = idParticipante;
	this.nombre = nombre;
	this.email = email;
	this.competencia = competencia;
}

public Participante() {
	// TODO Auto-generated constructor stub
}

public Competencia getCompetencia() {
	return competencia;
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
public Integer getIdParticipante() {
	return idParticipante;
}
public void setIdParticipante(Integer idParticipante) {
	this.idParticipante = idParticipante;
}

public void setCompetencia(Competencia competencia) {
	this.competencia = competencia;
	
}
}
