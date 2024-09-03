package JiraAPITests;

import Payload.CreateIssue;
import RequestSpecification.baseURI;
import Token.Token;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateSingleBug extends baseURI {
    Response response;
    public String issueId;

    @Test
    public void createIssueSingleBug(){
        Response response = given().
                header("Authorization","Basic "+Token.accessToken)
               .header("Content-Type", "application/json")
                .body("{\n"
                        + "    \"fields\": {\n"
                        + "       \"project\":\n"
                        + "       {\n" + "        " +
                        "  \"key\": \"SCRUM\"\n" + "       }," +
                        "\n"
                        + "       \"summary\": \"Website items are not working- automation Rest Assured\"," +
                        "\n" + "     " +
                        "  \"issuetype\": {\n" + "         " +
                        " \"name\": \"Bug\"\n" + "       }\n" + "   }\n"
                        + "}").log()
                .all()
                .when().post("rest/api/3/issue")
                .then().log()
                .all()
                .assertThat().statusCode(201)
                .extract().response();

        issueId = response.jsonPath().get("id");
        System.out.println(issueId);

    }


    @Test
    public void getIssueIdAndAddAttachement(){

    }
}
