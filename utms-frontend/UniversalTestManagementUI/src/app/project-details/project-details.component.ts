import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Project } from '../project.model';
import { TestRun } from '../test-run.model';
import { TestRunService } from '../test-run.service';

@Component({
  selector: 'app-project-details',
  templateUrl: './project-details.component.html',
  styleUrls: ['./project-details.component.css']
})
export class ProjectDetailsComponent implements OnInit {
  project: Project;
  runs: Array<TestRun>

  constructor(private route: ActivatedRoute, private testRunService: TestRunService) { }

  ngOnInit(): void {
    this.route.data.subscribe((data: { project: Project }) => {
      this.project = data.project;
    });
    this.testRunService.listAll(this.project.id).subscribe((runs: TestRun[]) => {
      this.runs = runs;
    });
  }

  filterEnabledTestCases(testRun : TestRun) {
    return testRun.suites.filter(s => s.tests.filter(tc => tc.status !== 'skipped').length);
  }

  filterPassedTestCases(testRun : TestRun) {
    return testRun.suites.filter(s => s.tests.filter(tc => tc.status === 'passed').length);
  }

  getProject() {
    return this.project;
  }
}
