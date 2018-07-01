import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { AppModule } from '../app.module';
import { RouterTestingModule } from '@angular/router/testing';

import { AssociateSearchComponent } from './associate-search.component';
import {  SkillsService} from '../services/skills.service';
import { MockSkillService } from '../mock/mock-skill-service';
import { Skill } from '../models/skill';
import { EmployeeService } from '../services/employee.service';
import { Associate } from '../models/associate';
import { MockEmployeeService } from '../mock/mock-employee-service';

describe('DashboardComponent', () => {
  let component: AssociateSearchComponent;
  let fixture: ComponentFixture<AssociateSearchComponent>;
  pic1:Blob;
  
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [AppModule, RouterTestingModule],
      providers: [{ provide: SkillsService, useClass: MockSkillService },
      { provide: EmployeeService, useClass: MockEmployeeService }
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AssociateSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create dashboard', () => {
    expect(component).toBeTruthy();
  });
  it('should delete employee', () => {
   
  pic1:Blob;
    let associate: Associate = {"associateId":16516,"name":"sdas","email":"jijokrishnan33@gmail.com","mobile":"4564645645","gender":"Male","pic":this.pic1,"statusGreen":false,"statusBlue":true,"statusRed":false,"level1":false,"level2":true,"level3":false,"remark":"asda","spokenLevel":9,"communicationLevel":10,"logicLevel":8,"aptitudeLevel":18,"confidenceLevel":10,"strength":"asda","weakness":"sdasd","skills":[{ "skill_id": 238, "skill_name": "HTML" ,"skill_level":10}],"strongSkills":""};
    component.delete(associate);
    expect(component).toBeTruthy();
  });
});