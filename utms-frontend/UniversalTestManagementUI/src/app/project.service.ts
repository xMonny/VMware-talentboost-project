import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Project } from './project.model';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
    static baseUrl = 'http://localhost:8081/projects';

    httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };

    constructor(private http: HttpClient) { }

    listAll() : Observable<Array<Project>> {
        return this.http.get<Array<Project>>(`${ProjectService.baseUrl}`).pipe(map(data => {
            return data.map(item => {
              const project: Project = {
                id: item.id,
                name: item.name,
                description: item.description,
                runs: item.runs
              }
              return project
            })
          }));
    }

    getByName(name: string): Observable<Project> {
        const url = `${ProjectService.baseUrl}/${name}`;
        return this.http.get<Project>(url).pipe(map(item => {
              const project: Project = {
                id: item.id,
                name: item.name,
                description: item.description,
                runs: item.runs
              }
              return project
        }));
    }
}

