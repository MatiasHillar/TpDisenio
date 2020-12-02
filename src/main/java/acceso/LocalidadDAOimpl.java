/**
 * 
 */
package acceso;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import logica.Localidad;
import logica.Provincia;

/**
 * @author Pichi
 *
 */
public class LocalidadDAOimpl implements LocalidadDAO {

	@Override
	public ArrayList<Localidad> buscarPorProvincia(Long id) {
		HttpURLConnection conn = null;
		ArrayList<Localidad> localidades = new ArrayList<Localidad>();
		try {
			URL url = new URL("https://apis.datos.gob.ar/georef/api/localidades-censales?orden=nombre&aplanar=true&campos=basico&max=5000&exacto=true&provincia=" + id.toString());
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			InputStream is = conn.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
		    String line;
		    while ((line = rd.readLine()) != null) {
		        response.append(line);
		        response.append('\r');
		      }
		      rd.close();
		    
		      Object obj = new JSONParser().parse(response.toString());
		      JSONObject json = (JSONObject) obj;
		      JSONArray array = (JSONArray) json.get("localidades_censales");
		      
		      for(int i=0; i<array.size(); i++) {
		    	  JSONObject jsonob = (JSONObject) array.get(i);
		    	  Localidad l = new Localidad((String) jsonob.get("nombre"));
		    	  
		    	  localidades.add(l);
		      }
		      
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(conn != null)
			conn.disconnect();
		}
		
		
		return localidades;
	}

}
