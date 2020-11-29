/**
 * 
 */
package logica;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Pichi
 *
 */
public class FixtureDTO {
	private Integer idFixture; 
	private String[][] tablaDePosiciones;
	private Integer competencia; 
	private String[][] rondas;
	
	public FixtureDTO(Integer idFixture, String[][] tablaDePosiciones, Integer competencia, String[][] rondas) {
		super();
		this.idFixture = idFixture;
		this.tablaDePosiciones = tablaDePosiciones;
		this.competencia = competencia;
		this.rondas = rondas;
	}

	
	public String[][] getTablaDePosiciones() {
		return tablaDePosiciones;
	}

	public void setTablaDePosiciones(String[][] tablaDePosiciones) {
		this.tablaDePosiciones = tablaDePosiciones;
	}

	public Integer getCompetencia() {
		return competencia;
	}

	public void setCompetencia(Integer competencia) {
		this.competencia = competencia;
	}

	public String[][] getRondas() {
		return rondas;
	}

	public void setRondas(String[][] rondas) {
		this.rondas = rondas;
	}

	public Integer getIdFixture() {
		return idFixture;
	}
	
	
	
	
}
