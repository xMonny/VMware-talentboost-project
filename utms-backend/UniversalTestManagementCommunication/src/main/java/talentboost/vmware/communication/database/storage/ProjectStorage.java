package talentboost.vmware.communication.database.storage;

import org.springframework.jdbc.core.JdbcTemplate;
import talentboost.vmware.communication.data.map.ProjectRowMapper;
import talentboost.vmware.communication.data.model.Project;
import talentboost.vmware.communication.data.model.TestRun;
import talentboost.vmware.communication.data.model.TestSuite;
import talentboost.vmware.communication.database.storage.exception.IdNullException;

import java.util.List;
import java.util.Set;

public class ProjectStorage {

    private final JdbcTemplate jdbcTemplate;

    public ProjectStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getNumber() {
        String sql = "SELECT COUNT(*) FROM project";
        String result = this.jdbcTemplate.queryForObject(sql, String.class);
        if (result == null) {
            return 0;
        }
        return Integer.parseInt(result);
    }

    public List<Project> getAll() {
        String sql = "SELECT * FROM project";

        return jdbcTemplate.query(
                sql,
                new ProjectRowMapper());
    }

    public int getId(String name) throws IdNullException {
        String sql = "SELECT id FROM project WHERE name like ?";
        String idString = this.jdbcTemplate.queryForObject(sql, new Object[]{name}, String.class);
        if (idString == null) {
            throw new IdNullException("Detected null id for project with name " + name);
        }
        return Integer.parseInt(idString);
    }

    public Project getProject(String name) {
        String sql = "SELECT * FROM project WHERE name = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{name}, new ProjectRowMapper());
    }

    public Project getProject(int id) {
        String sql = "SELECT * FROM project WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ProjectRowMapper());
    }

    public void add(Project project) throws IdNullException {
        String name = project.getName();
        if (contains(name)) {
            int projectId = getId(name);
            createTestRunFor(project, projectId);
        } else {
            insertProject(project);
        }
    }

    private boolean contains(String name) {
        String sql = "SELECT count(*) FROM project WHERE name = ?";

        String countString = jdbcTemplate.queryForObject(sql, new Object[]{name}, String.class);
        if (countString == null) {
            return false;
        }
        int count = Integer.parseInt(countString);
        return count > 0;
    }

    private int generateId() {
        if (getNumber() == 0) {
            return 1;
        } else {
            return getNumber() + 1;
        }
    }

    private void insertProject(Project project) {
        int projectId = generateId();
        String name = project.getName();
        String description = project.getDescription();
        String sql = "INSERT INTO project(id, name, description) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, projectId, name, description);
        createTestRunFor(project, projectId);
    }

    private void createTestRunFor(Project project, int projectId) {
        String status = project.getStatus();
        Set<TestSuite> testSuites = project.getTestSuites();
        TestRun testRun = new TestRun(status, testSuites);
        TestRunStorage testRunStorage = Storage.getTestRunStorage();
        testRunStorage.add(testRun, projectId);
    }
}
