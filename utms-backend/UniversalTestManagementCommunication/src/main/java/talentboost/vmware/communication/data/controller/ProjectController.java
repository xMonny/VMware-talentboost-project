package talentboost.vmware.communication.data.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import talentboost.vmware.communication.data.extraction.Extractor;
import talentboost.vmware.communication.data.model.Project;
import talentboost.vmware.communication.database.storage.ProjectStorage;
import talentboost.vmware.communication.database.storage.Storage;
import talentboost.vmware.communication.database.storage.exception.IdNullException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/projects", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProjectController {

    private static final String NOT_FOUND_PROJECT_MESSAGE = "Not found project";

    private final ProjectStorage projectStorage;

    public ProjectController() {
        this.projectStorage = Storage.getProjectStorage();
    }

    @PostMapping
    public void postProject(@RequestBody String planJsonString) {
        Project project = Extractor.extractProject(planJsonString);
        try {
            projectStorage.add(project);
        } catch (IdNullException e) {
            System.out.println(e.getMessage());
        }
    }

    @GetMapping
    public List<Project> getAllProjects() {
        return projectStorage.getAll();
    }

    @GetMapping(value = "/{projectName}")
    public Project getProject(@PathVariable String projectName) {
        return projectStorage.getProject(projectName);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String projectNotFoundExceptionHandler() {
        return NOT_FOUND_PROJECT_MESSAGE;
    }
}
