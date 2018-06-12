import { Component, OnInit } from '@angular/core';
import {Associate} from '../models/associate';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { SkillsService } from '../services/skills.service';
import { Skill } from '../models/skill';
@Component({
  selector: 'app-associate',
  templateUrl: './associate.component.html',
  styleUrls: ['./associate.component.css']
})
export class AssociateComponent implements OnInit {

  associate:Associate;
  page:String;
  associateId:String;
  header:String;
  skillList:Skill[];
  associateForm =  new FormGroup({
    name: new FormControl('',Validators.required),
    associateId: new FormControl('',Validators.required),
    email: new FormControl('',Validators.required),
    mobile: new FormControl('',Validators.required),
    status_green: new FormControl('',Validators.required),
    status_red: new FormControl('',Validators.required),
    status_blue: new FormControl('',Validators.required),
    level1:new FormControl('',Validators.required),
    level2:new FormControl('',Validators.required),
    level3:new FormControl('',Validators.required),
    remark:new FormControl('',Validators.required),
    strength:new FormControl('',Validators.required),
    weakness:new FormControl('',Validators.required),
    skillsList:new FormGroup({skill: new FormControl('',Validators.required)}),
    
  })

  constructor(
    //This introduced for the snapshot error.
    private route: ActivatedRoute,
    private router: Router,
    private skillsService:SkillsService
    //Add services here
  ) { }

  ngOnInit() {
    this.associateId = this.route.snapshot.paramMap.get('associateId');
    this.page = this.route.snapshot.data.page;
    if(this.page === "editassociate"){
      this.header ="update";
      this.saveEmployee();
    }
    else if (this.page === "createassociate") {
      this.header ="create";
      this.associate = new Associate();
      this.associateForm.setValue({
        skillList:this.skillList
      });
    }
    

  }

  saveEmployee(){

  }

  getAllSkills(){
    this.skillsService.getSkills().subscribe(
      data => this.skillList =data,  
      error => {},
      () => {
      }

    )
  }

}
