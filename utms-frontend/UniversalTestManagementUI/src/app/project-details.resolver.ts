import { Injectable } from '@angular/core';
import {
  Resolve,
  ActivatedRouteSnapshot
} from '@angular/router';
import { Observable } from 'rxjs';
import { Project } from './project.model';
import { ProjectService } from './project.service';

@Injectable({
  providedIn: 'root'
})
export class ProjectDetailsResolver implements Resolve<Observable<Project>> {
  constructor(private projectService: ProjectService) {
  }

  resolve(route: ActivatedRouteSnapshot): Observable<Project> {
    const name = route.paramMap.get('name');

    return this.projectService.getByName(name);
  }
}
