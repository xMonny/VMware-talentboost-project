package talentboost.vmware.utms.message.parser;

import talentboost.vmware.utms.message.failure.Failure;
import talentboost.vmware.utms.message.success.Success;

import static talentboost.vmware.utms.tool.gson.PrettyGson.GSON;

public class JsonMessageParser {

    public static String generateFailureMessage(String message) {
        Failure failure = new Failure(message);
        return parseToJson(failure);
    }

    public static String generateSuccessMessage(String message) {
        Success success = new Success(message);
        return parseToJson(success);
    }

    public static String parseToJson(Failure failure) {
        return GSON.toJson(failure);
    }

    public static String parseToJson(Success success) {
        return GSON.toJson(success);
    }
}
