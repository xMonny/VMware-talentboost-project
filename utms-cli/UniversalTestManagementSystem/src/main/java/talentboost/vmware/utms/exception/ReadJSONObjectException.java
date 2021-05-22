package talentboost.vmware.utms.exception;

public class ReadJSONObjectException extends RuntimeException {

    public ReadJSONObjectException(String message) {
        super(message);
    }

    public ReadJSONObjectException(String message, Throwable err) {
        super(message, err);
    }
}
