package talentboost.vmware.utms.execution.failure;

import talentboost.vmware.utms.message.failure.Failure;

abstract class FailureReceiver {

    protected final String argument;

    public FailureReceiver(String argument) {
        this.argument = argument;
    }

    public abstract Failure getFailure();
}
