import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { TestSuite } from './test-suite.model';

@Injectable({
  providedIn: 'root'
})
export class TestSuiteService {
    static baseUrl = 'http://localhost:8081/projects';

    constructor(private http: HttpClient) { }

    listAll(projectId: number, runId: number) : Observable<Array<TestSuite>> {
        return this.http.get<Array<TestSuite>>(`${TestSuiteService.baseUrl}/${projectId}/runs/${runId}`).pipe(map(data => {
            return data.map(item => {
              const testSuite: TestSuite = {
                name: item.name,
                status: item.status,
                tests: item.tests
              }
              return testSuite
            })
        }));
    }
}

