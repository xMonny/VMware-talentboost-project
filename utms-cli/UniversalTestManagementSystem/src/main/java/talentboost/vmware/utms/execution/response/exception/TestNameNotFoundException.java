package talentboost.vmware.utms.execution.response.exception;

public class TestNameNotFoundException extends Exception {

    public TestNameNotFoundException(String message) {
        super(message);
    }

    public TestNameNotFoundException(String message, Throwable err) {
        super(message, err);
    }
}
