import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import {AppModule} from '../app.module';
import { AddSkillsComponent } from './add-skills.component';
import {Skill} from '../models/skill';
import {SkillsService} from '../services/skills.service';
import { NgModule } from '@angular/core';
import { APP_BASE_HREF } from '@angular/common';

import {FormsModule,ReactiveFormsModule} from '@angular/forms';
describe('AddSkillsComponent', () => {
  let component: AddSkillsComponent;
  let fixture: ComponentFixture<AddSkillsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [],
      imports:[AppModule,FormsModule,ReactiveFormsModule],
      providers:[SkillsService,{provide: APP_BASE_HREF, useValue:'/'}]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddSkillsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
