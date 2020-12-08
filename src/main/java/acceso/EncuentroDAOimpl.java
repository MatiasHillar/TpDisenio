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

	private static final String INSERT_ENCUENTRO = "INSERT INTO pruebacomp.ENCUENTRO (id_ronda, participante1,"
			+ " participante2, id_lugar) VALUES (?, ?, ?, ?)";

	private static final String UPDATE_ENCUENTRO = "UPDATE pruebacomp.ENCUENTRO SET id_resultado = ?,"
			+ " participante1 = ?, participante2 = ?, id_ronda = ?, id_lugar = ?"
			+ " WHERE id_encuentro = ?";
	
	private static final String SELECT_ENCUENTROS_RONDA = "SELECT * FROM pruebacomp.ENCUENTRO WHERE id_ronda=?";
	
	
	private ResultadoDAO daoRes = new ResultadoDAOimpl();
	private ParticipanteDAO daoP = new ParticipanteDAOimpl();
	private LugarRealizacionDAO daoL = new LugarRealizacionDAOimpl();
	
	@Override
	public Encuentro saveOrUpdate(Connection conn, Encuentro e) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			if(e.getIdEncuentro()==null) {
				pstmt = conn.prepareStatement(INSERT_ENCUENTRO, Statement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, e.getRonda().getIdRonda());
				pstmt.setInt(2, e.getParticipante1().getIdParticipante());
				pstmt.setInt(3, e.getParticipante2().getIdParticipante());
				pstmt.setInt(4, e.getLugar().getIdLugar());
				pstmt.executeUpdate();
			}
			else {
				pstmt = conn.prepareStatement(UPDATE_ENCUENTRO);
				pstmt.setInt(1, e.getResultado().getId());
				pstmt.setInt(2, e.getParticipante1().getIdParticipante());
				pstmt.setInt(3, e.getParticipante2().getIdParticipante());
				pstmt.setInt(4, e.getRonda().getIdRonda());
				pstmt.setInt(5, e.getLugar().getIdLugar());
				pstmt.setInt(6, e.getIdEncuentro());
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
	
	@Override
	public ArrayList<Encuentro> buscarEncuentrosPorRonda(int idRonda, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Encuentro> encuentros = new ArrayList<Encuentro>();
		try {
			
			pstmt = conn.prepareStatement(SELECT_ENCUENTROS_RONDA);
			pstmt.setInt(1, idRonda);
			rs = pstmt.executeQuery();
			
			
			if(!rs.next()) {
				return encuentros;
			}
			else {
				do {
					Encuentro e = new Encuentro();
					e.setIdEncuentro(rs.getInt(1));
					e.setFecha(rs.getDate("fecha"));
//					e.setRonda(res.getInt(daoRonda.)); setear competencia?
					e.setLugar(daoL.buscarPorId(rs.getInt("id_lugar"), conn));
					e.setParticipante1((daoP.buscarPorId(rs.getInt("participante1"),conn)));
					e.setParticipante2((daoP.buscarPorId(rs.getInt("participante2"),conn)));
					
					encuentros.add(e);
					
				} while(rs.next());		
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstmt!=null) pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return encuentros;
	}

}
