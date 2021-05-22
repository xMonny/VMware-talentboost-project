package talentboost.vmware.communication.data.extraction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import talentboost.vmware.communication.data.model.Plan;
import talentboost.vmware.communication.data.model.Project;

public class Extractor {

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .disableHtmlEscaping()
            .create();

    public static Project extractProject(String jsonPlanString) {
        Plan plan = GSON.fromJson(jsonPlanString, Plan.class);
        return plan.getProject();
    }
}
