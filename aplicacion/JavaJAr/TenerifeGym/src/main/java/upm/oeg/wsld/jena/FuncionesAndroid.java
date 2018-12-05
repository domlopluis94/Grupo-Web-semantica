package upm.oeg.wsld.jena;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;
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
		String filename = "bin/resources/bio_saludables_final.rdf";
		String ns = "https://www.santacruzdetenerife.es/";
		// Create an empty model
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
		
		
		// Use the FileManager to find the input file
		InputStream in = FileManager.get().open(filename);

		if (in == null)
			throw new IllegalArgumentException("File: "+filename+" not found");
		
		model.read(in,null);
		
		OntClass maquina = model.getOntClass(ns+"Clases/MaquinaPublica");
		ExtendedIterator ite = maquina.listInstances();
		
		Individual instance = null;
		Property propiedad = model.getOntProperty(ns+"Propiedades/HasModel");
		while(ite.hasNext()) 
		{
			instance = (Individual) ite.next();
			System.out.println("LocalName: " + instance.getPropertyValue(propiedad) + " " + instance.getURI());
		}
		
		/*
		String PREFIX = "PREFIX clases: <https://www.santacruzdetenerife.es/Clases/> \n"
				+ "PREFIX propiedades: <https://www.santacruzdetenerife.es/Propiedades/> \n"
				+ "PREFIX ns:<http://www.santacruzdetenerife.es/>";
		
		String queryString2 = PREFIX + " SELECT ?x WHERE {?x a clases:MaquinaPublica} ";
		Query query = QueryFactory.create(queryString2);
		QueryExecution qexec = QueryExecutionFactory.create(query, model) ;
		ResultSet results = qexec.execSelect() ;
		while (results.hasNext())
		{
			QuerySolution binding = results.nextSolution();
			Resource subj = (Resource) binding.get("x");
			Literal fn = binding.getLiteral("x");
			System.out.println("Subject: " + subj.getURI() + " " + fn);
		}
		*/
	}
	
}
