package logica;

import java.util.HashSet;
import java.util.Set;

public class ResultadoSet extends Resultado{
private Integer idResultadoSet;
private Set<Unset> sets = new HashSet<Unset>();

public Set<Unset> getSets() {
	return sets;
}

public void setSets(Set<Unset> sets) {
	this.sets = sets;
}

public Integer getIdResultadoSet() {
	return idResultadoSet;
}
}
