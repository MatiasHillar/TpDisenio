package logica;

import java.util.HashSet;
import java.util.Set;

public class Fixture {
	
	private Integer idFixture; 
	private TablaDePosiciones tablaDePosiciones;
	private Competencia competencia; 

	private Set<Ronda> ronda = new HashSet(0);

	public Set<Ronda> getRonda() {
		return ronda;
	}

	public void setRonda(Set<Ronda> ronda) {
		this.ronda = ronda;
	}	
	
	
	
}