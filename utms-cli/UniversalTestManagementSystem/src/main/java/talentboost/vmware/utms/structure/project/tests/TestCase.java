package talentboost.vmware.utms.structure.project.tests;

import talentboost.vmware.utms.structure.project.tests.command.CommandData;
import talentboost.vmware.utms.structure.project.status.Status;
import talentboost.vmware.utms.structure.project.tests.command.CommandExecutor;
import talentboost.vmware.utms.structure.project.tests.command.exception.InterruptedThreadException;
import talentboost.vmware.utms.structure.project.tests.command.exception.InvalidMessageTypeException;
import talentboost.vmware.utms.structure.project.tests.command.exception.MessageReaderException;
import talentboost.vmware.utms.structure.project.tests.command.exception.ProcessIOException;
import talentboost.vmware.utms.structure.project.tests.data.Data;

import java.util.Objects;

public class TestCase extends Data {

    private static final String NULL_TEST_CASE_NAME = "Detected null name for test case";
    private static final String DEFAULT_TEST_CASE_NAME = "Default_Test_Name";

    private String name;
    private transient boolean enabled;
    private transient String command;
    private String description;

    public TestCase() {
        this.name = DEFAULT_TEST_CASE_NAME;
        this.enabled = false;
        this.command = null;
        this.description = null;
    }

    public TestCase(String name, boolean enabled, String command, String description) {
        setName(name);
        this.enabled = enabled;
        this.command = command;
        this.description = description;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException(NULL_TEST_CASE_NAME);
        }
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (!enabled) {
            this.status = Status.SKIPPED;
        }
    }

    public boolean getEnabled() {
        return this.enabled;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return this.command;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    private void setCommandData(CommandData commandData) {
        if (commandData == null) {
            this.status = Status.FAILED;
        } else {
            this.output = commandData.getOutput();
            this.encodedOutput = commandData.getEncodedOutput();
            this.error = commandData.getError();
            this.encodedError = commandData.getEncodedError();
            this.status = commandData.getStatus();
            this.startDate = commandData.getStartDate();
            this.endDate = commandData.getEndDate();
        }
    }

    public void run() throws MessageReaderException, InvalidMessageTypeException, ProcessIOException, InterruptedThreadException {
        if (enabled) {
            CommandData commandData = CommandExecutor.extractCommandDataFrom(command);
            setCommandData(commandData);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestCase testCase = (TestCase) o;
        return enabled == testCase.enabled
                && name.equals(testCase.name)
                && Objects.equals(command, testCase.command)
                && Objects.equals(description, testCase.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, enabled, command, description);
    }
}
