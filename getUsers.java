package apitesting;

	import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
    import org.testng.Assert;
	import io.cucumber.java.en.Given;
	import io.cucumber.java.en.Then;
	import io.cucumber.java.en.When;
	import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
    //import utilities.configreader;
    //import utilities.ConfigReader;
import utillity.ExcelDataInputReader;

	public class getUsers {
		
		String base_URL="https://userserviceapp-f5a54828541b.herokuapp.com/uap";
		 
		Response response,response1,response2,getresponse, getresponse2,Update_response1;
		String userId;
		String userFirstname;
		 //ExcelDataInputReader reader= new ExcelDataInputReader();
		 ExcelDataInputReader excelreader = new ExcelDataInputReader("/Users/praveenag/Desktop/apitesting/excel1.xlsx", "Sheet 1");
		 
		JSONObject request,request1;
		//configreader readConfig=new configreader();
		
		@Given("user get all users using base url with endpoint")
		public void user_get_all_users_using_base_url_with_endpoint() {	
		     RestAssured.baseURI="https://userserviceapp-f5a54828541b.herokuapp.com/uap";
		     
		}

		@When("User make a GET request to get all users with endpoint")
		public void user_make_a_get_request_to_get_all_users_with_endpoint() {
		
			RestAssured.requestSpecification = given();
			response = RestAssured.requestSpecification.auth().
				    basic("Numpy@gmail.com", "userapi@october").when().get("/users").then().extract().response();

		}

		@Then("User get response with all the users and get status code {int}")
		public void user_get_response_with_all_the_users_and_get_status_code(Integer int1) {
		 	System.out.print("All Users : Response--> " + response.getBody().asPrettyString());
			Assert.assertEquals(response.statusCode(), 200);
		     
		}

	
	@Given("Admin create 1st user with base url")
	public void admin_create_1st_user_with_base_url() {
		
   
 		  RestAssured.baseURI = "https://userserviceapp-f5a54828541b.herokuapp.com/uap";
 	
 	
	}
	   

	@When("user make a post request with end point {string}")
	public void user_make_a_post_request_with_end_point(String string) throws IOException {	
 
try {
	        
 
	List<List<String>> data;
    data = ExcelDataInputReader.readExcelData(); // Replace with your actual Excel reading method

    // Assuming the first row is a header and data starts from the second row
    for (int i = 1; i < data.size(); i++)
    {
    	 
        List<String> row = data.get(i);

        // Adjust these indices based on your Excel structure
        String user_first_name = row.get(1);
        String user_last_name =row.get(2);
        String user_contact_number = row.get(3);
        String user_email_id = row.get(4);
        String plotNumber = row.get(5);
        String street =row.get(6);
        String state = row.get(7);
        String country = row.get(8);
        String zipCode = row.get(9);
         

        // Construct the JSON payload
       String jsonBody = String.format(
        	    "{\n" +
        	    "  \"userAddress\": {\n" +
        	    "    \"plotNumber\": \"%s\",\n" +
        	    "    \"street\": \"%s\",\n" +
        	    "    \"state\": \"%s\",\n" +
        	    "    \"country\": \"%s\",\n" +
        	    "    \"zipCode\": \"%s\"\n" +
        	    "  },\n" +
        	    "  \"user_first_name\": \"%s\",\n" +
        	    "  \"user_last_name\": \"%s\",\n" +
        	    "  \"user_contact_number\": \"%s\",\n" +
        	    "  \"user_email_id\": \"%s\"\n" +
        	    "}", 
        	    plotNumber, street, state, country, zipCode, 
        	    user_first_name, user_last_name, user_contact_number, user_email_id
        	);
        
            
//        System.out.println("Request Body : " + jsonBody);
		RestAssured.requestSpecification = given();
		response = RestAssured.requestSpecification.auth().
			    basic("Numpy@gmail.com", "userapi@october").
			    contentType("application/json").
			    body(jsonBody).when().
			    post("/createusers")
			    .then().extract().response();
		
 		JsonPath js = new JsonPath(jsonBody);
	
		
		
		 System.out.println("Response Body: " + response.getBody().asPrettyString());
	        System.out.println("Response Status Code:\n " + response.getStatusCode());
	        userId = js.getString("user_id");
			userFirstname= js.getString("user_first_name");
    }
}
	catch (IOException e) {
			 System.err.println("Error: " + e.getMessage());
				e.printStackTrace();
			}		
    	      
	}
	

	@Then("user should get response body and status code should be {int}")
	public void user_should_get_response_body_and_status_code_should_be(Integer int1) {
		
		
			if (response.getStatusCode() == 201) {
	            // Successfully created
	            System.out.println("User creates successfully.");
	        } else if (response.getStatusCode() == 400) {
	            // Bad request - log error message
	            System.out.println("Error: " + response.getBody().asString());
	        }else if(response.getStatusCode() == 409) {
	            System.out.println("Error: " + response.getBody().asString());

	        }
  
 	}

 
//<--get user by id-->
	
	@When("user make a get request with an end point {string}")
	public void user_make_a_get_request_with_an_end_point(String string) {
		
		 

		getresponse = RestAssured.requestSpecification.auth().
			    basic("Numpy@gmail.com", "userapi@october").when().get("/user/"+userId).then().extract().response();
		
		getresponse = RestAssured.requestSpecification.auth().
			    basic("Numpy@gmail.com", "userapi@october").when().get("/users/username/"+ userFirstname).then().extract().response();

 
	}

	@Then("user should get status code as {string} and responsebody")
	public void user_should_get_status_code_as_and_responsebody(String string) {
		
		System.out.print("  Response--> \n " + getresponse.getBody().asPrettyString());

		Assert.assertEquals(getresponse.statusCode(), 200);

		System.out.println("\n Status code is:" + getresponse.statusCode());
		
		
	
	}
 
	@Then("user should get status code as {string} and responsebody with username")
	public void user_should_get_status_code_as_and_responsebody_with_username(String string) {
		
		System.out.print("  Response--> \n " + getresponse2.getBody().asPrettyString());

		Assert.assertEquals(getresponse2.statusCode(), 200);

		System.out.println("\n Status code is:" + getresponse2.statusCode());
	
	}

	@When("User make a PUT request to update program by using userId\\(update 1st user) with endpoint {string}")
	public void user_make_a_put_request_to_update_program_by_using_user_id_update_1st_user_with_endpoint(String string) {
		
		request1.put("street", "valcan");
		
		System.out.println("Request :" + request.toJSONString());

		RestAssured.requestSpecification = given().header("Content-Type", "application/json")
				.contentType(ContentType.JSON).accept(ContentType.JSON).body(request1.toJSONString());

		 Update_response1 = RestAssured.requestSpecification.when().put("/updateuser/" + userId).then().extract()
				.response();
	}

	@Then("User should get status code as {string} and updated response body")
	public void user_should_get_status_code_as_and_updated_response_body(String string) {
		
 		
		if (Update_response1.getStatusCode() == 200) {
            // Successfully updated
            System.out.println("User updated successfully.");
        } else if (Update_response1.getStatusCode() == 400) {
            // Bad request - log error message
            System.out.println("Error: " + response.getBody().asString());
        } else if (response.getStatusCode() == 404) {
            // Not found
            System.out.println("Error: User not found.");
        } else if (Update_response1.getStatusCode() >= 500) {
            // Server error
            System.out.println("Server error: " + Update_response1.getBody().asString());
        } else {
            // Other unexpected responses
            System.out.println("Unexpected response: " + Update_response1.getBody().asString());
        }

        // Assert that the response code is as expected (e.g., 200)
        Assert.assertEquals(Update_response1.getStatusCode(), 200);
    }
	

	@When("User make a DELETE request with an end point {string}")
	public void user_make_a_delete_request_with_an_end_point(String string) {
		RestAssured.baseURI = base_URL;
		response1 = RestAssured.requestSpecification.when().delete("/deleteuser/" + userId).then().extract().response();

		response2 = RestAssured.requestSpecification.when().delete("/deleteuser/username/" + userFirstname).then().extract().response();
		
	}

	@Then("User gets status code {string} user will be deleted")
	public void user_gets_status_code_user_will_be_deleted(String string) {
		
		Assert.assertEquals(Update_response1.statusCode(), 200);
		System.out.println("\n Status code is:" + Update_response1.statusCode());
		System.out.print("user has been deleted successfully");
	}

	@Then("User gets status code as {string} user will be deleted")
	public void user_gets_status_code_as_user_will_be_deleted(String string) {
		Assert.assertEquals(Update_response1.statusCode(), 200);
		System.out.println("\n Status code is:" + Update_response1.statusCode());
		System.out.print("user has been deleted successfully");
	}

	}

 