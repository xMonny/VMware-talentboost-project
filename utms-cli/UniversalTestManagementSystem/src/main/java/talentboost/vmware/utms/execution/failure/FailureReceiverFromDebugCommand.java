package talentboost.vmware.utms.execution.failure;

import talentboost.vmware.utms.message.failure.Failure;

import static talentboost.vmware.utms.execution.syntax.InputCommandSyntax.*;
import static talentboost.vmware.utms.message.Message.DEBUG_COMMAND_ERROR_MESSAGE;

public class FailureReceiverFromDebugCommand extends FailureReceiver {

    public FailureReceiverFromDebugCommand(String command) {
        super(command);
    }

    @Override
    public Failure getFailure() {
        if (!argument.matches(DEBUG_FULL_COMMAND) && !argument.matches(DEBUG_SHORT_COMMAND)) {
            return new Failure(String.format(DEBUG_COMMAND_ERROR_MESSAGE, argument));
        }
        return null;
    }
}
