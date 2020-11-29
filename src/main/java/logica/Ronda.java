package logica;

import java.util.ArrayList;

public class Ronda {
	private Integer idRonda;
	private TIPO_RONDA tipo;
	private ArrayList<Encuentro> encuentros;

	public TIPO_RONDA getTipo() {
		return tipo;
	}

	public void setTipo(TIPO_RONDA tipo) {
		this.tipo = tipo;
	}

	public Integer getIdRonda() {
		return idRonda;
	}

	public void setIdRonda(Integer idRonda) {
		this.idRonda = idRonda;
	}

	public ArrayList<Encuentro> getEncuentros() {
		return encuentros;
	}

	public void setEncuentros(ArrayList<Encuentro> encuentros) {
		this.encuentros = encuentros;
	}
	
	
	
}
