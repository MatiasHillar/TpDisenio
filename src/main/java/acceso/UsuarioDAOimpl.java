package acceso;

import java.sql.Connection;	
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import excepciones.UsuarioExistenteException;
import logica.Localidad;
import logica.Provincia;
import logica.Usuario;

public class UsuarioDAOimpl implements UsuarioDAO {
	private static final String INSERT_USUARIO = "INSERT INTO pruebacomp.usuario (NOMBRE, APELLIDO, "
			+ "EMAIL, PASSWORD, LOCALIDAD) "
			+ "VALUES (?, ?, crypt(?, 'bf'), crypt(?, 'bf'), ?)";
	private static final String UPDATE_USUARIO = "UPDATE pruebacomp.usuario SET NOMBRE = ?, APELLIDO = ?"
			+ ", EMAIL = crypt(?, 'bf'), PASSWORD = crypt(?, 'bf'), LOCALIDAD = ?"
			+ " WHERE ID_USUARIO = ?"; 
	private static final String DELETE_USUARIO = "DELETE FROM pruebacomp.usuario WHERE "
			+ "ID_USUARIO = ?";
	private static final String SELECT_USUARIO = "SELECT * FROM pruebacomp.usuario WHERE"
			+ " ID_USUARIO = ?";
	
	private static final String AUTENTICAR_USER = "SELECT * FROM pruebacomp.usuario WHERE password=crypt(?, password) AND email=crypt(?, email)";
	
	private static final String CHECK_EXISTENCE = "SELECT email=crypt(?, email) FROM pruebacomp.usuario";
	
	
	private ProvinciaDAO daoProv = new ProvinciaDAOimplSQL();
	private LocalidadDAO daoLoca = new LocalidadDAOimplSQL();
	
	public Usuario saveOrUpdate(Usuario u) throws UsuarioExistenteException, SQLException {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			conn.setAutoCommit(false);
			if(u.getIdUsuario() == null) {
				if(checkExistence(u))
					throw (new UsuarioExistenteException());
				else {
					Provincia pUser = u.getLocalidad().getProvincia();
					u.getLocalidad().setProvincia(daoProv.saveOrUpdate(conn, pUser));
					
					Localidad lUser = u.getLocalidad();
					u.setLocalidad(daoLoca.saveOrUpdate(conn, lUser));
					
					
					pstmt = conn.prepareStatement(INSERT_USUARIO);
					pstmt.setString(1, u.getNombre());
					pstmt.setString(2, u.getApellido());
					pstmt.setString(3, u.getEmail());
					pstmt.setString(4, u.getPassword());
					pstmt.setInt(5, u.getLocalidad().getIdLocalidad());
					pstmt.executeUpdate();
				}
			}
			else {
				pstmt = conn.prepareStatement(UPDATE_USUARIO);
				pstmt.setString(1, u.getNombre());
				pstmt.setString(2, u.getApellido());
				pstmt.setString(3, u.getEmail());
				pstmt.setString(4, u.getPassword());
				pstmt.setString(5, u.getLocalidad().getNombre());
				pstmt.setInt(6, u.getIdUsuario());
				pstmt.executeUpdate();

			}
			
			conn.commit();
		}
		catch(SQLException e) {
			conn.rollback();
			e.printStackTrace();
		}
		try {
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return u;
	}

	public void delete(int id) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(DELETE_USUARIO);
			pstmt.setInt(1, id);
			pstmt.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Usuario> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	private Boolean checkExistence(Usuario u) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
		pstmt = conn.prepareStatement(CHECK_EXISTENCE);
		pstmt.setString(1, u.getEmail());
		
		ResultSet rs = pstmt.executeQuery()	;
		while(rs.next()) {
			if(rs.getBoolean(1))
				return true;
		}
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public Usuario buscarPorId(int id) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Usuario us= null;
		try {
			pstmt = conn.prepareStatement(SELECT_USUARIO,ResultSet.TYPE_SCROLL_INSENSITIVE,	ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(!rs.first()) System.out.println("no existe");;
			us = new Usuario();
			
		us.setApellido(rs.getString("APELLIDO"));
		us.setNombre(rs.getString("NOMBRE"));
		us.setEmail(rs.getString("EMAIL"));
		us.setIdUsuario(rs.getInt("ID_USUARIO"));
		us.setPassword(rs.getString("PASSWORD"));
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstmt!=null) pstmt.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return us;
	}
	
	
	
	public Usuario autenticarUsuario(String email, String password) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Usuario u = null;
		try {
			pstmt = conn.prepareStatement(AUTENTICAR_USER, ResultSet.TYPE_SCROLL_INSENSITIVE,	ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, password);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			if(rs.first()) {
				u = new Usuario();
				u.setIdUsuario(rs.getInt("id_usuario"));
				u.setNombre(rs.getString("nombre"));
				u.setApellido(rs.getString("apellido"));
			
			}
				
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstmt!=null)
					pstmt.close();
				if(conn!=null)
					conn.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return u;
		
		
	}
		

}
