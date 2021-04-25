package com;

//import the model
import model.Product;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//for JSON
import com.google.gson.*; 

//set the Service Path
@Path("/Products")
public class ProductService {
	
	Product ProductObj = new Product();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String ReadProduct(){
		return ProductObj.ReadProduct();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String InsertProduct(@FormParam("productName") String productName, @FormParam("price") String price, @FormParam("investment") String investment, @FormParam("description") String description){
		String output = ProductObj.InsertProduct(productName, price, investment, description);
		
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String ProductData){

		 JsonObject JSONProductObj = new JsonParser().parse(ProductData).getAsJsonObject();
		
		 String productId = JSONProductObj.get("productId").getAsString();
		 String productName = JSONProductObj.get("productName").getAsString();
		 String price = JSONProductObj.get("price").getAsString();
		 String investment = JSONProductObj.get("investment").getAsString();
		 String description = JSONProductObj.get("description").getAsString();
		 
		 String output = ProductObj.UpdateProduct(productId, productName, price, investment, description);
		 
		 return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String ProductData){
	
		 JsonObject JSONProductObj = new JsonParser().parse(ProductData).getAsJsonObject(); 
	
		 String productId = JSONProductObj.get("productId").getAsString();
		 String output = ProductObj.DeleteProduct(productId);
		 
		 return output;
	}

}
