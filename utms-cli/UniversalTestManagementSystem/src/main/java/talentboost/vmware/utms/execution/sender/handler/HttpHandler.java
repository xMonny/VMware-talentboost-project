package talentboost.vmware.utms.execution.sender.handler;

import talentboost.vmware.utms.message.parser.JsonMessageParser;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import static talentboost.vmware.utms.message.Message.SERVER_POST_ERROR_MESSAGE;

public class HttpHandler {

    private static final String SUCCESSFUL_SERVER_POST = "Successful post to chosen server.";
    private static final String REQUEST_METHOD = "POST";
    private static final String REQUEST_PROPERTY_TYPE = "Content-Type";
    private static final String REQUEST_PROPERTY_STYLE = "application/json";

    private final HttpURLConnection connection;

    public HttpHandler(HttpURLConnection connection) throws IOException {
        this.connection = connection;
    }

    public String sendJsonPostRequest(String jsonData) {

        try {
            setToJsonPost();
            sendMessage(connection, jsonData);

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                return JsonMessageParser.generateSuccessMessage(SUCCESSFUL_SERVER_POST);
            } else {
                return JsonMessageParser.generateFailureMessage(SERVER_POST_ERROR_MESSAGE);
            }
        } catch (IOException e) {
            return JsonMessageParser.generateFailureMessage(SERVER_POST_ERROR_MESSAGE + e.getMessage());
        }
    }

    private HttpURLConnection setUpHttpURLConnection(String url) throws IOException {
        URL obj = new URL(url);

        return (HttpURLConnection) obj.openConnection();
    }

    private void setToJsonPost() throws ProtocolException {
        connection.setRequestMethod(REQUEST_METHOD);
        connection.setRequestProperty(REQUEST_PROPERTY_TYPE, REQUEST_PROPERTY_STYLE);
    }

    private void sendMessage(HttpURLConnection connection, String message) throws IOException {
        connection.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(message);
        wr.flush();
        wr.close();
    }
}
