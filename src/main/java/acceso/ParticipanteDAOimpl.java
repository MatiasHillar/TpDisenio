/**
 * 
 */
package acceso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logica.Competencia;
import logica.Participante;


public class ParticipanteDAOimpl implements ParticipanteDAO{

	private static final String SELECT_PARTICIPANTE = "SELECT * FROM pruebacomp.PARTICIPANTE"
			+ " WHERE id_participante = ?";
	
	private static final String SELECT_COMP_PARTICIPANTES = "SELECT * FROM pruebacomp.PARTICIPANTE"
			+ " WHERE id_competencia=?";
	
	private static final String INSERT_PARTICIPANTE = "INSERT INTO pruebacomp.PARTICIPANTE (nombre, email, id_competencia) "
			+ "VALUES(?,?,?)";
	
	private static final String UPDATE_PARTICIPANTE = "UPDATE pruebacomp.PARTICIPANTE SET nombre=? "
			+ "email=? WHERE id_participante=?";
	
	private static final String DELETE_PARTICIPANTE = "DELETE FROM pruebacomp.PARTICIPANTE WHERE id_participante=?";
	
	private static final String DELETE_BY_FILTERS = "DELETE FROM pruebacomp.PARTICIPANTE WHERER"
			+ " nombre = ? AND email = ?";
	
	
	@Override
	public void saveOrUpdate(Participante p) throws SQLException {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		
		try {
			conn.setAutoCommit(false);
			if(p.getIdParticipante() == null) {
				pstmt = conn.prepareStatement(INSERT_PARTICIPANTE);
				pstmt.setString(1, p.getNombre());
				pstmt.setString(2, p.getEmail());
				pstmt.setInt(3, p.getCompetencia().getIdCompetencia());
				pstmt.execute();
				
				
			}
			else {
				pstmt = conn.prepareStatement(UPDATE_PARTICIPANTE);
				pstmt.setString(1, p.getNombre());
				pstmt.setString(2, p.getEmail());
				pstmt.executeUpdate();
				
				
			}
			
			conn.commit();
		}
		catch(SQLException e) {
			conn.rollback();
		}
		
		try {
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Participante p) throws SQLException{
		// TODO Auto-generated method stub
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(DELETE_PARTICIPANTE);
			pstmt.setInt(1, p.getIdParticipante());
			pstmt.execute();
			
			(new FixtureDAOimpl()).deleteByCompetencia(conn, p.getCompetencia().getIdCompetencia());
			
			
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
		
	}

	@Override
	public void deleteByFilters(String nombre, String email) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(DELETE_BY_FILTERS);
			pstmt.setString(1, nombre);
			pstmt.setString(2, email);
			pstmt.execute();
			
			conn.commit();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//busca por competencia
	@Override
	public List<Participante> buscar(int idCompetencia, Connection conn) {
		PreparedStatement pstmt = null;
		List<Participante> participantes = new ArrayList<Participante>();
		ResultSet rs = null;
		
		try {
			
			pstmt = conn.prepareStatement(SELECT_COMP_PARTICIPANTES);
			pstmt.setInt(1, idCompetencia);
			rs = pstmt.executeQuery();
			if(!rs.next())
				return participantes;
			else {
				do {
					Participante p = new Participante();
					p.setIdParticipante(rs.getInt("id_participante"));
					p.setNombre(rs.getString("nombre"));
					p.setEmail(rs.getString("email"));
					p.setCompetencia(new Competencia(rs.getInt("id_competencia")));
					
					
					participantes.add(p);
				} while(rs.next());				
			}			
		
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			if(pstmt!=null)pstmt.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return participantes;
	}

	@Override
	public Participante buscarPorId(int id, Connection conn) {
		PreparedStatement pstmt = null;
		Participante p = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(SELECT_PARTICIPANTE);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {
				p = new Participante(id, rs.getString("nombre"), rs.getString("email"), new Competencia(rs.getInt("id_competencia")));
			}

		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			if(pstmt!=null)pstmt.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return p;
		
	}
	
	
	public void save(Participante p) throws SQLException {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		
		try {
			conn.setAutoCommit(false);
			if(p.getIdParticipante() == null) {
				pstmt = conn.prepareStatement(INSERT_PARTICIPANTE);
				pstmt.setString(1, p.getNombre());
				pstmt.setString(2, p.getEmail());
				pstmt.setInt(3, p.getCompetencia().getIdCompetencia());
				pstmt.execute();
				
				(new FixtureDAOimpl()).deleteByCompetencia(conn, p.getCompetencia().getIdCompetencia());
				
				
			}
			else {
				pstmt = conn.prepareStatement(UPDATE_PARTICIPANTE);
				pstmt.setString(1, p.getNombre());
				pstmt.setString(2, p.getEmail());
				pstmt.executeUpdate();
				
				
			}
			
			conn.commit();
		}
		catch(SQLException e) {
			conn.rollback();
		}
		
		try {
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
