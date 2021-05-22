package talentboost.vmware.communication.data.model;

import java.util.Objects;
import java.util.Set;

public class TestSuite {

    private final String name;
    private final String status;
    private final Set<TestCase> tests;

    public TestSuite(String name, String status, Set<TestCase> tests) {
        this.name = name;
        this.status = status;
        this.tests = tests;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public Set<TestCase> getTests() {
        return tests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestSuite testSuite = (TestSuite) o;
        return name.equals(testSuite.name)
                && status.equals(testSuite.status)
                && tests.equals(testSuite.tests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, status, tests);
    }
}
