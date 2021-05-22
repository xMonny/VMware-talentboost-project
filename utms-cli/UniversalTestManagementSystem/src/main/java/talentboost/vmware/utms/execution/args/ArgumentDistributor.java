package talentboost.vmware.utms.execution.args;

import talentboost.vmware.utms.execution.args.exception.ArrayLengthOutOfBoundsException;

import static talentboost.vmware.utms.execution.syntax.InputCommandSyntax.*;
import static talentboost.vmware.utms.execution.syntax.InputCommandSyntax.EIGHT_ARGUMENT;
import static talentboost.vmware.utms.message.Message.INVALID_ARGUMENTS_NUMBER_ERROR_MESSAGE;

public class ArgumentDistributor {

    private final ArgumentStorage.ArgumentStorageBuilder argumentStorageBuilder;
    private final String[] args;

    public ArgumentDistributor(String[] args) {
        argumentStorageBuilder = new ArgumentStorage.ArgumentStorageBuilder();
        this.args = args;
    }

    private boolean isServerCommand(String command) {
        return command.matches(SERVER_FULL_COMMAND)
                || command.matches(SERVER_SHORT_COMMAND);
    }

    private boolean isTestNameCommand(String command) {
        return command.matches(TEST_NAME_FULL_COMMAND)
                || command.matches(TEST_NAME_SHORT_COMMAND);
    }

    private boolean isSuiteNameCommand(String command) {
        return command.matches(SUITE_NAME_FULL_COMMAND)
                || command.matches(SUITE_NAME_SHORT_COMMAND);
    }

    private boolean argsLengthOutOfRange() {
        return args.length < FOUR_ARGUMENTS
                || args.length > EIGHT_ARGUMENTS;
    }

    private void setInCaseOfAdditionalCommand(String command) {
        if (isTestNameCommand(command)) {
            argumentStorageBuilder.setTestName(args[FOURTH_ARGUMENT]);
            argumentStorageBuilder.setSeverUrl(args[SIXTH_ARGUMENT]);
        } else if (isSuiteNameCommand(command)) {
            argumentStorageBuilder.setSuiteName(args[FOURTH_ARGUMENT]);
            argumentStorageBuilder.setSeverUrl(args[SIXTH_ARGUMENT]);
        }
    }

    public ArgumentStorage getArgumentStorage() throws ArrayLengthOutOfBoundsException {
        if (argsLengthOutOfRange()) {
            throw new ArrayLengthOutOfBoundsException(INVALID_ARGUMENTS_NUMBER_ERROR_MESSAGE);
        }
        argumentStorageBuilder.setConfigFile(args[SECOND_ARGUMENT]);
        if (args.length == FOUR_ARGUMENTS || args.length == FIVE_ARGUMENTS) {
            argumentStorageBuilder.setConfigFile(args[SECOND_ARGUMENT]);
            argumentStorageBuilder.setSeverUrl(args[FOURTH_ARGUMENT]);
        } else if (args.length == SIX_ARGUMENTS) {
            String secondCommand = args[THIRD_ARGUMENT];
            if (isServerCommand(secondCommand)) {
                argumentStorageBuilder.setSeverUrl(args[FOURTH_ARGUMENT]);
                argumentStorageBuilder.setDebugFlag(args[SIXTH_ARGUMENT]);
            } else {
                setInCaseOfAdditionalCommand(secondCommand);
            }
        } else {
            String secondCommand = args[THIRD_ARGUMENT];
            setInCaseOfAdditionalCommand(secondCommand);
            if (args.length == EIGHT_ARGUMENTS) {
                argumentStorageBuilder.setDebugFlag(args[EIGHT_ARGUMENT]);
            }
        }
        return argumentStorageBuilder.build();
    }
}
