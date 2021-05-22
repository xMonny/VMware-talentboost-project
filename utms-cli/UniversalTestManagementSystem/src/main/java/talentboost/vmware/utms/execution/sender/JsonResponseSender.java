package talentboost.vmware.utms.execution.sender;

import talentboost.vmware.utms.execution.sender.handler.HttpHandler;
import talentboost.vmware.utms.message.parser.JsonMessageParser;

import java.io.IOException;
import java.net.HttpURLConnection;

import static talentboost.vmware.utms.message.Message.HTTP_URL_CONSTRUCT_ERROR_MESSAGE;

public class JsonResponseSender {

    public static void sendToConsole(String jsonMessage) {
        System.out.println(jsonMessage);
    }

    public static String sendJsonToServer(HttpURLConnection connection, String jsonMessage) {
        HttpHandler httpHandler;
        try {
            httpHandler = new HttpHandler(connection);
        } catch (IOException e) {
            return JsonMessageParser.generateFailureMessage(HTTP_URL_CONSTRUCT_ERROR_MESSAGE);
        }
        return httpHandler.sendJsonPostRequest(jsonMessage);
    }
}
