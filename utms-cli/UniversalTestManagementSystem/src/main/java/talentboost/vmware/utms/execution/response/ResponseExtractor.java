package talentboost.vmware.utms.execution.response;

import talentboost.vmware.utms.execution.response.exception.SuiteNameNotFoundException;
import talentboost.vmware.utms.execution.response.exception.TestNameNotFoundException;
import talentboost.vmware.utms.structure.Plan;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ResponseExtractor {

    private final ResponseType responseType;
    private final String argument;
    private final Plan plan;

    public ResponseExtractor(ResponseType responseType, String argument, Plan plan) {
        this.responseType = responseType;
        this.argument = argument;
        this.plan = plan;
    }

    private String responseFromConfigCommand() {
        if (responseType.equals(ResponseType.FULL)) {
            return new ResponseReceiverFromConfigCommand(plan).getResponse();
        }
        return null;
    }

    private String responseFromSuiteNameCommand() throws SuiteNameNotFoundException {
        if (responseType.equals(ResponseType.SUITE)) {
            return new ResponseReceiverFromSuiteNameCommand(plan, argument).getResponse();
        }
        return null;
    }

    private String responseFromTestNameCommand() throws TestNameNotFoundException {
        if (responseType.equals(ResponseType.TEST)) {
            return new ResponseReceiverFromTestNameCommand(plan, argument).getResponse();
        }
        return null;
    }

    public String getJsonResponse() throws TestNameNotFoundException, SuiteNameNotFoundException {
        Set<String> responses = new HashSet<>();
        responses.add(responseFromConfigCommand());
        responses.add(responseFromSuiteNameCommand());
        responses.add(responseFromTestNameCommand());
        return responses.stream()
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }
}
