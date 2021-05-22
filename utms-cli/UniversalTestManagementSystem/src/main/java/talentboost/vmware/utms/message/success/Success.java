package talentboost.vmware.utms.message.success;

import com.google.gson.annotations.SerializedName;

public class Success {

    @SerializedName("success")
    private final String message;

    public Success(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
