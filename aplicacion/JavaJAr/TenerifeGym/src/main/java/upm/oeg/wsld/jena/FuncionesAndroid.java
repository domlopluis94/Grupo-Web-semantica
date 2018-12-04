package upm.oeg.wsld.jena;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;

/**
 * 
 * 
 */
public class FuncionesAndroid
{

	/**
	 * ArrayList con el nombre de las maquinas (modelo) sin repetir nombres 
	 * @return
	 */
	public ArrayList<String> listademaquinas (){
		ArrayList<String> ganso = null;
		
		return ganso;
	}
	
	
	/**
	 * Le pasamos el Nombre de la maquina y nos retorna todas las latitudes y longitudes de las maquinas con ese modelo  
	 * @param Nmaquina
	 * @return retorna array de int , lat , long , lat, long
	 */
	public int [] cordenadasMaquinas (String Nmaquina){
		int [] coordenadas = null;
		return coordenadas;
	}
	/**
	 * nos retorna todas las latitudes y longitudes de las maquinas   
	 * 
	 * @return retorna array de int , lat , long , lat, long
	 */
	public int [] cordenadasTodasLasMaquinas (){
		int [] coordenadas = null;
		return coordenadas;
	}
	
	/**
	 * Le paso el nombre de la maquina y me da su URI
	 * @param Nmaquina
	 * @return
	 */
	public String UriMaquina (String Nmaquina){

		return "";
	}
	
	
	//para pruebas
	public static void main(String args[])
	{
		
	}
	
}
