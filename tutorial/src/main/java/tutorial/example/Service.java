package tutorial.example;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.json.Json;
import javax.json.JsonObject;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

 
 


public class Service extends ExampleSupport {
	
	public String llave = "?key=AIzaSyDswtrLx139yeFuE2qH09cx2_i7e20z6UE";
	public int cantidad = 1000;
	public static String baseUrl = "https://isr-urhxsjsspa-uc.a.run.app/ISR/";
	public static String baseUrlIMC = "https://imc-wthdd523za-uc.a.run.app/imc/";
	public static String token = "https://jwt-acces-token-generator-urhxsjsspa-uc.a.run.app/token";
	public String targetUrl;
	public String numCadena;
	public String valor = null;
	public String responseParse = null;
	public String edad;
	public String altura;
	public String peso;
	 

	
	
	public String getValor() {
		return valor;
	}


	public void setValor(String valor) {
		this.valor = valor;
	}


	public String getResponseParse() {
		return responseParse;
	}


	public void setResponseParse(String responseParse) {
		this.responseParse = responseParse;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public String getEdad() {
		return edad;
	}


	public void setEdad(String edad) {
		this.edad = edad;
	}


	public String getAltura() {
		return altura;
	}


	public void setAltura(String altura) {
		this.altura = altura;
	}


	public String getPeso() {
		return peso;
	}


	public void setPeso(String peso) {
		this.peso = peso;
	}


	public String imc() throws Exception {
		System.out.println("Estoy en imc");
		System.out.println("Edad:"+ edad);
		System.out.println("altura"+ altura);
		System.out.println("peso"+ peso);
		
		String endUrl = baseUrlIMC+ edad + "/"+ altura +"/"+ peso;
		Document doc = Jsoup.connect(endUrl).get();
		System.out.println("doc:" + doc);
		Elements ps = doc.select("body");
		System.out.println("ps:" + ps);
		String response = ps.toString();
		
		
		Document doc1 = Jsoup.parse(response);
		Element link = doc.select("body").first();

		valor = doc.body().text();
		System.out.println("BODY: " + valor);
		 
		long tiempo = System.currentTimeMillis(); 
		//create payload
		Map<String, Object> payload = new HashMap<>();
		payload.put("iss", "indice-de-masa-corporal-295502@appspot.gserviceaccount.com");
		payload.put("scope", "https://imc-wthdd523za-uc.a.run.app");
		payload.put("aud", "https://oauth2.googleapis.com/token");
		payload.put("exp", tiempo+10000000);
		payload.put("iat", tiempo);
		

		// read key
		String privateKeyB64 = "-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDd3fITAhHGpj/L\nbT1XCJHu30y3HbnOrikwmp73ztb1YE84vOGuW6aSPM6B9FsyVbbpqsCQwoKxuAzi\nKbhI+meZ92tc7ACSkq7zL4+jJTfFZJM/1tv8D0FxcWnhDFM60YFJCK8tw4VKNS4a\nYRszr95RNmcn6yFvFYxIhCnc1nROXvw1Md5cc2ZLlf+8CxGIxbeIQBiQTX9Rjxom\nQh5Mw6Z60MGkTTIRX4fCp/vB66nF8jJaCeKjjwJTce65pnB05IqW1ji9tEil6pfp\ngCO+NMCwpvlAhjSp033bws+D5DYLOUixrschYwO2pmtawU0i22WMrx3xXMXX8Nnj\nl07THwZxAgMBAAECggEAW3BXjmHx9Y8bB7oT58TOcEZzlZyYsYexzihLkBIdIa/5\ntoaR9IwtUGPjMEgHNe2BnzgSeRbRSnFyTdun6dvZyujfxMI3UjFGDU/ywOhb3eyf\ni1YAABhBfoGhg3DS9JDX2cPMfu3wBy93usN+tISs+YeUaC1N8DxPPqvJnQx9jrZu\ngQm5gqPmhN5dprcmITvy1aBGy7vikEQ6BkSkIhhnamxh6b9W610oAueJDxQCRYho\nbNm/sxOjrK3gPwFoiilxFc2ah/CNcuqtDfmpfOl2E8W0IKep4Iqh+EoFkguJR7jw\n7O8Bwu49kD4WqeajNjmPQ0nRvGjMjyX0JKwgiHwG6QKBgQDxiNS/fupVWAAOziiK\nYrrzKCOdVZeQzInefgU8YALFnUQQvnA1VNDPtMIFrfM7BTf1RgsZUbgtVYlf0jT8\ndfkGzUtDBROT/aFf9JCFmgPT4JWksqlRycT07zxqMdbk76gYWrmt03Lgs8fjIhcJ\n5hhlF9S0B87xJn3m3JnpAphB0wKBgQDrJ5NA6CMqwyA9HzNd9CY28v2Ftj12wSkA\nCTqkVHmqYNJGa9Ag+UDy2uRjZB//9a8Xx6OaZVni4i4eJVGS0nzIBFGTuSrONwrC\nT142OC4+Lc6Rg1gh+kScfyY29CJAJi4tAS6zCaihWRWNeOT6RIuZMgXyIKdkqf6V\n5A0tNNEoKwKBgBAbAaSrP9DlB0v1bzqbDsA8AvPwyyR6nw7Y9FxsQoMO04USTcUg\nnLPc57sdOETNOkf+narYiiFrvLdI+wOCklJjygr0AbQyi1osop5mAK9WIGRVDXlI\nrPtwIGUsYZnuaRUGp67xlI1rL37zvPmKAEJtbl96zc/Cs057kz+4TpLFAoGAVybe\ns2KVjgatTxneLscK1rit7z2AgB/Msz+4ZSkNwnCJ3iGdDGt0f81Scees2G9Uykpq\nPxGrA6RyFSgdmH28U2EOXuJ0k+LgcO17bDrcIJ1XfqSgKE+Z2CpjnmBSxVWhLXrP\ntPkyb0IV883Hh12aI1ffzkH96Bf8y86ud3+ouOsCgYEA0EwAG5r65sgYg8P7pPP6\n7gyD1dWQ3Qx/9e+cRYO+E6pNQmyoVaLGhTr56+YTGEQ2515aueiaeYaBNFLz5GMa\nDbx41klUiMQJ6c9ofD6jxAIJJyZB9gUvFQTWDKEC7A6zxe/9oYuNWSAIwx1hW2CS\nJftgYiqrYuPDEf4u+JoyDhk=\n-----END PRIVATE KEY-----\n";
		privateKeyB64 = privateKeyB64.replace("-----BEGIN PRIVATE KEY-----", "");
		privateKeyB64 = privateKeyB64.replace("-----END PRIVATE KEY-----", "");
		privateKeyB64 = privateKeyB64.replaceAll("\\s+","");
		byte[] privateKeyDecoded = Base64.getDecoder()
		                .decode(privateKeyB64);

		//create key spec
		PKCS8EncodedKeySpec spec =
		                new PKCS8EncodedKeySpec(privateKeyDecoded);

		// create key form spec
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(spec);

		//create signed JWT - JWS
		String jws = Jwts.builder().setClaims(payload).signWith(SignatureAlgorithm.RS256, privateKey).compact();

		System.out.println(jws);
		
		String URL = "https://oauth2.googleapis.com/token";
		URL url = new URL(URL);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		conn.setRequestMethod("POST");
		

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
		
		return SUCCESS;
		
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
