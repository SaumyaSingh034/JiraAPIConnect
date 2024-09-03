package JiraAPITests;

import Payload.CreateIssue;
import RequestSpecification.baseURI;
import Token.Token;
import Utility.Constant;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.util.logging.FileHandler;

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
        given()
                .pathParam("key",issueId)
                .header("X-Atlassian-Token","no-check")
                .header("Authorization", "Basic "+ Token.accessToken)
                .multiPart("file", new File("/Users/saumya.singh/IdeaProjects/JiraRestAPIConnect/src/test/resources/Screenshot 2024-09-03 at 2.38.25 PM.png"))
                .log()
                .all()
                .post("rest/api/3/issue/{key}/attachments")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200);

    }
}
