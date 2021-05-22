import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TestRun } from './test-run.model';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TestRunService {
    static baseUrl = 'http://localhost:8081/projects';

    constructor(private http: HttpClient) { }

    listAll(projectId: number) : Observable<Array<TestRun>> {
        return this.http.get<Array<TestRun>>(`${TestRunService.baseUrl}/${projectId}/runs`).pipe(map(data => {
            return data.map(item => {
              const testRun: TestRun = {
                id: item.id,
                status: item.status,
                suites: item.suites
              }
              return testRun
            })
          }));
    }
}

