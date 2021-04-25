package com;

//import the model
import model.User;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//for JSON
import com.google.gson.*; 

//set the Service Path
@Path("/User")
public class UserService {
	
	User UserObj = new User();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String ReadUser(){
		return UserObj.ReadUser(); //call the method in model User to read the user
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String AddUser(@FormParam("name") String name, @FormParam("emailAdress") String emailAdress, @FormParam("age") String age, @FormParam("isAdmin") String isAdmin, @FormParam("type") String type, @FormParam("credit") String credit){
		String output = UserObj.AddUser(name, emailAdress, age, isAdmin, type, credit);
		
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String UpdateUser(String UserDetails){

		 JsonObject JSONUserObj = new JsonParser().parse(UserDetails).getAsJsonObject();
		
		 String userId = JSONUserObj.get("userId").getAsString();
		 String name = JSONUserObj.get("name").getAsString();
		 String emailAdress = JSONUserObj.get("emailAdress").getAsString();
		 String age = JSONUserObj.get("age").getAsString();
		 String isAdmin = JSONUserObj.get("isAdmin").getAsString();
		 String type = JSONUserObj.get("type").getAsString();
		 String credit = JSONUserObj.get("credit").getAsString();
		 
		 String output = UserObj.UpdateUser(userId, name, emailAdress, age, isAdmin, type, credit); //update the user details
		 
		 return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String UserDetails){
	
		 JsonObject JSONUserObj = new JsonParser().parse(UserDetails).getAsJsonObject(); //Get JSON object from URL
	
		 String userId = JSONUserObj.get("userId").getAsString();
		 String output = UserObj.DeleteUser(userId);
		 
		 return output;
	}

}
