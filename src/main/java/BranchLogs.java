import Api.ApiConsumer;
import Constants.Constants;
import Enums.ProjectsEnum;
import Utils.DateUtils;
import com.onwbp.org.json.JSONArray;
import com.onwbp.org.json.JSONException;
import com.onwbp.org.json.JSONObject;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class BranchLogs {

    // Variáveis:
    long minutes = 0;

    // Resources:
    private final DateUtils dateUtils = new DateUtils();
    private final ApiConsumer apiConsumer = new ApiConsumer();

    public void calculaTotalHoras() {
        HashMap<String, String> branchs = initProjectList();
        for (Map.Entry<String, String> entrySet : branchs.entrySet()) {
            calculateHours(entrySet.getKey(), entrySet.getValue());
        }
        System.out.println("Total de horas: " + minutes / 60);
    }


    public void calculateHours(String branch, String id) {
        int page = 1;
        long projectMinutes = 0;
        while (true) {
            try {
                JSONArray jsonArray = new JSONArray(apiConsumer.getCommits(page, id));
                long resultMinutes = getMinutes(jsonArray);
                minutes = minutes + resultMinutes;
                if (jsonArray.length() == 0) {
                    break;
                }
                projectMinutes = projectMinutes + resultMinutes;

            } catch (Exception e) {
                break;
            }
            page++;
        }
        System.out.println("[" + branch + "] Total de horas: " + projectMinutes / 60);

    }

    public long getMinutes(JSONArray jsonArray) throws ParseException, JSONException {

        long finalResult = 0;

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);
            String email = String.valueOf(json.get(Constants.JSON.EMAIL_KEY));
            String commitDate = String.valueOf(json.get(Constants.JSON.COMMIT_KEY));

            if (!email.equals(Constants.EMAILS.DEIVIS) && !email.equals(Constants.EMAILS.DAVID)) {
                continue;
            }

            long result = dateUtils.buildDate(commitDate);
            if (result <= 0) {
                continue;
            }
            finalResult = finalResult + result;
        }
        return finalResult;
    }


    public HashMap<String, String> initProjectList() {
        HashMap<String, String> branchs = new HashMap<>();
        branchs.put(ProjectsEnum.BRADESCO.getProjectName(), ProjectsEnum.BRADESCO.getProjectId());
        branchs.put(ProjectsEnum.DPP_MAIN.getProjectName(), ProjectsEnum.DPP_MAIN.getProjectId());
        branchs.put(ProjectsEnum.DPP_RENNER.getProjectName(), ProjectsEnum.DPP_RENNER.getProjectId());
        branchs.put(ProjectsEnum.ARCA.getProjectName(), ProjectsEnum.ARCA.getProjectId());
        branchs.put(ProjectsEnum.ZURICH.getProjectName(), ProjectsEnum.ZURICH.getProjectId());
        branchs.put(ProjectsEnum.ZURICH_2.getProjectName(), ProjectsEnum.ZURICH_2.getProjectId());
        branchs.put(ProjectsEnum.API_PROVIDERS.getProjectName(), ProjectsEnum.API_PROVIDERS.getProjectId());
        branchs.put(ProjectsEnum.CONSENT_API.getProjectName(), ProjectsEnum.CONSENT_API.getProjectId());
        branchs.put(ProjectsEnum.EBX_TRAINING.getProjectName(), ProjectsEnum.EBX_TRAINING.getProjectId());
        branchs.put(ProjectsEnum.HDI.getProjectName(), ProjectsEnum.HDI.getProjectId());
        branchs.put(ProjectsEnum.ORIZON.getProjectName(), ProjectsEnum.ORIZON.getProjectId());
        branchs.put(ProjectsEnum.LGPD.getProjectName(), ProjectsEnum.LGPD.getProjectId());
        branchs.put(ProjectsEnum.RENNER.getProjectName(), ProjectsEnum.RENNER.getProjectId());
        return branchs;
    }
}