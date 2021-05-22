package talentboost.vmware.utms.message;

public interface Message {

    String INVALID_ARGUMENTS_NUMBER_ERROR_MESSAGE = "Detected invalid number of arguments.";
    String INVALID_ARGUMENTS_ORDER = "Invalid order of arguments. Correct order is: "
            + "--config(-c) <file> [--suite-name(-sn) <name> or --test-name(-tn) <name>] --server(-s) <url> [--debug(-d) [flag]]";

    String FILE_NOT_FOUND_ERROR_MESSAGE = "Configuration file not found.";
    String NOT_FILE_ERROR_MESSAGE = "Expect configuration file to be input: %s is not a file.";
    String FILE_FORMAT_ERROR_MESSAGE = "Configuration file should be yaml or yml.";
    String FILE_READ_PERMS_ERROR_MESSAGE = "Configuration file cannot be read.";
    String NULL_FILE_ERROR_MESSAGE = "Configuration file is null.";

    String CONFIG_COMMAND_ERROR_MESSAGE = "Expected --config or -c as first command but it was %s.";
    String FIRST_ADDITIONAL_COMMAND_ERROR_MESSAGE = "Commands --suite-name(-sn) or --test-name(-tn) "
            + "can be first additional command: %s is not recognized.";
    String DEBUG_COMMAND_ERROR_MESSAGE = "Command --debug or -d can be last command: %s is not recognized.";
    String SERVER_COMMAND_ERROR_MESSAGE = "Expected --server or -s command but it was %s.";
    String SUITE_NOT_FOUND_ERROR_MESSAGE = "Suite not found.";
    String TEST_NOT_FOUND_ERROR_MESSAGE = "Test not found.";

    String HTTP_URL_CONSTRUCT_ERROR_MESSAGE = "Set http url was failed.";
    String MALFORMED_URL_ERROR_MESSAGE = "URL is malformed.";
    String OPEN_HTTP_CONNECTION_ERROR_MESSAGE = "Error occurred in opening http url connection.";
    String SERVER_POST_ERROR_MESSAGE = "Error occurred in posting to server.";
}

