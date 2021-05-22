package talentboost.vmware.communication.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Project {

    private int id;
    private String name;
    private String description;
    private String status;
    @SerializedName("suites")
    private Set<TestSuite> testSuites;
    @SerializedName("runs")
    private Set<TestRun> testRuns;

    public Project() {
        this.testSuites = new LinkedHashSet<>();
    }

    public Project(int id, String name, String description, Set<TestRun> testRuns) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.testRuns = testRuns;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public Set<TestSuite> getTestSuites() {
        return testSuites;
    }

    public Set<TestRun> getTestRuns() {
        return testRuns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id == project.id
                && name.equals(project.name)
                && Objects.equals(description, project.description)
                && Objects.equals(status, project.status)
                && testSuites.equals(project.testSuites)
                && Objects.equals(testRuns, project.testRuns);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, status, testSuites, testRuns);
    }
}
