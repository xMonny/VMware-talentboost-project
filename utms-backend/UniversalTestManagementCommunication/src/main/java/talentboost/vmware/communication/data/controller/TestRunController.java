package talentboost.vmware.communication.data.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import talentboost.vmware.communication.data.extraction.Extractor;
import talentboost.vmware.communication.data.model.Project;
import talentboost.vmware.communication.data.model.TestRun;
import talentboost.vmware.communication.database.storage.Storage;
import talentboost.vmware.communication.database.storage.TestRunStorage;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/projects", produces = MediaType.APPLICATION_JSON_VALUE)
public class TestRunController {

    private static final String NOT_FOUND_TEST_RUN_MESSAGE = "Not found test run";

    private final TestRunStorage testRunStorage;

    public TestRunController() {
        this.testRunStorage = Storage.getTestRunStorage();
    }

    @PostMapping(value = "/{projectId}/runs")
    public void postTestRun(@PathVariable int projectId, @RequestBody String planJsonString) {
        Project project = Extractor.extractProject(planJsonString);
        TestRun testRun = new TestRun(project.getStatus(), project.getTestSuites());
        testRunStorage.add(testRun, projectId);
    }

    @GetMapping(value = "/{projectId}/runs")
    public List<TestRun> getTestRun(@PathVariable int projectId) {
        return testRunStorage.getAll(projectId);
    }

    @GetMapping(value = "/{projectId}/runs/{id}")
    public TestRun getTestRun(@PathVariable int projectId, @PathVariable int id) {
        return testRunStorage.get(projectId, id);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String runTestNotFoundExceptionHandler() {
        return NOT_FOUND_TEST_RUN_MESSAGE;
    }
}
