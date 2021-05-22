package talentboost.vmware.communication.data.map;

import org.springframework.jdbc.core.RowMapper;
import talentboost.vmware.communication.data.model.TestCase;
import talentboost.vmware.communication.data.model.TestSuite;
import talentboost.vmware.communication.database.storage.Storage;
import talentboost.vmware.communication.database.storage.TestCaseStorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.List;

import static talentboost.vmware.communication.database.storage.column.ColumnName.NAME_COLUMN;
import static talentboost.vmware.communication.database.storage.column.ColumnName.RUN_ID_COLUMN;
import static talentboost.vmware.communication.database.storage.column.ColumnName.PROJECT_ID_COLUMN;
import static talentboost.vmware.communication.database.storage.column.ColumnName.STATUS_COLUMN;

public class TestSuiteRowMapper implements RowMapper<TestSuite> {

    //TODO: throws SQLException
    @Override
    public TestSuite mapRow(ResultSet rs, int rowNum) throws SQLException {
        String name = rs.getString(NAME_COLUMN);
        int runId = rs.getInt(RUN_ID_COLUMN);
        int projectId = rs.getInt(PROJECT_ID_COLUMN);
        String status = rs.getString(STATUS_COLUMN);
        TestCaseStorage testCaseStorage = Storage.getTestCaseStorage();
        List<TestCase> testCases = testCaseStorage.getAll(name, runId, projectId);
        return new TestSuite(name, status, new LinkedHashSet<>(testCases));
    }
}
