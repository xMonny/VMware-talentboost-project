import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Project } from '../project.model';
import { ProjectService } from '../project.service';

@Component({
  selector: 'app-projects-list',
  templateUrl: './projects-list.component.html',
  styleUrls: ['./projects-list.component.css']
})
export class ProjectsListComponent implements OnInit {
  projects: Array<Project>

  constructor(private projectService: ProjectService) { }

  ngOnInit(): void {
    this.projectService.listAll().subscribe(data => this.projects=data);
  }
}
