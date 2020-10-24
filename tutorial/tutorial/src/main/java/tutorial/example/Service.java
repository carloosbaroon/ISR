package tutorial.example;

import java.util.StringTokenizer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

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
		
		final HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IjVlZmZhNzZlZjMzZWNiNWUzNDZiZDUxMmQ3ZDg5YjMwZTQ3ZDhlOTgiLCJ0eXAiOiJKV1QifQ.eyJhdWQiOiIzMjU1NTk0MDU1OS5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsImF6cCI6InRlYW0tM0BkZXNhcnJvbGxvcy10ZXN0LmlhbS5nc2VydmljZWFjY291bnQuY29tIiwiZW1haWwiOiJ0ZWFtLTNAZGVzYXJyb2xsb3MtdGVzdC5pYW0uZ3NlcnZpY2VhY2NvdW50LmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJleHAiOjE2MDIzNDY4NzQsImlhdCI6MTYwMjM0MzI3NCwiaXNzIjoiaHR0cHM6Ly9hY2NvdW50cy5nb29nbGUuY29tIiwic3ViIjoiMTAxOTQyOTY0NzkyMTkxMzcwMTU5In0.m3amz2PZwfFqRpHMwSFTYagJhPog5fZtnoMmaMmr4n3C0dfQJo1CAZUfwBkstmGOISh0PX_MTWt6nnw9RUHTFZSIr7p1D_Haav7AGqZGZspI5216_I4C5ErMgHckF2muebWTIZiyMrINtlRPIY_VTT9gQlS1_vo6qc3JJUDc4Bv2nGkHFgTeU5nef2xZZqeb9rxBjvxWICjEiO10pOSc1YSTosJSwhEqeMQz3izgOsRW5wfAVcesbwD6O-oA1IRO4wVJZs_R27qkLGPcwJ5q3AJehv7Q5cGKx6BL7yOWeRU_zg6htWyKmPtzgjTTtXfRdxY40YIHipesRiujc9MCjg");
		
        final HttpEntity<String> entity = new HttpEntity<String>(headers);
        
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(targetUrl, HttpMethod.GET, entity, String.class);        
	    System.out.println(response.getBody());
        
        String convertedToString = String.valueOf(response.getBody());
        
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
