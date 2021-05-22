package talentboost.vmware.utms.structure.project.status;

import com.google.gson.annotations.SerializedName;

public enum Status {

    @SerializedName("passed")
    PASSED,
    @SerializedName("failed")
    FAILED,
    @SerializedName("skipped")
    SKIPPED
}
