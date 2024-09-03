package RequestSpecification;

import Utility.Constant;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class baseURI {

    @BeforeMethod
    public void setUpBaseURI(){
        RestAssured.baseURI = Constant.baseURI;

    }
}
