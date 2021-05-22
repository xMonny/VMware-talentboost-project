package talentboost.vmware.communication.database.storage;

import org.springframework.jdbc.core.JdbcTemplate;
import talentboost.vmware.communication.data.map.TestRunRowMapper;
import talentboost.vmware.communication.data.model.TestRun;
import talentboost.vmware.communication.data.model.TestSuite;

import java.util.List;
import java.util.Set;

public class TestRunStorage {

    private final JdbcTemplate jdbcTemplate;

    public TestRunStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getNumber(int projectId) {
        String sql = "SELECT COUNT(*) FROM test_run WHERE project_id = ?";
        String result = this.jdbcTemplate.queryForObject(sql, new Object[]{projectId}, String.class);
        if (result == null) {
            return 0;
        }
        return Integer.parseInt(result);
    }

    public List<TestRun> getAll(int projectId) {
        String sql = "SELECT * FROM test_run WHERE project_id = ?";

        return jdbcTemplate.query(
                sql,
                new Object[]{projectId},
                new TestRunRowMapper());
    }

    public TestRun get(int projectId, int id) {
        String sql = "SELECT * FROM test_run WHERE project_id = ? AND id = ?";

        return jdbcTemplate.queryForObject(
                sql, new Object[]{projectId, id}, new TestRunRowMapper());
    }

    public int defineId(int projectId) {
        int number = getNumber(projectId);
        if (number == 0) {
            return 1;
        } else {
            return number + 1;
        }
    }

    public void add(TestRun testRun, int projectId) {
        int id = defineId(projectId);
        String status = testRun.getStatus();
        Set<TestSuite> testSuites = testRun.getSuites();
        String sql = "INSERT INTO test_run(id, project_id, status) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, id, projectId, status);
        addTestSuites(testSuites, id, projectId);
    }

    private void addTestSuites(Set<TestSuite> testSuites, int runId, int projectId) {
        TestSuiteStorage testSuiteStorage = Storage.getTestSuiteStorage();
        for (TestSuite current : testSuites) {
            testSuiteStorage.add(current, runId, projectId);
        }
    }
}
