package talentboost.vmware.utms.execution;

import talentboost.vmware.utms.execution.args.ArgumentDistributor;
import talentboost.vmware.utms.execution.args.ArgumentStorage;
import talentboost.vmware.utms.execution.args.exception.ArrayLengthOutOfBoundsException;
import talentboost.vmware.utms.execution.failure.FailureExtractor;
import talentboost.vmware.utms.message.parser.JsonMessageParser;
import talentboost.vmware.utms.execution.response.ResponseExtractor;
import talentboost.vmware.utms.execution.response.ResponseType;
import talentboost.vmware.utms.execution.response.exception.SuiteNameNotFoundException;
import talentboost.vmware.utms.execution.response.exception.TestNameNotFoundException;
import talentboost.vmware.utms.execution.sender.JsonResponseSender;
import talentboost.vmware.utms.structure.Plan;
import talentboost.vmware.utms.structure.project.tests.command.exception.InterruptedThreadException;
import talentboost.vmware.utms.structure.project.tests.command.exception.InvalidMessageTypeException;
import talentboost.vmware.utms.structure.project.tests.command.exception.MessageReaderException;
import talentboost.vmware.utms.structure.project.tests.command.exception.ProcessIOException;
import talentboost.vmware.utms.tool.yaml.YamlTool;
import talentboost.vmware.utms.tool.yaml.exception.ReadYAMLFileException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static talentboost.vmware.utms.message.Message.MALFORMED_URL_ERROR_MESSAGE;
import static talentboost.vmware.utms.message.Message.OPEN_HTTP_CONNECTION_ERROR_MESSAGE;

public class JarMain {

    private static final String EMPTY_STRING = "";
    private static final String NONE_STRING = "none";
    private static final String TRUE_STRING = "true";

    private static void printJsonFailureFor(String message) {
        String failureMessage = JsonMessageParser.generateFailureMessage(message);
        JsonResponseSender.sendToConsole(failureMessage);
    }

    private static String getInputFailureMessageFrom(String[] args) {
        FailureExtractor extractor = new FailureExtractor(args);
        String failureMessage = extractor.extractFailureMessage();
        if (failureMessage != null) {
            return failureMessage;
        }
        return EMPTY_STRING;
    }

    private static String responseAfterYamlPrepare(String fileName) throws ReadYAMLFileException {
        return YamlTool.prepareForClassParsing(fileName);
    }

    private static Plan getPlanFromYamlContent(String yamlContent) {
        return YamlTool.parseYAMLToPlan(yamlContent);
    }

    private static String executeForResponse(Plan plan, ArgumentStorage argumentStorage) {
        ResponseType responseType;
        String argument;
        if (argumentStorage.getTestName() != null) {
            responseType = ResponseType.TEST;
            argument = argumentStorage.getTestName();
        } else if (argumentStorage.getSuiteName() != null) {
            responseType = ResponseType.SUITE;
            argument = argumentStorage.getSuiteName();
        } else {
            responseType = ResponseType.FULL;
            argument = NONE_STRING;
        }

        ResponseExtractor responseExtractor = new ResponseExtractor(responseType, argument, plan);
        String response;
        try {
            response = responseExtractor.getJsonResponse();
        } catch (TestNameNotFoundException | SuiteNameNotFoundException e) {
            response = JsonMessageParser.generateFailureMessage(e.getMessage());
        }
        return response;
    }

    private static HttpURLConnection createHttpUrlConnection(String url) throws IOException {
        URL obj = new URL(url);
        return (HttpURLConnection) obj.openConnection();
    }

    private static void postInformationFor(Plan plan, ArgumentStorage argumentStorage) {

        String response = executeForResponse(plan, argumentStorage);

        String url = argumentStorage.getServerUrl();
        try {
            HttpURLConnection urlConnection = createHttpUrlConnection(url);
            String messageResponse =
                    JsonResponseSender.sendJsonToServer(urlConnection, response);
            if (argumentStorage.getDebugFlag() != null
                    && argumentStorage.getDebugFlag().equals(TRUE_STRING)) {
                JsonResponseSender.sendToConsole(response);
            }
            JsonResponseSender.sendToConsole(messageResponse);
        } catch (MalformedURLException e) {
            printJsonFailureFor(MALFORMED_URL_ERROR_MESSAGE);
        } catch (IOException e) {
            printJsonFailureFor(OPEN_HTTP_CONNECTION_ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {

        String failureMessage = getInputFailureMessageFrom(args);
        if (!failureMessage.isEmpty()) {
            JsonResponseSender.sendToConsole(failureMessage);
            return;
        }
        ArgumentDistributor argumentDistributor = new ArgumentDistributor(args);
        ArgumentStorage argumentStorage;
        try {
            argumentStorage = argumentDistributor.getArgumentStorage();
        } catch (ArrayLengthOutOfBoundsException e) {
            printJsonFailureFor(e.getMessage());
            return;
        }

        String fileName = argumentStorage.getConfigFile();

        try {
            String preparedYamlContent = responseAfterYamlPrepare(fileName);

            Plan plan = getPlanFromYamlContent(preparedYamlContent);
            plan.defineStatus();
            postInformationFor(plan, argumentStorage);
        } catch (ReadYAMLFileException | InterruptedThreadException |
                InvalidMessageTypeException | ProcessIOException | MessageReaderException e) {
            printJsonFailureFor(e.getMessage());
        }
    }
}
