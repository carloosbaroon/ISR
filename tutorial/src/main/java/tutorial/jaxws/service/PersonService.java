package tutorial.jaxws.service;

import tutorial.jaxws.beans.Person;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)

public interface PersonService {
	
	@WebMethod
	public boolean addPerson(Person p);
	
	@WebMethod
	public boolean deletePerson(int id);
	
	@WebMethod
	public Person getPerson(int id);
	
	@WebMethod
	public Person[] getAllPersons();
}

