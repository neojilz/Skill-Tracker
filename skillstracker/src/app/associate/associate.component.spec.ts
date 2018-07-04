import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import {FormsModule,ReactiveFormsModule} from '@angular/forms';
import {AppModule} from '../app.module';
import {EmployeeService} from '../services/employee.service' ;

import { AssociateComponent } from './associate.component';
import { APP_BASE_HREF } from '@angular/common';

describe('AssociateComponent', () => {
  let component: AssociateComponent;
  let fixture: ComponentFixture<AssociateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports:[AppModule,FormsModule,ReactiveFormsModule],
      providers:[EmployeeService,{provide: APP_BASE_HREF, useValue:'/'}]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AssociateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
