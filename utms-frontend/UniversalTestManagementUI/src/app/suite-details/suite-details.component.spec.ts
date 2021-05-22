import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuiteDetailsComponent } from './suite-details.component';

describe('SuiteDetailsComponent', () => {
  let component: SuiteDetailsComponent;
  let fixture: ComponentFixture<SuiteDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SuiteDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SuiteDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
