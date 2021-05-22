package talentboost.vmware.communication.database.storage;

import org.springframework.jdbc.core.JdbcTemplate;
import talentboost.vmware.communication.database.config.DBConfiguration;

import javax.sql.DataSource;

public class Storage {

    private static final DBConfiguration configuration = new DBConfiguration();
    private static final DataSource dataSource = configuration.getDataSource();
    private static final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    private static final ProjectStorage projectStorage = new ProjectStorage(jdbcTemplate);
    private static final TestRunStorage testRunStorage = new TestRunStorage(jdbcTemplate);
    private static final TestCaseStorage testCaseStorage = new TestCaseStorage(jdbcTemplate);
    private static final TestSuiteStorage testSuiteStorage = new TestSuiteStorage(jdbcTemplate);

    private Storage() { }

    public static ProjectStorage getProjectStorage() {
        return projectStorage;
    }

    public static TestRunStorage getTestRunStorage () {
        return testRunStorage;
    }

    public static TestCaseStorage getTestCaseStorage() {
        return testCaseStorage;
    }

    public static TestSuiteStorage getTestSuiteStorage() {
        return testSuiteStorage;
    }
}
