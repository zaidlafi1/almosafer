
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
    
    @Test
    public void City (){ 		
    			
    	RestAssured.
        when().get("https://www.almosafer.com/api/accommodation/lookup/city?locale=en").
        then().
        assertThat().statusCode(200).
        and().body("[0].value", is("Dammam")).
        and().body("[0].id", is(44));
    	    
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




