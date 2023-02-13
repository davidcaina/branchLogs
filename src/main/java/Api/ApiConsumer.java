package Api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ApiConsumer {


    public String getCommits(int page, String branch) throws UnirestException {
        HttpResponse<String> response = Unirest.get("https://gitlab.com/api/v4/projects/" + branch + "/repository/commits?per_page=100&page=" + String.valueOf(page))
                .header("PRIVATE-TOKEN", "glpat-Ab5hCTrmCtr6ZpbN82cn")
                .header("Cookie", "_cfuvid=gPlzZvStT13y2mOxpeFq0fA3avKlc7csxMB_HkuZxCk-1676312796811-0-604800000")
                .asString();

        return response.getBody();
    }
}
