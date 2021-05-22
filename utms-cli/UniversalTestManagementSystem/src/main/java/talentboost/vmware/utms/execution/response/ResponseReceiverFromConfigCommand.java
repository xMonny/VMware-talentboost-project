package talentboost.vmware.utms.execution.response;

import talentboost.vmware.utms.structure.Plan;

import static talentboost.vmware.utms.tool.gson.PrettyGson.GSON;

class ResponseReceiverFromConfigCommand extends ResponseReceiver {

    public ResponseReceiverFromConfigCommand(Plan plan) {
        super(plan);
    }

    @Override
    public String getResponse() {
        return GSON.toJson(plan);
    }
}
