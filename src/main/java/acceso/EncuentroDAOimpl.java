package acceso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import logica.Encuentro;
import logica.Ronda;
import logica.TIPO_RONDA;

public class EncuentroDAOimpl implements EncuentroDAO{

	private static final String INSERT_ENCUENTRO = "INSERT INTO pruebacomp.ENCUENTRO (id_ronda) VALUES (?)";

	private static final String UPDATE_ENCUENTRO = "UPDATE pruebacomp.ENCUENTRO SET id_resultado = ?"
			+ " WHERE id_encuentro = ?";
	
	private static final String SELECT_ENCUENTROS_RONDA = "SELECT * FROM pruebacomp.ENCUENTRO WHERE id_ronda=?";
	
	
	private ResultadoDAO daoRes = new ResultadoDAOimpl();
	
	@Override
	public Encuentro saveOrUpdate(Connection conn, Encuentro e) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			if(e.getIdEncuentro()==null) {
				pstmt = conn.prepareStatement(INSERT_ENCUENTRO, Statement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, e.getRonda().getIdRonda());
				pstmt.executeUpdate();
			}
			else {
				pstmt = conn.prepareStatement(UPDATE_ENCUENTRO);
				pstmt.setInt(1, e.getResultado().getId());
				pstmt.setInt(2, e.getIdEncuentro());
				daoRes.saveOrUpdate(conn, e.getResultado());
			}
			
			
		}
		catch(SQLException e2) {
			throw e2;
		}
	finally {
		try {
			if(pstmt!=null) pstmt.close();	
		}
		catch(SQLException e2){
			e2.printStackTrace();
		}
	}
		return e;
	}

	@Override
	public void delete(Connection conn, int id) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Encuentro> buscarEncuentrosPorRonda(int idRonda) {
		// TODO Auto-generated method stub
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Encuentro> encuentros = new ArrayList<Encuentro>();
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SELECT_ENCUENTROS_RONDA);
			pstmt.setInt(1, idRonda);
			ResultSet res = pstmt.executeQuery();
			
			
			if(!res.next()) {
				return encuentros;
			}
			else {
				do {
					Encuentro e = new Encuentro();
					e.setIdEncuentro(res.getInt(1));
					e.setFecha(res.getDate("fecha"));
					e.setLugar((new LugarRealizacionDAOimpl()).buscarPorId(res.getInt("id_lugar")));
					e.setParticipante1(((new ParticipanteDAOimpl()).buscarPorId(res.getInt("participante1"))));
					e.setParticipante2(((new ParticipanteDAOimpl()).buscarPorId(res.getInt("participante2"))));
					
					encuentros.add(e);
					
				} while(res.next());		
			}
			conn.commit();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return encuentros;
	}

}
