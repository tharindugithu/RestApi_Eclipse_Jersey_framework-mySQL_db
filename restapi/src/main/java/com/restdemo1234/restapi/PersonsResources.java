package com.restdemo1234.restapi;

import java.util.Arrays;
import java.util.List;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("persons")
public class PersonsResources {
	
  PersonsRepository personsRepository = new PersonsRepository();
  
	
  @GET	
  @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})//content negotiat
  public List<Persons> getPerson() {

	  return personsRepository.getPersons();  
  }
  @GET	
  @Path("person/{id}")
  @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
  public Persons getPersonData(@PathParam("id") int id) {

	  return personsRepository.getPerson(id);  
  }
  @POST
  @Path("person")
  @Produces(MediaType.APPLICATION_JSON)//respond send to server to client only json format
  @Consumes(MediaType.APPLICATION_JSON)//client to server , server accept data type is only json
  public Persons createPerson(Persons p) {
	  System.out.println(p);
	  personsRepository.create(p);
	  return p;
}
  @PUT
  @Path("person")
  @Produces(MediaType.APPLICATION_JSON)//respond send to server to client only json format
  @Consumes(MediaType.APPLICATION_JSON)//client to server , server accept data type is only json
  public Persons updatePerson(Persons p) {
	  System.out.println(p);
	  if(personsRepository.getPerson(p.getId()).getId()==0) {//if user id is not exist then create new person
		  personsRepository.create(p);
	  }else {
		  personsRepository.update(p);
	  }
	 
	  return p;
}
  @DELETE
  @Path("person/{id}")
  @Produces(MediaType.APPLICATION_JSON)//respond send to server to client only json format
  public Persons deletePerson(@PathParam("id") int id) {
	  Persons p = personsRepository.getPerson(id);
	  if(p.getId() != 0) {
		  personsRepository.delete(id);
	  }
	  return p;
}
}
