package talentboost.vmware.communication.data.map;

import org.springframework.jdbc.core.RowMapper;
import talentboost.vmware.communication.data.model.TestRun;
import talentboost.vmware.communication.data.model.TestSuite;
import talentboost.vmware.communication.database.storage.Storage;
import talentboost.vmware.communication.database.storage.TestSuiteStorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.List;

import static talentboost.vmware.communication.database.storage.column.ColumnName.ID_COLUMN;
import static talentboost.vmware.communication.database.storage.column.ColumnName.PROJECT_ID_COLUMN;
import static talentboost.vmware.communication.database.storage.column.ColumnName.STATUS_COLUMN;

public class TestRunRowMapper implements RowMapper<TestRun> {

    //TODO: instead of set we can use constructors
    @Override
    public TestRun mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt(ID_COLUMN);
        int projectId = rs.getInt(PROJECT_ID_COLUMN);
        String status = rs.getString(STATUS_COLUMN);
        TestSuiteStorage testSuiteStorage = Storage.getTestSuiteStorage();
        List<TestSuite> testSuites = testSuiteStorage.getAll(id, projectId);
        return new TestRun(id, status, new LinkedHashSet<>(testSuites));
    }
}
