package talentboost.vmware.utms.tool.yaml.exception;

public class ReadYAMLFileException extends Exception {

    public ReadYAMLFileException(String message) {
        super(message);
    }

    public ReadYAMLFileException(String message, Throwable err) {
        super(message, err);
    }
}
