package talentboost.vmware.communication.data.model;

import java.util.Objects;
import java.util.Set;

public class TestRun {

    private final int id;
    private final String status;
    private final Set<TestSuite> suites;

    public TestRun(int id, String status, Set<TestSuite> suites) {
        this.id = id;
        this.status = status;
        this.suites = suites;
    }

    public TestRun(String status, Set<TestSuite> suites) {
        this.id = 0;
        this.status = status;
        this.suites = suites;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public Set<TestSuite> getSuites() {
        return suites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestRun testRun = (TestRun) o;
        return id == testRun.id && status.equals(testRun.status) && suites.equals(testRun.suites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, suites);
    }
}
