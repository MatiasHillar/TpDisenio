/**
 * 
 */
package logica;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Pichi
 *
 */
public class CompetenciaDTO {
	private Integer idCompetencia;
	private String deporte;
	private Integer formaPuntuacion;
	private String[] usuario;
	private String nombre;
	private Boolean permiteEmpate;
	private String fechaInicio;
	private String fechaFin;
	private String fechaBaja;
	private String estado;
	private String reglamento;
	
	private String[][] fixture;
	private String[][] disponiblePara;
	private String[][] participantes;
	
	
	
	public CompetenciaDTO(Integer idCompetencia, String deporte, String nombre, String estado) {
		super();
		this.idCompetencia = idCompetencia;
		this.deporte = deporte;
		this.nombre = nombre;
		this.estado = estado;
	}
	
	

	public CompetenciaDTO(Integer idCompetencia, String deporte, Integer formaPuntuacion, String[] usuario,
			String nombre, Boolean permiteEmpate, String fechaInicio, String fechaFin, String fechaBaja, String estado,
			String reglamento, String[][] fixture, String[][] disponiblePara, String[][] participantes) {
		super();
		this.idCompetencia = idCompetencia;
		this.deporte = deporte;
		this.formaPuntuacion = formaPuntuacion;
		this.usuario = usuario;
		this.nombre = nombre;
		this.permiteEmpate = permiteEmpate;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fechaBaja = fechaBaja;
		this.estado = estado;
		this.reglamento = reglamento;
		this.fixture = fixture;
		this.disponiblePara = disponiblePara;
		this.participantes = participantes;
	}



	public CompetenciaDTO(Integer idCompetencia, String deporte, Integer formaPuntuacion, String[] usuario,
			String nombre, Boolean permiteEmpate, String fechaInicio, String fechaFin, String fechaBaja, String estado,
			String reglamento, String[][] fixture, String[][] disponiblePara) {
		super();
		this.idCompetencia = idCompetencia;
		this.deporte = deporte;
		this.formaPuntuacion = formaPuntuacion;
		this.usuario = usuario;
		this.nombre = nombre;
		this.permiteEmpate = permiteEmpate;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fechaBaja = fechaBaja;
		this.estado = estado;
		this.reglamento = reglamento;
		this.fixture = fixture;
		this.disponiblePara = disponiblePara;
	}

	public CompetenciaDTO(Integer idCompetencia, String deporte, Integer formaPuntuacion, String[] usuario,
			String nombre, Boolean permiteEmpate, String fechaInicio, String estado, String reglamento,
			String[][] disponiblePara) {
		super();
		this.idCompetencia = idCompetencia;
		this.deporte = deporte;
		this.formaPuntuacion = formaPuntuacion;
		this.usuario = usuario;
		this.nombre = nombre;
		this.permiteEmpate = permiteEmpate;
		this.fechaInicio = fechaInicio;
		this.estado = estado;
		this.reglamento = reglamento;
		this.disponiblePara = disponiblePara;
	}

	
	
	public Integer getIdCompetencia() {
		return idCompetencia;
	}


	public String getDeporte() {
		return deporte;
	}

	public void setDeporte(String deporte) {
		this.deporte = deporte;
	}

	public Integer getFormaPuntuacion() {
		return formaPuntuacion;
	}

	public void setFormaPuntuacion(Integer formaPuntuacion) {
		this.formaPuntuacion = formaPuntuacion;
	}

	public String[] getUsuario() {
		return usuario;
	}

	public void setUsuario(String[] usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getPermiteEmpate() {
		return permiteEmpate;
	}

	public void setPermiteEmpate(Boolean permiteEmpate) {
		this.permiteEmpate = permiteEmpate;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(String fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getReglamento() {
		return reglamento;
	}

	public void setReglamento(String reglamento) {
		this.reglamento = reglamento;
	}

	public String[][] getFixture() {
		return fixture;
	}

	public void setFixture(String[][] fixture) {
		this.fixture = fixture;
	}

	public String[][] getDisponiblePara() {
		return disponiblePara;
	}

	public void setDisponiblePara(String[][] disponiblePara) {
		this.disponiblePara = disponiblePara;
	}
	
	
	
	
	
	
}
