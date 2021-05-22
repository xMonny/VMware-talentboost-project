package talentboost.vmware.utms.execution.response;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import talentboost.vmware.utms.execution.response.exception.SuiteNameNotFoundException;
import talentboost.vmware.utms.execution.response.exception.TestNameNotFoundException;
import talentboost.vmware.utms.structure.Plan;
import talentboost.vmware.utms.structure.project.tests.TestSuite;

import java.lang.reflect.Type;
import java.util.LinkedHashSet;
import java.util.Set;

import static talentboost.vmware.utms.execution.syntax.ProjectPropertySyntax.PROJECT_PROPERTY;
import static talentboost.vmware.utms.execution.syntax.ProjectPropertySyntax.SUITES_PROPERTY;
import static talentboost.vmware.utms.tool.gson.PrettyGson.GSON;

abstract class ResponseReceiver {

    private static final JsonParser parser = new JsonParser();
    private JsonObject planJson;
    private JsonObject projectJson;
    private JsonArray suitesArray;
    protected final Type setType = new TypeToken<LinkedHashSet<JsonObject>>(){}.getType();

    protected final String argument;
    protected Plan plan;

    public ResponseReceiver(Plan plan) {
        this.argument = null;
        setPlan(plan);
        prepareJsonObjects();
    }

    public ResponseReceiver(Plan plan, String argument) {
        setPlan(plan);
        this.argument = argument;
        prepareJsonObjects();
    }

    private void setPlan(Plan plan) {
        this.plan = plan;
    }

    private void prepareJsonObjects() {
        String planJsonString = GSON.toJson(plan);
        this.planJson = parser.parse(planJsonString).getAsJsonObject();
        this.projectJson = planJson.getAsJsonObject(PROJECT_PROPERTY);
        this.suitesArray = projectJson.getAsJsonArray(SUITES_PROPERTY);
    }

    protected Set<JsonObject> getPlanSuites() {
        return GSON.fromJson(suitesArray, setType);
    }

    protected String changePlanSuitesWith(JsonElement suite) {
        projectJson.remove(SUITES_PROPERTY);
        projectJson.add(SUITES_PROPERTY, suite);
        planJson.remove(PROJECT_PROPERTY);
        planJson.add(PROJECT_PROPERTY, projectJson);

        return GSON.toJson(planJson);
    }

    public abstract String getResponse() throws SuiteNameNotFoundException, TestNameNotFoundException;
}
