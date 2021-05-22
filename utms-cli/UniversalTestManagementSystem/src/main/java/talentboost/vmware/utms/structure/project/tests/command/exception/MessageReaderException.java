package talentboost.vmware.utms.structure.project.tests.command.exception;

public class MessageReaderException extends Exception {

    public MessageReaderException(String message) {
        super(message);
    }

    public MessageReaderException(String message, Throwable err) {
        super(message, err);
    }
}
