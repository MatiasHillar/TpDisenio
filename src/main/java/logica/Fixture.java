package logica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Fixture {
	
	private Integer idFixture; 
	private TablaDePosiciones tablaDePosiciones;
	private Competencia competencia; 

	private Set<Ronda> ronda = new HashSet(0);

	public Set<Ronda> getRonda() {
		return ronda;
	}
	
	public Fixture(Integer idFixture) {
		this.idFixture = idFixture;
	}
	
	
	public Fixture(Integer idFixture, List<Ronda> rondas) {
		this.idFixture = idFixture;
		this.ronda = new HashSet<Ronda>(rondas);
		
	}
	
	
	
	public Fixture() {
		// TODO Auto-generated constructor stub
	}

	public Fixture(ArrayList<Ronda> rondas) {
		this.ronda = new HashSet<Ronda>(rondas);
	}

	public void setRonda(Set<Ronda> ronda) {
		this.ronda = ronda;
	}

	public Competencia getCompetencia() {
		return competencia;
	}

	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
	}

	public Integer getIdFixture() {
		return idFixture;
	}

	public void setIdFixture(Integer idFixture) {
		this.idFixture = idFixture;
	}

	public TablaDePosiciones getTablaDePosiciones() {
		return tablaDePosiciones;
	}

	public void setTablaDePosiciones(TablaDePosiciones tablaDePosiciones) {
		this.tablaDePosiciones = tablaDePosiciones;
	}	
	
	
	
}