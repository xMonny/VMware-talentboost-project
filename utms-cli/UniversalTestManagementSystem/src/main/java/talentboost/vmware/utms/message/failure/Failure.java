package talentboost.vmware.utms.message.failure;

import com.google.gson.annotations.SerializedName;

public class Failure {

    @SerializedName("error")
    private final String message;

    public Failure(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
