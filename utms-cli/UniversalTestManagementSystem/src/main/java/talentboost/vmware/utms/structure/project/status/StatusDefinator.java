package talentboost.vmware.utms.structure.project.status;

import talentboost.vmware.utms.structure.project.tests.TestCase;
import talentboost.vmware.utms.structure.project.tests.TestSuite;
import talentboost.vmware.utms.structure.project.tests.TestType;
import talentboost.vmware.utms.structure.project.tests.command.exception.InterruptedThreadException;
import talentboost.vmware.utms.structure.project.tests.command.exception.InvalidMessageTypeException;
import talentboost.vmware.utms.structure.project.tests.command.exception.MessageReaderException;
import talentboost.vmware.utms.structure.project.tests.command.exception.ProcessIOException;

import java.util.Set;
import java.util.stream.Collectors;

public class StatusDefinator {

    public static <T> Status determineStatus(Set<T> tests) throws InterruptedThreadException, InvalidMessageTypeException, ProcessIOException, MessageReaderException {
        if (tests.isEmpty()) {
            return Status.FAILED;
        }
        T t = tests.stream().findFirst().get();
        TestType testType;
        if (t instanceof TestCase) {
            testType = TestType.TEST_CASE;
        } else if (t instanceof TestSuite) {
            testType = TestType.TEST_SUITE;
        } else {
            return Status.FAILED;
        }
        run(tests, testType);
        return getStatus(tests, testType);
    }

    public static <T> Status getStatus(Set<T> tests, TestType testType) {
        Set<T> filtered;
        if (testType.equals(TestType.TEST_CASE)) {
            filtered = tests.stream()
                    .filter(tc -> ((TestCase) tc).getStatus().equals(Status.FAILED))
                    .collect(Collectors.toSet());
            if (!filtered.isEmpty()) {
                return Status.FAILED;
            } else {
                filtered = tests.stream()
                        .filter(tc -> ((TestCase) tc).getStatus().equals(Status.PASSED))
                        .collect(Collectors.toSet());
                if (filtered.isEmpty()) {
                    return Status.SKIPPED;
                } else {
                    return Status.PASSED;
                }
            }
        } else if (testType.equals(TestType.TEST_SUITE)) {
            filtered = tests.stream()
                    .filter(ts -> ((TestSuite) ts).getStatus().equals(Status.FAILED))
                    .collect(Collectors.toSet());
            if (!filtered.isEmpty()) {
                return Status.FAILED;
            } else {
                filtered = tests.stream()
                        .filter(ts -> ((TestSuite) ts).getStatus().equals(Status.PASSED))
                        .collect(Collectors.toSet());
                if (filtered.isEmpty()) {
                    return Status.SKIPPED;
                } else {
                    return Status.PASSED;
                }
            }
        }
        return Status.FAILED;
    }

    private static <T> void run(Set<T> tests, TestType testType) throws InvalidMessageTypeException, MessageReaderException, ProcessIOException, InterruptedThreadException {
        if (testType.equals(TestType.TEST_CASE)) {
            for (Object o : tests) {
                ((TestCase) o).run();
            }
        } else if (testType.equals(TestType.TEST_SUITE)) {
            for (Object o : tests) {
                ((TestSuite) o).defineStatus();
            }
        }
    }
}
