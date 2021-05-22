package talentboost.vmware.communication.data.map;

import org.springframework.jdbc.core.RowMapper;
import talentboost.vmware.communication.data.model.Project;
import talentboost.vmware.communication.data.model.TestRun;
import talentboost.vmware.communication.database.storage.Storage;
import talentboost.vmware.communication.database.storage.TestRunStorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.List;

import static talentboost.vmware.communication.database.storage.column.ColumnName.ID_COLUMN;
import static talentboost.vmware.communication.database.storage.column.ColumnName.NAME_COLUMN;
import static talentboost.vmware.communication.database.storage.column.ColumnName.DESCRIPTION_COLUMN;

public class ProjectRowMapper implements RowMapper<Project> {

    @Override
    public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt(ID_COLUMN);
        String name = rs.getString(NAME_COLUMN);
        String description = rs.getString(DESCRIPTION_COLUMN);
        TestRunStorage testRunStorage = Storage.getTestRunStorage();
        List<TestRun> testRuns = testRunStorage.getAll(id);
        return new Project(id, name, description, new LinkedHashSet<>(testRuns));
    }
}
