package talentboost.vmware.communication.database.storage.exception;

public class IdNullException extends Exception {

    public IdNullException(String message) {
        super(message);
    }

    public IdNullException(String message, Throwable err) {
        super(message, err);
    }
}
