package talentboost.vmware.utms.execution.response.exception;

public class SuiteNameNotFoundException extends Exception {

    public SuiteNameNotFoundException(String message) {
        super(message);
    }

    public SuiteNameNotFoundException(String message, Throwable err) {
        super(message, err);
    }
}
