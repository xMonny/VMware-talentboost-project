package talentboost.vmware.utms.execution.failure;

import talentboost.vmware.utms.message.failure.Failure;

import static talentboost.vmware.utms.execution.syntax.InputCommandSyntax.*;
import static talentboost.vmware.utms.message.Message.INVALID_ARGUMENTS_NUMBER_ERROR_MESSAGE;

class FailureReceiverFromArgumentsLength extends FailureReceiver {

    public FailureReceiverFromArgumentsLength(String argumentsLength) {
        super(argumentsLength);
    }

    @Override
    public Failure getFailure() {
        int argumentLength = Integer.parseInt(argument);
        if (argumentLength < FOUR_ARGUMENTS || argumentLength > EIGHT_ARGUMENTS) {
            return new Failure(INVALID_ARGUMENTS_NUMBER_ERROR_MESSAGE);
        }
        return null;
    }
}
