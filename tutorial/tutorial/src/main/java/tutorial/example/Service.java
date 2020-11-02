package tutorial.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Map;
import java.util.StringTokenizer;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;






public class Service extends ExampleSupport {
	
	public String llave = "?key=AIzaSyDswtrLx139yeFuE2qH09cx2_i7e20z6UE";
	public int cantidad = 1000;
	public static String baseUrl = "https://isr-urhxsjsspa-uc.a.run.app/ISR/";
	public static String token = "https://jwt-acces-token-generator-urhxsjsspa-uc.a.run.app/token";
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
		
	       
		URL url = new URL(token);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestMethod("GET");
		

		BufferedReader br = new BufferedReader(new InputStreamReader(
		        (conn.getInputStream())));

		String output;
		System.out.println("Output from Server .... \n");
		StringBuilder buffer = new StringBuilder();
		while ((output = br.readLine()) != null) {
		    System.out.println(output);
		    buffer.append(output);
		}
		
		
		output = buffer.toString();
		
		System.out.println("Doble"+output);

		conn.disconnect();
		
	      // convert to json object
	      JSONObject json = new JSONObject(output);
	      // print object
	      System.out.println(json.toString());
	      // get value for a key
	      String token = (String) json.get("acces_token");
	      // print value
	      System.out.println(token);
	      
	      //token = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImQwNWVmMjBjNDUxOTFlZmY2NGIyNWQzODBkNDZmZGU1NWFjMjI5ZDEiLCJ0eXAiOiJKV1QifQ.eyJhdWQiOiJodHRwczovL2lzci11cmh4c2pzc3BhLXVjLmEucnVuLmFwcCIsImF6cCI6ImRlc2Fycm9sYWRvcmFkbWluQGRlc2Fycm9sbG9zLXRlc3QuaWFtLmdzZXJ2aWNlYWNjb3VudC5jb20iLCJlbWFpbCI6ImRlc2Fycm9sYWRvcmFkbWluQGRlc2Fycm9sbG9zLXRlc3QuaWFtLmdzZXJ2aWNlYWNjb3VudC5jb20iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiZXhwIjoxNjA0MzQ1NjcxLCJpYXQiOjE2MDQzNDIwNzEsImlzcyI6Imh0dHBzOi8vYWNjb3VudHMuZ29vZ2xlLmNvbSIsInN1YiI6IjEwOTM1NjAzNjgwNzM1MzExMTg0NiJ9.mq5YttLOejPALAhqnDu4jGzhIQjZuL9aztdSxhuzi9JC9qcmA8eRXdxeQEzPdSIeGrxBWJx7BOTdsFlykDcrjfoiaJLxgqwxLXIBOwsnCyUmHTmrrKvk7D0ZctpR06_apt1OTnTGfirSDXTHdDP7T2XpYomCXY5UFa9cWCP3RgXzt1Y85x9AOwpkhn36LIlnBuOsNKSkkhXkj6u5OinyNw5aDn-h4e2rwocOEAAI9uNba_vlBJj06UeIbMFA_Z-MeuLU5dC-R1jh4qNHDZQUk11sjEkDMxm6w1OzfvcOjXegcN9Iiz_CQYTKR9NOLZONsNOMKv2VHjCMNJi2uqFrkQ";
        
	      System.out.println("token"+ token);
	      
		URL url2 = new URL(targetUrl);

		HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
		conn2.setRequestProperty("Authorization",token);

        conn2.setRequestProperty("Content-Type","application/json");
        conn2.setRequestMethod("GET");



		BufferedReader br2 = new BufferedReader(new InputStreamReader(
		        (conn2.getInputStream())));

		String output2;
		System.out.println("Output from Server .... \n");
		StringBuilder buffer2 = new StringBuilder();
		while ((output2 = br2.readLine()) != null) {
		    System.out.println(output2);
		    buffer2.append(output2);
		}
		
		output2 = buffer2.toString();
		
		System.out.println("Doble"+output2);

		conn2.disconnect();
        StringTokenizer multiTokenizer = new StringTokenizer(output2, "{:} ");
        
        while (multiTokenizer.hasMoreTokens())
        {
        	System.out.println(multiTokenizer.nextToken());
        	valor = multiTokenizer.nextToken();
        }
        
        System.out.println("Valor: "+ valor);
     
        return SUCCESS;
    }
	

	
}
