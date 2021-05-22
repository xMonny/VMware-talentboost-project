package talentboost.vmware.utms.structure.project.tests.command.exception;

public class InvalidMessageTypeException extends Exception {

    public InvalidMessageTypeException(String message) {
        super(message);
    }

    public InvalidMessageTypeException(String message, Throwable err) {
        super(message, err);
    }
}
