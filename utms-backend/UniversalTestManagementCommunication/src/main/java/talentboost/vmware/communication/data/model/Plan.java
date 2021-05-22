package talentboost.vmware.communication.data.model;

import java.util.Objects;

public class Plan {

    private final Project project;

    public Plan() {
        this.project = new Project();
    }

    public Plan(Project project) {
        this.project = project;
    }

    public Project getProject() {
        return this.project;
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
