package talentboost.vmware.utms.execution.failure;

import talentboost.vmware.utms.message.failure.Failure;

import java.io.File;

import static talentboost.vmware.utms.message.Message.FILE_NOT_FOUND_ERROR_MESSAGE;
import static talentboost.vmware.utms.message.Message.NOT_FILE_ERROR_MESSAGE;
import static talentboost.vmware.utms.message.Message.FILE_FORMAT_ERROR_MESSAGE;
import static talentboost.vmware.utms.message.Message.FILE_READ_PERMS_ERROR_MESSAGE;

class FailureReceiverFromFile extends FailureReceiver {

    private static final String YAML_PATTERN = "^[a-zA-Z]+\\.yaml$";
    private static final String YML_PATTERN = "^[a-zA-Z]+\\.yml$";

    public FailureReceiverFromFile(String filePath) {
        super(filePath);
    }

    @Override
    public Failure getFailure() {
        File inputFile = new File(argument);
        if (!inputFile.exists()) {
            return new Failure(FILE_NOT_FOUND_ERROR_MESSAGE);
        }
        if (!inputFile.isFile()) {
            return new Failure(String.format(NOT_FILE_ERROR_MESSAGE, inputFile.getName()));
        }
        if (!inputFile.getName().matches(YAML_PATTERN)
                && !inputFile.getName().matches(YML_PATTERN)) {
            return new Failure(FILE_FORMAT_ERROR_MESSAGE);
        }
        if (!inputFile.canRead()) {
            return new Failure(FILE_READ_PERMS_ERROR_MESSAGE);
        }
        return null;
    }
}
