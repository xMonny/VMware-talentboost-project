package talentboost.vmware.communication.database.storage;

import org.springframework.jdbc.core.JdbcTemplate;
import talentboost.vmware.communication.data.map.TestSuiteRowMapper;
import talentboost.vmware.communication.data.model.TestCase;
import talentboost.vmware.communication.data.model.TestSuite;

import java.util.List;
import java.util.Set;

public class TestSuiteStorage {

    private final JdbcTemplate jdbcTemplate;

    public TestSuiteStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TestSuite> getAll(int runId, int projectId) {
        String sql = "SELECT * FROM test_suite WHERE run_id = ? AND project_id = ?";

        return jdbcTemplate.query(
                sql,
                new Object[]{runId, projectId},
                new TestSuiteRowMapper());
    }

    public void add(TestSuite testSuite, int runId, int projectId) {
        String suiteName = testSuite.getName();
        String status = testSuite.getStatus();
        Set<TestCase> testCases = testSuite.getTests();
        String sql = "INSERT INTO test_suite(name, run_id, project_id, status) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, suiteName, runId, projectId, status);
        addTestCases(testCases, suiteName, runId, projectId);
    }

    private void addTestCases(Set<TestCase> testCases, String suiteName, int runId, int projectId) {
        TestCaseStorage testCaseStorage = Storage.getTestCaseStorage();
        for (TestCase current : testCases) {
            testCaseStorage.add(current, suiteName, runId, projectId);
        }
    }

}
