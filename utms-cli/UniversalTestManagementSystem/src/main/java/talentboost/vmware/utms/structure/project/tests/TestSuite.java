package talentboost.vmware.utms.structure.project.tests;

import com.google.gson.annotations.SerializedName;
import talentboost.vmware.utms.structure.project.status.StatusDefinator;
import talentboost.vmware.utms.structure.project.status.Status;
import talentboost.vmware.utms.structure.project.tests.command.exception.InterruptedThreadException;
import talentboost.vmware.utms.structure.project.tests.command.exception.InvalidMessageTypeException;
import talentboost.vmware.utms.structure.project.tests.command.exception.MessageReaderException;
import talentboost.vmware.utms.structure.project.tests.command.exception.ProcessIOException;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class TestSuite {

    private static final String NULL_TEST_SUITE_NAME = "Detected null name for test suite";
    private static final String DEFAULT_TEST_SUITE_NAME = "Default_suite_Name";

    private String name;
    private Status status;

    @SerializedName("tests")
    private Set<TestCase> testCases;

    public TestSuite() {
        this.name = DEFAULT_TEST_SUITE_NAME;
        this.testCases = new LinkedHashSet<>();
    }

    public TestSuite(Set<TestCase> testCases) {
        this.name = DEFAULT_TEST_SUITE_NAME;
        this.testCases = testCases;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException(NULL_TEST_SUITE_NAME);
        }
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setTestCases(Set<TestCase> testCases) {
        this.testCases = testCases;
    }

    public Set<TestCase> getTestCases() {
        return this.testCases;
    }

    public void defineStatus() throws MessageReaderException, InvalidMessageTypeException, ProcessIOException, InterruptedThreadException {
        this.status = StatusDefinator.determineStatus(testCases);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestSuite testSuite = (TestSuite) o;
        return name.equals(testSuite.name)
                && status == testSuite.status
                && testCases.equals(testSuite.testCases);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, status, testCases);
    }
}
