package talentboost.vmware.utms.execution.failure;

import talentboost.vmware.utms.message.failure.Failure;

import static talentboost.vmware.utms.execution.syntax.InputCommandSyntax.*;
import static talentboost.vmware.utms.message.Message.FIRST_ADDITIONAL_COMMAND_ERROR_MESSAGE;

class FailureReceiverFromFirstAdditionalCommand extends FailureReceiver {

    public FailureReceiverFromFirstAdditionalCommand(String command) {
        super(command);
    }

    @Override
    public Failure getFailure() {
        if (!argument.matches(SUITE_NAME_FULL_COMMAND) && !argument.matches(SUITE_NAME_SHORT_COMMAND)
                && !argument.matches(TEST_NAME_FULL_COMMAND) && !argument.matches(TEST_NAME_SHORT_COMMAND)) {
            return new Failure(String.format(FIRST_ADDITIONAL_COMMAND_ERROR_MESSAGE, argument));
        }
        return null;
    }
}
