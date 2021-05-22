package talentboost.vmware.utms.execution.failure;

import talentboost.vmware.utms.message.failure.Failure;

import static talentboost.vmware.utms.execution.syntax.InputCommandSyntax.*;
import static talentboost.vmware.utms.message.Message.SERVER_COMMAND_ERROR_MESSAGE;

public class FailureReceiverFromServerCommand extends FailureReceiver {

    public FailureReceiverFromServerCommand(String command) {
        super(command);
    }

    @Override
    public Failure getFailure() {
        if (!argument.matches(SERVER_FULL_COMMAND) && !argument.matches(SERVER_SHORT_COMMAND)) {
            return new Failure(String.format(SERVER_COMMAND_ERROR_MESSAGE, argument));
        }
        return null;
    }
}
