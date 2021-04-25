package com;

//import the model
import model.Cart;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//for JSON
import com.google.gson.*; 

//set the Service Path
@Path("/Cart")
public class CartService {
	
	Cart CartObj = new Cart();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String ReadCart(){
		return CartObj.ReadCart(); //call the read method in the Cart model
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String InsertCart(@FormParam("productName") String productName, @FormParam("price") String price, @FormParam("investmentDetails") String investmentDetails, @FormParam("quantity") String quantity, @FormParam("totalPrice") String totalPrice, @FormParam("discount") String discount){
		String output = CartObj.InsertCart(productName, price, investmentDetails, quantity, totalPrice, discount);
		
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String UpdateCart(String CartDetails){

		 JsonObject JSONProductObj = new JsonParser().parse(CartDetails).getAsJsonObject();
		
		 String cartId = JSONProductObj.get("cartId").getAsString();
		 String productName = JSONProductObj.get("productName").getAsString();
		 String price = JSONProductObj.get("price").getAsString();
		 String investmentDetails = JSONProductObj.get("investmentDetails").getAsString();
		 String quantity = JSONProductObj.get("quantity").getAsString();
		 String totalPrice = JSONProductObj.get("totalPrice").getAsString();
		 String discount = JSONProductObj.get("discount").getAsString();
		 String output = CartObj.UpdateCart(cartId, productName, price,investmentDetails, quantity, totalPrice, discount); //call Update method in Cart model
		 
		 return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String DeleteCart(String CartDetails){
	
		 JsonObject JSONProductObj = new JsonParser().parse(CartDetails).getAsJsonObject(); //Get the JSON object from the URL
			
		 String cartId = JSONProductObj.get("cartId").getAsString(); //extract the CartId
		 String output = CartObj.DeleteCart(cartId); //call the method for Delete element form database
		 
		 return output;
	}

}
