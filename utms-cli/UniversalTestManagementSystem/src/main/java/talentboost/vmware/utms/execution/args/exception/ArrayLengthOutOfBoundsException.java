package talentboost.vmware.utms.execution.args.exception;

public class ArrayLengthOutOfBoundsException extends Exception {

    public ArrayLengthOutOfBoundsException(String message) {
        super(message);
    }

    public ArrayLengthOutOfBoundsException(String message, Throwable err) {
        super(message, err);
    }
}
