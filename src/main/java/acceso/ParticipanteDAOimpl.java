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

/**
 * @author Pichi
 *
 */
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
	
	
	
	
	@Override
	public void saveOrUpdate(Participante p) throws SQLException {
		// TODO Auto-generated method stub
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
	public void delete(int id) {
		// TODO Auto-generated method stub
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(DELETE_PARTICIPANTE);
			pstmt.setInt(1, id);
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
	public List<Participante> buscar(int idCompetencia) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		List<Participante> participantes = new ArrayList<Participante>();
		
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SELECT_COMP_PARTICIPANTES);
			pstmt.setInt(1, idCompetencia);
			ResultSet res = pstmt.executeQuery();
			if(!res.next())
				return participantes;
			else {
				do {
					Participante p = new Participante();
					p.setIdParticipante(res.getInt(1));
					p.setNombre(res.getString(2));
					p.setEmail(res.getString(3));
					p.setCompetencia(new Competencia(res.getInt(4)));
					
					
					participantes.add(p);
				} while(res.next());				
			}			
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
		
		
		return participantes;
	}

	@Override
	public Participante buscarPorId(int id) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		Participante p = null;
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SELECT_PARTICIPANTE);
			pstmt.setInt(1, id);
			ResultSet res = pstmt.executeQuery();
			
			
			if(res.next()) {
				p = new Participante(id, res.getString(2), res.getString(3), new Competencia(res.getInt(4)));
			}
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
		
		return p;
		
	}

}
