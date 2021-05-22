package talentboost.vmware.utms.structure.project.tests.command;

import talentboost.vmware.utms.structure.project.status.Status;
import talentboost.vmware.utms.structure.project.tests.data.Data;

import java.util.Objects;

public class CommandData extends Data {

    public static class CommandDataBuilder {
        private String output;
        private String encodedOutput;
        private String error;
        private String encodedError;

        private Status status;
        private String startDate;
        private String endDate;

        public CommandDataBuilder setOutput(String output) {
            this.output = output;
            return this;
        }

        public CommandDataBuilder setEncodedOutput(String encodedOutput) {
            this.encodedOutput = encodedOutput;
            return this;
        }

        public CommandDataBuilder setError(String error) {
            this.error = error;
            return this;
        }

        public CommandDataBuilder setEncodedError(String encodedError) {
            this.encodedError = encodedError;
            return this;
        }

        public CommandDataBuilder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public CommandDataBuilder setStartDate(String startDate) {
            this.startDate = startDate;
            return this;
        }

        public CommandDataBuilder setEndDate(String endDate) {
            this.endDate = endDate;
            return this;
        }

        public CommandData build() {
            CommandData commandData = new CommandData();
            commandData.output = this.output;
            commandData.encodedOutput = this.encodedOutput;
            commandData.error = this.error;
            commandData.encodedError = this.encodedError;
            commandData.status = this.status;
            commandData.startDate = this.startDate;
            commandData.endDate = this.endDate;

            return commandData;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CommandDataBuilder that = (CommandDataBuilder) o;
            return Objects.equals(output, that.output)
                    && Objects.equals(encodedOutput, that.encodedOutput)
                    && Objects.equals(error, that.error)
                    && Objects.equals(encodedError, that.encodedError)
                    && status == that.status
                    && Objects.equals(startDate, that.startDate)
                    && Objects.equals(endDate, that.endDate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(output, encodedOutput, error, encodedError, status, startDate, endDate);
        }
    }
}
