package tutorial.example;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.xml.ws.Response;

import org.glassfish.jersey.client.ClientConfig;

public class Service extends ExampleSupport {
	
	private int cantidad;
	private static String baseUrl;
	private String targetUrl;
	 

	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public String execute() throws Exception {
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		URI serviceURI = UriBuilder.fromUri(targetUrl).build();
		WebTarget webTarget = client.target(serviceURI);
		
		baseUrl = "https://isr-urhxsjsspa-uc.a.run.app/ISR/";
		targetUrl = baseUrl+ cantidad;
		
		System.out.println("Cantidad:"+cantidad);
		System.out.println("Target:"+targetUrl);
		
		// response
		System.out.println(webTarget.path("rest").path("helloworld").request()
						.accept(MediaType.TEXT_PLAIN).get(Response.class).toString());
		
        return SUCCESS;
    }
	
	
}
