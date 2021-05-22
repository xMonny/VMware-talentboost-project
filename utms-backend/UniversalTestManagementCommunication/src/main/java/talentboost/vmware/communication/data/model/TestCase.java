package talentboost.vmware.communication.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import static talentboost.vmware.communication.data.model.status.Status.SKIPPED;

public class TestCase {

    private String name;
    private String description;
    private String status;
    private String output;
    @SerializedName("startDate")
    private String startTime;
    @SerializedName("endDate")
    private String endTime;
    private String error;

    private TestCase() {}

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isEnabled() {
        return !status.equals(SKIPPED.name());
    }

    public String getStatus() {
        return status;
    }

    public String getOutput() {
        return output;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getError() {
        return error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestCase testCase = (TestCase) o;
        return name.equals(testCase.name)
                && Objects.equals(description, testCase.description)
                && status.equals(testCase.status)
                && Objects.equals(output, testCase.output)
                && Objects.equals(startTime, testCase.startTime)
                && Objects.equals(endTime, testCase.endTime)
                && Objects.equals(error, testCase.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, status, output, startTime, endTime, error);
    }

    public static class TestCaseBuilder {
        private String name;
        private String description;
        private String status;
        private String output;
        @SerializedName("startDate")
        private String startTime;
        @SerializedName("endDate")
        private String endTime;
        private String error;

        public TestCaseBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public TestCaseBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public TestCaseBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        public TestCaseBuilder setOutput(String output) {
            this.output = output;
            return this;
        }

        public TestCaseBuilder setStartTime(String startTime) {
            this.startTime = startTime;
            return this;
        }

        public TestCaseBuilder setEndTime(String endTime) {
            this.endTime = endTime;
            return this;
        }

        public TestCaseBuilder setError(String error) {
            this.error = error;
            return this;
        }

        public TestCase build() {
            TestCase testCase = new TestCase();
            testCase.name = this.name;
            testCase.description = this.description;
            testCase.status = this.status;
            testCase.output = this.output;
            testCase.startTime = this.startTime;
            testCase.endTime = this.endTime;
            testCase.error = this.error;
            return testCase;
        }
    }
}
