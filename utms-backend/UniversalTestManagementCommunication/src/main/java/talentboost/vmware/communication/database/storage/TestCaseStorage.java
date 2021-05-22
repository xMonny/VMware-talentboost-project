package talentboost.vmware.communication.database.storage;

import org.springframework.jdbc.core.JdbcTemplate;
import talentboost.vmware.communication.data.map.TestCaseRowMapper;
import talentboost.vmware.communication.data.model.TestCase;

import java.util.List;

public class TestCaseStorage {

    private final JdbcTemplate jdbcTemplate;

    public TestCaseStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TestCase> getAll() {
        String sql = "SELECT * FROM test_case";

        return jdbcTemplate.query(
                sql,
                new TestCaseRowMapper());
    }

    public List<TestCase> getAll(String suiteName, int runId, int projectId) {
        String sql = "SELECT * FROM test_case WHERE suite_name like ? AND run_id = ? AND project_id = ?";

        return jdbcTemplate.query(
                sql,
                new Object[]{suiteName, runId, projectId},
                new TestCaseRowMapper());
    }

    public void add(TestCase testCase, String suiteName, int runId, int projectId) {
        String testName = testCase.getName();
        String description = testCase.getDescription();
        String status = testCase.getStatus();
        String output = testCase.getOutput();
        String startTime = testCase.getStartTime();
        String endTime = testCase.getEndTime();
        String error = testCase.getError();
        boolean enabled = testCase.isEnabled();
        String sql = "INSERT INTO test_case(name, suite_name, run_id, project_id, description, enabled, status" +
                ", output, start_time, end_time, error) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, testName, suiteName, runId, projectId, description, enabled, status, output,
                startTime, endTime, error);
    }
}
