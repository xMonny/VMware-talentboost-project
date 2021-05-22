package talentboost.vmware.utms.structure.project.tests.command.exception;

public class ProcessIOException extends Exception {

    public ProcessIOException(String message) {
        super(message);
    }

    public ProcessIOException(String message, Throwable err) {
        super(message, err);
    }
}
