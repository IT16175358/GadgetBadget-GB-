package com;

//import the model
import model.Rating;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//for JSON
import com.google.gson.*; 

//set the Service Path
@Path("/Ratings")
public class RatingService {
	
	Rating RatingObj = new Rating(); // create the Object RatingObj 
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String ReadRating(){
		return RatingObj.ReadRating(); //call the readRating method define in Rating model.
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String AddRating(@FormParam("productId") String productId, @FormParam("ratingValue") String ratingValue, @FormParam("review") String review, @FormParam("overallRating") String overallRating){
		String output = RatingObj.AddRating(productId, ratingValue, review, overallRating); //product id must be present in order to add Rating for a product 
		
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String UpdateRating(String RatingData){
		
		 JsonObject JSONRatingObj = new JsonParser().parse(RatingData).getAsJsonObject(); //get values as JSON 
		 //save data in Strings
		 String ratingId = JSONRatingObj.get("ratingId").getAsString();
		 String ratingValue = JSONRatingObj.get("ratingValue").getAsString();
		 String review = JSONRatingObj.get("review").getAsString();
		 String overallRating = JSONRatingObj.get("overallRating").getAsString();
		 
		 String output = RatingObj.UpdateRatings(ratingId, ratingValue, review, overallRating); //update ratings with given ratingId
		 
		 return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String DeleteRating(String RatingData){
	
		 JsonObject JSONRatingObj = new JsonParser().parse(RatingData).getAsJsonObject(); //get values as JSON 
	
		 String ratingId = JSONRatingObj.get("ratingId").getAsString();
		 
		 String output = RatingObj.DeleteRating(ratingId); //call delete method in rating model
		 
		 return output;
	}

}
