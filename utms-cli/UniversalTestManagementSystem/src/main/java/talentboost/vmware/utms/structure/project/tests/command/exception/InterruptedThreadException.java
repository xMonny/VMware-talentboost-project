package talentboost.vmware.utms.structure.project.tests.command.exception;

public class InterruptedThreadException extends Exception {

    public InterruptedThreadException(String message) {
        super(message);
    }

    public InterruptedThreadException(String message, Throwable err) {
        super(message, err);
    }
}
