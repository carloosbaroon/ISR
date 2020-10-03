package tutorial.example;

import java.io.StringReader;
import java.net.URI;
import java.util.StringTokenizer;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.xml.ws.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.mysql.cj.xdevapi.JsonArray;

public class Service extends ExampleSupport {
	
	public int cantidad = 1000;
	public static String baseUrl = "https://isr-urhxsjsspa-uc.a.run.app/ISR/";
	public String targetUrl;
	public String numCadena;
	public String valor = null;
	 

	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public String execute() throws Exception {
		numCadena= Integer.toString(cantidad);
		
		targetUrl = baseUrl+ numCadena; 
		
		
		
		System.out.println("Cantidad:"+numCadena);
		System.out.println("Target:"+targetUrl);
		
		RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> call= restTemplate.getForEntity(targetUrl,String.class);
        //System.out.println(call.getBody());
        
        String convertedToString = String.valueOf(call.getBody());
        
        System.out.println("String: "+convertedToString);
    
        StringTokenizer multiTokenizer = new StringTokenizer(convertedToString, "{:} ");
        
        while (multiTokenizer.hasMoreTokens())
        {
        	System.out.println(multiTokenizer.nextToken());
        	valor = multiTokenizer.nextToken();
        }
        
        System.out.println("Valor: "+ valor);
     
        return SUCCESS;
    }
	

	
}
