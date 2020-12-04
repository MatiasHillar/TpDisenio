/**
 * 
 */
package logica;

import java.util.ArrayList;

import acceso.ProvinciaDAOimpl;
import acceso.UsuarioDAOimpl;
import excepciones.UsuarioExistenteException;

/**
 * @author Pichi
 *
 */
public class GestorUsuario {
	
	public static int usuario_autenticado = 2;
	  
	  

	  public static ArrayList<ProvinciaDTO> obtenerProvincias() {
	    ArrayList<ProvinciaDTO> provinciasDTO = new ArrayList<ProvinciaDTO>();
	    ArrayList<Provincia> provincias = (ArrayList<Provincia>) (new ProvinciaDAOimpl()).buscarProvincias();
	    
	    for(Provincia p: provincias) {
	      ProvinciaDTO dto = new ProvinciaDTO(p.getNombre(), p.getLocalidades());
	      provinciasDTO.add(dto);
	    }
	    
	    return provinciasDTO;
	  }
	  
	  private static void setUsuarioAutenticado(int id) {
	    GestorUsuario.usuario_autenticado = id;
	  }
	  
	  
	  public static String autenticarUsuario(String email, String password) {
		  int id = (new UsuarioDAOimpl()).autenticarUsuario(email, password);
		  if(id == -1)
			  return "Los datos de inicio de sesion no son correctos";
		  else {
			  setUsuarioAutenticado(id);
			  return "Inicio de sesion exitoso";
			 
		  }
			 
	  }
	  
	  
	  
	  public static String registrarUsuario(String nombreProvincia, String nombreLocalidad, String nombre, String apellido, String email, String password) {
	    Localidad l = new Localidad(nombreLocalidad);
	    Usuario nuevoUsuario = new Usuario(nombre, apellido, email, password, l);
	    
	    if(nombreProvincia.contentEquals("<Ninguna>"))
	      return "Debe seleccionar una provincia";
	    
	    if(nombreLocalidad.contentEquals("<Ninguna>"))
	      return "Debe seleccionar una localidad";
	    
	    if(nombre.contentEquals("") || apellido.contentEquals("")) {
	      return "Los campos Apellido y Nombre no pueden estar vacios";
	    }
	    
	    if(password.length()<8)
	      return "Su contrase\u00F1a debe tener minimo 8 caracteres";
	    
	    if(!GestorParticipante.validarEmail(email))
	      return "El email ingresado no es valido";
	          
	    
	    try {
	      (new UsuarioDAOimpl()).saveOrUpdate(nuevoUsuario);
	    }
	    catch(UsuarioExistenteException e) {
	      return "Ya existe un usuario registrado con ese email";
	    }
	    
	    
	    
	    return "Usuario registrado con exito!";
	    
	  }
	
}
