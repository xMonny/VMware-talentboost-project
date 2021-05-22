import { BrowserModule } from '@angular/platform-browser';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ProjectsListComponent } from './projects-list/projects-list.component';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ProjectDetailsComponent } from './project-details/project-details.component';
import { ProjectDetailsResolver } from './project-details.resolver';
import { TestRunDetailsResolver } from './test-run-details.resolver';
import { SuiteDetailsComponent } from './suite-details/suite-details.component';

const routes: Routes = [
  {
    path: '',
    component: ProjectsListComponent
  },
  {
    path: 'projects/:name',
    component: ProjectDetailsComponent,
    resolve: {
      project: ProjectDetailsResolver
    }
  },
  {
    path:'projects/:name/runs/:id',
    component: SuiteDetailsComponent
  }
];

@NgModule({
  declarations: [
    AppComponent,
    ProjectsListComponent,
    ProjectDetailsComponent,
    SuiteDetailsComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
  ]
})
export class AppModule { }
