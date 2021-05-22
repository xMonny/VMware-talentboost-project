package talentboost.vmware.communication.data.map;

import org.springframework.jdbc.core.RowMapper;
import talentboost.vmware.communication.data.model.TestCase;

import java.sql.ResultSet;
import java.sql.SQLException;

import static talentboost.vmware.communication.database.storage.column.ColumnName.NAME_COLUMN;
import static talentboost.vmware.communication.database.storage.column.ColumnName.DESCRIPTION_COLUMN;
import static talentboost.vmware.communication.database.storage.column.ColumnName.STATUS_COLUMN;
import static talentboost.vmware.communication.database.storage.column.ColumnName.OUTPUT_COLUMN;
import static talentboost.vmware.communication.database.storage.column.ColumnName.START_TIME_COLUMN;
import static talentboost.vmware.communication.database.storage.column.ColumnName.END_TIME_COLUMN;
import static talentboost.vmware.communication.database.storage.column.ColumnName.ERROR_COLUMN;

public class TestCaseRowMapper implements RowMapper<TestCase> {

    @Override
    public TestCase mapRow(ResultSet rs, int rowNum) throws SQLException {
        String name = rs.getString(NAME_COLUMN);
        String description = rs.getString(DESCRIPTION_COLUMN);
        String status = rs.getString(STATUS_COLUMN);
        String output = rs.getString(OUTPUT_COLUMN);
        String startTime = rs.getString(START_TIME_COLUMN);
        String endTime = rs.getString(END_TIME_COLUMN);
        String error = rs.getString(ERROR_COLUMN);

        TestCase.TestCaseBuilder testCaseBuilder = new TestCase.TestCaseBuilder();
        return testCaseBuilder.setName(name)
                .setDescription(description)
                .setStatus(status)
                .setOutput(output)
                .setStartTime(startTime)
                .setEndTime(endTime)
                .setError(error)
                .build();
    }
}
