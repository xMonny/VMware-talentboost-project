package talentboost.vmware.utms.execution.failure;

import talentboost.vmware.utms.message.failure.Failure;

import static talentboost.vmware.utms.execution.syntax.InputCommandSyntax.CONFIG_FULL_COMMAND;
import static talentboost.vmware.utms.execution.syntax.InputCommandSyntax.CONFIG_SHORT_COMMAND;
import static talentboost.vmware.utms.message.Message.CONFIG_COMMAND_ERROR_MESSAGE;

class FailureReceiverFromConfigCommand extends FailureReceiver {

    public FailureReceiverFromConfigCommand(String command) {
        super(command);
    }

    @Override
    public Failure getFailure() {
        if (!argument.matches(CONFIG_FULL_COMMAND) && !argument.matches(CONFIG_SHORT_COMMAND)) {
            return new Failure(String.format(CONFIG_COMMAND_ERROR_MESSAGE, argument));
        }
        return null;
    }
}
