import { Injectable } from '@angular/core';
import {
  Resolve,
  ActivatedRouteSnapshot
} from '@angular/router';
import { Observable } from 'rxjs';
import { TestRun } from './test-run.model';
import { TestRunService } from './test-run.service';

@Injectable({
  providedIn: 'root'
})
export class TestRunDetailsResolver implements Resolve<Observable<Array<TestRun>>> {
  constructor(private testRunService: TestRunService) {
  }

  resolve(route: ActivatedRouteSnapshot): Observable<Array<TestRun>> {
    const projectId = Number(route.paramMap.get('name'));

    return this.testRunService.listAll(projectId);
  }
}
