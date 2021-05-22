package talentboost.vmware.utms.execution.failure;

import talentboost.vmware.utms.message.parser.JsonMessageParser;
import talentboost.vmware.utms.message.failure.Failure;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import static talentboost.vmware.utms.message.Message.INVALID_ARGUMENTS_ORDER;

public class FailureExtractor {

    private final String[] arguments;
    private FailureReceiver failureReceiver;

    public FailureExtractor(String[] arguments) {
        this.arguments = arguments;
    }

    private Failure argumentsLengthFailure() {
        failureReceiver = new FailureReceiverFromArgumentsLength(String.valueOf(arguments.length));
        return failureReceiver.getFailure();
    }

    private Failure configCommandFailure() {
        if (arguments.length > 0) {
            failureReceiver = new FailureReceiverFromConfigCommand(arguments[0]);
            return failureReceiver.getFailure();
        }
        return null;
    }

    private Failure fileFailure() {
        if (arguments.length > 1) {
            failureReceiver = new FailureReceiverFromFile(arguments[1]);
            return failureReceiver.getFailure();
        }
        return null;
    }

    private Failure otherArgumentsFailure() {
        if (arguments.length > 2) {
            Failure firstAdditionalCommandFailure = firstAdditionalCommandFailure(arguments[2]);
            if (isFailure(firstAdditionalCommandFailure)) {
                return serverDebugOrderFailure(2);
            } else {
                if (arguments.length > 4) {
                    return serverDebugOrderFailure(4);
                } else {
                    return new Failure(INVALID_ARGUMENTS_ORDER);
                }
            }
        }
        return null;
    }

    private Failure firstAdditionalCommandFailure(String command) {
        failureReceiver = new FailureReceiverFromFirstAdditionalCommand(command);
        return failureReceiver.getFailure();
    }

    private Failure serverCommandFailure(String command) {
        failureReceiver = new FailureReceiverFromServerCommand(command);
        return failureReceiver.getFailure();
    }

    private Failure debugCommandFailure(String command) {
        failureReceiver = new FailureReceiverFromDebugCommand(command);
        return failureReceiver.getFailure();
    }

    private boolean isFailure(Failure failure) {
        return failure != null;
    }

    private Failure serverDebugOrderFailure(int argumentPosition) {
        Failure serverCommandFailure = serverCommandFailure(arguments[argumentPosition]);
        if (isFailure(serverCommandFailure)) {
            return serverCommandFailure;
        } else {
            if (arguments.length > argumentPosition + 2) {
                return debugCommandFailure(arguments[argumentPosition + 2]);
            }
        }
        return null;
    }

    public String extractFailureMessage() {
        Set<Failure> failures = new LinkedHashSet<>();
        failures.add(argumentsLengthFailure());
        failures.add(configCommandFailure());
        failures.add(fileFailure());
        failures.add(otherArgumentsFailure());
        Failure failure = failures.stream()
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
        if (failure != null) {
            return JsonMessageParser.parseToJson(failure);
        }
        return null;
    }
}
