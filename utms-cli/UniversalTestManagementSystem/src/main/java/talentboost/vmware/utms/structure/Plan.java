package talentboost.vmware.utms.structure;

import talentboost.vmware.utms.structure.project.Project;
import talentboost.vmware.utms.structure.project.tests.command.exception.InterruptedThreadException;
import talentboost.vmware.utms.structure.project.tests.command.exception.InvalidMessageTypeException;
import talentboost.vmware.utms.structure.project.tests.command.exception.MessageReaderException;
import talentboost.vmware.utms.structure.project.tests.command.exception.ProcessIOException;

import java.util.Objects;

public class Plan {

    private Project project;

    public Plan() {
        this.project = new Project();
    }

    public Plan(Project project) {
        this.project = project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Project getProject() {
        return this.project;
    }

    public void defineStatus() throws InvalidMessageTypeException,
            MessageReaderException, ProcessIOException, InterruptedThreadException {
        project.defineStatus();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plan plan = (Plan) o;
        return project.equals(plan.project);
    }

    @Override
    public int hashCode() {
        return Objects.hash(project);
    }
}
