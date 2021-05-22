package talentboost.vmware.utms.structure.project;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.annotations.SerializedName;
import org.json.JSONException;
import org.json.JSONObject;
import talentboost.vmware.utms.exception.CastToJSONObjectException;
import talentboost.vmware.utms.exception.ReadJSONObjectException;
import talentboost.vmware.utms.structure.project.status.Status;
import talentboost.vmware.utms.structure.project.status.StatusDefinator;
import talentboost.vmware.utms.structure.project.tests.TestCase;
import talentboost.vmware.utms.structure.project.tests.TestSuite;
import talentboost.vmware.utms.structure.project.tests.command.exception.InterruptedThreadException;
import talentboost.vmware.utms.structure.project.tests.command.exception.InvalidMessageTypeException;
import talentboost.vmware.utms.structure.project.tests.command.exception.MessageReaderException;
import talentboost.vmware.utms.structure.project.tests.command.exception.ProcessIOException;

import java.io.IOException;
import java.util.*;

public class Project {

    private static final String JSON_CAST_ERROR_MESSAGE = "Error occurred in casting to JSON Object.";
    private static final String JSON_READ_ERROR_MESSAGE = "Error occurred while reading JSON Object";
    private static final String DEFAULT_PROJECT_NAME = "Default_Name";
    private static final String DEFAULT_PROJECT_DESCRIPTION = "Default_Description";
    private static final String NULL_PROJECT_NAME = "Project name cannot be null";
    private static final String NULL_PROJECT_DESCRIPTION = "Project description cannot be null";
    private static final String NULL_PROJECT_SUITES = "Project suites cannot be null";

    private String name;
    private String description;
    private Status status;

    private transient Map<String, Map<String, TestCase>> suites;

    @SerializedName("suites")
    private Set<TestSuite> testSuites;

    public Project() {
        this.name = DEFAULT_PROJECT_NAME;
        this.description = DEFAULT_PROJECT_DESCRIPTION;
        this.suites = new LinkedHashMap<>();
    }

    public Project(String name, String description, Map<String, Map<String, TestCase>> suites) {
        setName(name);
        setDescription(description);
        setSuites(suites);
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException(NULL_PROJECT_NAME);
        }
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setDescription(String description) {
        if (description == null) {
            throw new IllegalArgumentException(NULL_PROJECT_DESCRIPTION);
        }
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setSuites(Map<String, Map<String, TestCase>> suites) {
        if (suites == null) {
            throw new IllegalArgumentException(NULL_PROJECT_SUITES);
        }
        this.suites = suites;
        this.testSuites = getTestSuitesFrom(suites);
    }

    private Set<TestSuite> getTestSuitesFrom(Map<String, Map<String, TestCase>> suites) {
        Set<TestSuite> testSuites = new LinkedHashSet<>();
        for (Map.Entry<String, Map<String, TestCase>> entrySuites : suites.entrySet()) {
            String currentSuiteName = entrySuites.getKey();
            Map<String, TestCase> suiteTests = entrySuites.getValue();
            Set<TestCase> testCases = getTestCasesFrom(suiteTests);

            TestSuite currentTestSuite = new TestSuite(testCases);
            currentTestSuite.setName(currentSuiteName);
            testSuites.add(currentTestSuite);
        }
        return testSuites;
    }

    private Set<TestCase> getTestCasesFrom(Map<String, TestCase> testSuites) {
        Set<TestCase> testCases = new LinkedHashSet<>();
        for (Map.Entry<String, TestCase> entryTestCase : testSuites.entrySet()) {
            String currentTestCaseName = entryTestCase.getKey();
            try {
                JSONObject mapJo = new JSONObject(String.valueOf(entryTestCase.getValue()));
                ObjectMapper objectMapper = new ObjectMapper();
                TestCase testCase = objectMapper.readValue(mapJo.toString(), new TypeReference<TestCase>() {
                });
                testCase.setName(currentTestCaseName);
                testCases.add(testCase);
            } catch (JSONException e) {
                throw new CastToJSONObjectException(JSON_CAST_ERROR_MESSAGE, e);
            } catch (IOException e) {
                throw new ReadJSONObjectException(JSON_READ_ERROR_MESSAGE, e);
            }
        }
        return testCases;
    }

    public Map<String, Map<String, TestCase>> getSuites() {
        return this.suites;
    }

    public Set<TestSuite> getTestSuites() {
        return this.testSuites;
    }

    public void defineStatus() throws MessageReaderException, InvalidMessageTypeException, ProcessIOException, InterruptedThreadException {
        this.status = StatusDefinator.determineStatus(testSuites);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(name, project.name)
                && Objects.equals(description, project.description)
                && status == project.status
                && Objects.equals(testSuites, project.testSuites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, status, testSuites);
    }
}
