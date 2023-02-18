
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;

import static io.restassured.RestAssured.given;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import java.time.LocalDate;
import java.time.Period;
import java.util.Random;


public class almosafer {

    LocalDate startDate = LocalDate.now().plus(Period.ofDays((new Random().nextInt(90))));
    LocalDate endDate = startDate.plus(Period.ofDays((new Random().nextInt(30))));
    Random rand = new Random();
    int n = rand.nextInt(3);
    int id;
    String City;
    
    
    @Test
    public void City (){ 	
	    
	    		if (n == 0) {
    				  City = "Dammam";
    				  id =44;
    			}
    			else if (n==1) {
    				City ="Dhahran"; 
    				id =58;
    				
    			}
                        else if (n==2) {
    				City = "Al Khobar"; 
    				id = 50;
    			}
	    
    			
    	RestAssured.
        when().get("https://www.almosafer.com/api/accommodation/lookup/city?locale=en").
        then().
        assertThat().statusCode(200).
        and().body("["+n+"]"+".value", is(City)).
        and().body("["+n+"]"+".id", is(id));
    	    
        }
    
    
    @Test
    public void chalet (){
    	String empty = "";
    	given().body("{\"searchCriteria\":[{\"lookupTypeId\":2,\"lookupId\":[44]}],"
    			+ "\"checkIn\":\"" +startDate.toString()+"\""
    			+ ",\"checkOut\":\"" +endDate.toString()+"\""
    			+ ",\"sortBy\":\"rank\","
    			+ "\"sortOrder\":\"DESC\",\"rankType\":\"dynamic\",\"pageNo\":1,\"pageSize\":10}")
    	
    	  .header("content-type", "application/json")
          .header("x-authorization","skdjfh73273$7268u2j89s")
          .header("x-currency","SAR")
          
       	.when().post("https://www.almosafer.com/api/accommodation/property/search").then()
    	.assertThat().statusCode(200).
    	and().body("pageSize", is(10)).
    	and().body("currencyCode", is("SAR"));

    }
}




