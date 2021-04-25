package com;

//import the model
import model.Funding;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//for JSON
import com.google.gson.*; 

//set the Service Path
@Path("/Fundings")
public class FundingService {
	
	Funding FundingObj = new Funding(); //Create the Funding object
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String ReadingFunding(){
		return FundingObj.ReadFunding(); //call the Read method in Funding 
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String SetFunding(@FormParam("productId") String productId, @FormParam("rate") String rate, @FormParam("description") String description, @FormParam("period") String period, @FormParam("totalfunding") String totalfunding){
		String output = FundingObj.SetFunding(productId, rate, description, period, totalfunding); // in order to set the values for funding we have to give the productId
		
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String UpdateFunding(String FundingData){

		 JsonObject JSONFundingObj = new JsonParser().parse(FundingData).getAsJsonObject(); //get the JSON OBJECT FROM URL
		
		 String fundingId = JSONFundingObj.get("fundingId").getAsString();
		 String rate = JSONFundingObj.get("rate").getAsString();
		 String description = JSONFundingObj.get("description").getAsString();
		 String period = JSONFundingObj.get("period").getAsString();
		 String totalfunding = JSONFundingObj.get("totalfunding").getAsString();
		 String output = FundingObj.UpdatingFunding(fundingId, rate, description, period, totalfunding); //call the Update method
		 
		 return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String DeleteFunding(String FundingData){
	
		 JsonObject JSONFundingObj = new JsonParser().parse(FundingData).getAsJsonObject();//get the JSON OBJECT FROM URL
	
		 String fundingId = JSONFundingObj.get("fundingId").getAsString();
		 String output = FundingObj.DeleteFunding(fundingId); //Delete the Funding Element form the database
		 
		 return output;
	}

}
