package talentboost.vmware.utms.structure.project.tests.data;

import com.google.gson.annotations.SerializedName;
import talentboost.vmware.utms.structure.project.status.Status;

import java.util.Objects;

public class Data {

    protected transient String output;
    @SerializedName("output")
    protected String encodedOutput;
    protected transient String error;
    @SerializedName("error")
    protected String encodedError;
    protected Status status;
    protected String startDate;
    protected String endDate;

    public String getOutput() {
        return this.output;
    }

    public String getEncodedOutput() {
        return this.encodedOutput;
    }

    public String getError() {
        return this.error;
    }

    public String getEncodedError() {
        return this.encodedError;
    }

    public Status getStatus() {
        return this.status;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return Objects.equals(output, data.output)
                && Objects.equals(encodedOutput, data.encodedOutput)
                && Objects.equals(error, data.error)
                && Objects.equals(encodedError, data.encodedError)
                && status == data.status
                && Objects.equals(startDate, data.startDate)
                && Objects.equals(endDate, data.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(output, encodedOutput, error, encodedError, status, startDate, endDate);
    }
}
