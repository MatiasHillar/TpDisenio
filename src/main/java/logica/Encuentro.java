package logica;

import java.util.Date;

public class Encuentro {
private Integer idEncuentro;
private Resultado resultado;
private Ronda ronda;
private LugarRealizacion lugar;
private Date fecha;
private Participante participante1;
private Participante participante2;

public LugarRealizacion getLugar() {
	return lugar;
}
public void setLugar(LugarRealizacion lugar) {
	this.lugar = lugar;
}
public Participante getParticipante1() {
	return participante1;
}
public void setParticipante1(Participante participante1) {
	this.participante1 = participante1;
}
public Participante getParticipante2() {
	return participante2;
}
public void setParticipante2(Participante participante2) {
	this.participante2 = participante2;
}
public Date getFecha() {
	return fecha;
}
public void setFecha(Date fecha) {
	this.fecha = fecha;
}
public Resultado getResultado() {
	return resultado;
}
public void setResultado(Resultado resultado) {
	this.resultado = resultado;
}
public Ronda getRonda() {
	return ronda;
}
public void setRonda(Ronda ronda) {
	this.ronda = ronda;
}
public Integer getIdEncuentro() {
	return idEncuentro;
}
public void setIdEncuentro(Integer idEncuentro) {
	this.idEncuentro = idEncuentro;
}

}
