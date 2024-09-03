package Payload;

public class CreateIssue {

    public static String createSingleBug(String key, String summary, String description, String issueType){
        return "{\"fields\":{\"project\":{\"key\":\""+key+"\"}," +
                "\"summary\":\""+summary+".\"," +
                "\"description\":\""+description+"\"," +
                "\"issuetype\":{\"name\":\""+issueType+"\"}}}";
    }
}
