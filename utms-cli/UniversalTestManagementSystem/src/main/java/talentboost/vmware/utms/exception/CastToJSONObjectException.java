package talentboost.vmware.utms.exception;

public class CastToJSONObjectException extends RuntimeException {

    public CastToJSONObjectException(String message) {
        super(message);
    }

    public CastToJSONObjectException(String message, Throwable err) {
        super(message, err);
    }
}
