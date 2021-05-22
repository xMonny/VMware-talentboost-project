import { Component, OnInit } from '@angular/core';
import { ProjectDetailsComponent } from '../project-details/project-details.component';
import { Project } from '../project.model';

@Component({
  selector: 'app-suite-details',
  templateUrl: './suite-details.component.html',
  styleUrls: ['./suite-details.component.css']
})
export class SuiteDetailsComponent implements OnInit {

  project: Project;

  constructor() { }

  ngOnInit(): void {
    
  }

}
