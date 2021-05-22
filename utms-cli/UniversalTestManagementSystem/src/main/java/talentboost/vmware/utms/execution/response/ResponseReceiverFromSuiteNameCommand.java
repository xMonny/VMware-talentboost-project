package talentboost.vmware.utms.execution.response;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import talentboost.vmware.utms.execution.response.exception.SuiteNameNotFoundException;
import talentboost.vmware.utms.structure.Plan;

import java.util.Set;

import static talentboost.vmware.utms.execution.syntax.ProjectPropertySyntax.NAME_PROPERTY;
import static talentboost.vmware.utms.message.Message.SUITE_NOT_FOUND_ERROR_MESSAGE;

class ResponseReceiverFromSuiteNameCommand extends ResponseReceiver {

    public ResponseReceiverFromSuiteNameCommand(Plan plan, String argument) {
        super(plan, argument);
    }

    @Override
    public String getResponse() throws SuiteNameNotFoundException {
        Set<JsonObject> suites = getPlanSuites();
        JsonObject searched = suites.stream()
                .filter(s -> s.get(NAME_PROPERTY).getAsString().equals(argument))
                .findFirst()
                .orElse(null);
        if (searched == null) {
            throw new SuiteNameNotFoundException(SUITE_NOT_FOUND_ERROR_MESSAGE);
        }
        JsonArray arraySuite = new JsonArray();
        arraySuite.add(searched);
        return changePlanSuitesWith(arraySuite);
    }
}
