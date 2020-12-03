package acceso;

import java.sql.Connection;	
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import logica.ResultadResultadoFinal;
import logica.Resultado;
import logica.ResultadoPuntuacion;
import logica.ResultadoSet;
import logica.Unset;

public class ResultadoDAOimpl implements ResultadoDAO {

	private static final String INSERT_RESULTADO = "INSERT INTO pruebacomp.RESULTADO id_encuentro VALUES(?)";
	
	private static final String INSERT_RES_PUNTUACION = "INSERT INTO pruebacomp.RESULTADO_PUNTUACION VALUES (?)";
	
	private static final String INSERT_RES_FINAL = "INSERT INTO pruebacomp.RESULTADO_RESULT_FINAL VALUES (?)";
	
	private static final String INSERT_RES_SETS = "INSERT INTO pruebacomp.RESULTADO_SET VALUES (?)";
	
	private static final String INSERT_SET = "INSERT INTO pruebacomp.SET (id_resultado_set, puntos_participante1,"
			+ " puntos_participant2, VALUES (?, ?, ?)";
	
	private static final String UPDATE_RES_FINAL = "UPDATE pruebacomp.RESULTADO_RESULT_FINAL SET ?"
			+ " WHERE id_resultado = ?";
	
	@Override
	public Resultado saveOrUpdate(Connection conn, Resultado res) {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet generatedKeys = null;
		int key;
		try{
			if(res.getId()==null) {
				//QUITAR SERIAL A CLASES HIJAS
					pstmt = conn.prepareStatement(INSERT_RESULTADO, Statement.RETURN_GENERATED_KEYS);
					pstmt.setInt(1, res.getE().getIdEncuentro());
					pstmt.executeUpdate();
					generatedKeys = pstmt.getGeneratedKeys();
					if(generatedKeys.next()) {
						key = generatedKeys.getInt(1);
						
						if(res.getClass() == ResultadoPuntuacion.class) {
							pstmt1 = conn.prepareStatement(INSERT_RES_PUNTUACION);
						}
						else if(res.getClass() == ResultadResultadoFinal.class) {
							pstmt1 = conn.prepareStatement(INSERT_RES_FINAL);
						}
						else {
							pstmt1 = conn.prepareStatement(INSERT_RES_SETS);
							pstmt2 = conn.prepareStatement(INSERT_SET);
							pstmt2.setInt(1, key);
							for(Unset s : ((ResultadoSet)res).getSets()) {
								pstmt2.setInt(2, s.getTantosParticipante1());
								pstmt2.setInt(3, s.getTantosParticipante2());
								pstmt2.executeUpdate();
							}
							
						}
						pstmt1.setInt(1, key);
						pstmt1.executeUpdate();
					}
			}
			else {
				if(res.getClass() == ResultadResultadoFinal.class) {
					pstmt1 = conn.prepareStatement(UPDATE_RES_FINAL);
					pstmt1.setString(1, ((ResultadResultadoFinal)res).getGanador().toString());
					pstmt1.setInt(2, res.getId());
				}
			}
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
	return res;
	}

	@Override
	public void delete(Connection conn, int id) {
		// TODO Auto-generated method stub

	}

}
