package talentboost.vmware.utms.structure.project.tests.command.formatter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

    private static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'";
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat(DATE_FORMAT_PATTERN);

    private final Date date;

    public DateFormatter(long millis) {
        this.date = new Date(millis);
    }

    @Override
    public String toString() {
        return FORMATTER.format(date);
    }
}
