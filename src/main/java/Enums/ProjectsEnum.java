package Enums;

public enum ProjectsEnum {

    BRADESCO("Bradesco", "38291653"),
    DPP_MAIN("DPP-MAIN", "19708777"),
    DPP_RENNER("DPP-Renner", "31055169"),
    ARCA("Arca", "39672097"),
    ZURICH("Zurich", "40954140"),
    ZURICH_2("Zurich2", "41368543"),
    API_PROVIDERS("APIproviders", "42038787"),
    CONSENT_API("Consent-api", "32445869"),
    EBX_TRAINING("EbxTraining", "34880825"),
    HDI("HDI", "34315363"),
    ORIZON("Orizon", "31457286"),
    LGPD("LGPD1", "18926726"),
    RENNER("RENNER", "12696766");

    private final String projectId;
    private final String projectName;

    ProjectsEnum(String projectName, String projectId) {
        this.projectId = projectId;
        this.projectName = projectName;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }
}
