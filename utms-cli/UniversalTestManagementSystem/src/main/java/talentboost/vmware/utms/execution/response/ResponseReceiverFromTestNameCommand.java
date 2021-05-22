package talentboost.vmware.utms.execution.response;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import talentboost.vmware.utms.execution.response.exception.TestNameNotFoundException;
import talentboost.vmware.utms.structure.Plan;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import static talentboost.vmware.utms.execution.syntax.ProjectPropertySyntax.TESTS_PROPERTY;
import static talentboost.vmware.utms.execution.syntax.ProjectPropertySyntax.NAME_PROPERTY;
import static talentboost.vmware.utms.message.Message.TEST_NOT_FOUND_ERROR_MESSAGE;
import static talentboost.vmware.utms.tool.gson.PrettyGson.GSON;

class ResponseReceiverFromTestNameCommand extends ResponseReceiver {

    public ResponseReceiverFromTestNameCommand(Plan plan, String argument) {
        super(plan, argument);
    }

    private Set<JsonObject> filterByTestName(Set<JsonObject> suites, String testName) {
        suites.forEach(s -> {
            JsonObject currentSuite = s.getAsJsonObject();
            JsonArray testsArray = currentSuite.getAsJsonArray(TESTS_PROPERTY);
            Set<JsonObject> testsSet = GSON.fromJson(testsArray, setType);
            JsonObject searched = testsSet.stream()
                    .filter(t -> t.get(NAME_PROPERTY).getAsString().equals(testName))
                    .findFirst()
                    .orElse(null);
            currentSuite.remove(TESTS_PROPERTY);
            if (searched != null) {
                JsonArray arrayTest = new JsonArray();
                arrayTest.add(searched);
                currentSuite.add(TESTS_PROPERTY, arrayTest);
            }
        });

        return suites.stream()
                .filter(s -> s.get(TESTS_PROPERTY) != null)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private JsonArray convertToJsonArray(Set<JsonObject> suites) {
        JsonArray suitesArray = new JsonArray();
        for (JsonObject remained : suites) {
            suitesArray.add(remained);
        }
        return suitesArray;
    }

    @Override
    public String getResponse() throws TestNameNotFoundException {
        Set<JsonObject> suites = getPlanSuites();
        suites = filterByTestName(suites, argument);
        if (suites.isEmpty()) {
            throw new TestNameNotFoundException(TEST_NOT_FOUND_ERROR_MESSAGE);
        }
        JsonArray suitesArray = convertToJsonArray(suites);
        return changePlanSuitesWith(suitesArray);
    }
}
