/**
 * 
 */
package acceso;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import logica.Provincia;

/**
 * @author Pichi
 *
 */
public class ProvinciaDAOimplHTTP implements ProvinciaDAO{

	private LocalidadDAO daoLoc = new LocalidadDAOimplHTTP();
	
	@Override
	public List<Provincia> buscarProvincias() {
		HttpURLConnection conn = null;
		ArrayList<Provincia> provincias = new ArrayList<Provincia>();
		try {
			URL url = new URL("https://apis.datos.gob.ar/georef/api/provincias");
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			
			InputStream is = conn.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
		    String line;
		    while ((line = rd.readLine()) != null) {
		        response.append(line);
		        response.append('\r');
		      }
		      rd.close();
		      System.out.println(response);
		    
		      Object obj = new JSONParser().parse(response.toString());
		      JSONObject json = (JSONObject) obj;
		      JSONArray array = (JSONArray) json.get("provincias");
		      
		      for(int i=0; i<array.size(); i++) {
		    	  JSONObject jsonob = (JSONObject) array.get(i);

		    	  Provincia p = new Provincia((String) jsonob.get("nombre"), Long.valueOf((String) jsonob.get("id")),
		    			  daoLoc.buscarPorProvincia(Long.valueOf((String) jsonob.get("id"))));
		    	  provincias.add(p);
		    	  
		      }
		      
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(conn != null)
			conn.disconnect();
		}
		
		
		
		return provincias;
		
	}

	@Override
	public Provincia saveOrUpdate(Connection conn, Provincia p) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
