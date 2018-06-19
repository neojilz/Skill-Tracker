import { Component, OnInit } from '@angular/core';
import {Associate} from '../models/associate';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { SkillsService } from '../services/skills.service';
import { Skill } from '../models/skill';
import { ChangeDetectorRef } from '@angular/core';
// import {MatSliderModule} from '@angular/material/slider';
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
  level:String ="";
  status:String = "";
  skillList:Skill[];
  
  // associateForm =  new FormGroup({
  //   name: new FormControl('Hello',Validators.required),
  //   associateId: new FormControl('',Validators.required),
  //   email: new FormControl('',Validators.required),
  //   mobile: new FormControl('',Validators.required),
  //   status_green: new FormControl('',Validators.required),
  //   status_red: new FormControl('',Validators.required),
  //   status_blue: new FormControl('',Validators.required),
  //   level1:new FormControl('',Validators.required),
  //   level2:new FormControl('',Validators.required),
  //   level3:new FormControl('',Validators.required),
  //   gender:new FormControl('',Validators.required),
  //   remark:new FormControl('',Validators.required),
  //   strength:new FormControl('',Validators.required),
  //   weakness:new FormControl('',Validators.required),
  //   // skillsArray:new FormControl('',Validators.required),
  //   // skillsList:new FormControl('',Validators.required),
  //  skillsList:new FormGroup({skillForm: new FormControl('',Validators.required)}),
    
  // })

  constructor(
    //This introduced for the snapshot error.
    private route: ActivatedRoute,
    private router: Router,
    private skillsService:SkillsService,
    private changeDetectorRef:ChangeDetectorRef
    //Add services here
  ) { }

  ngAfterViewChecked() {
    this.changeDetectorRef.detectChanges();
  }


  ngOnInit() {
    this.getAllSkills();
    this.associateId = this.route.snapshot.paramMap.get('associateId');
    this.page = this.route.snapshot.data.page;
    if(this.page === "editassociate"){
      this.header ="update";
      this.saveEmployee();
    }
    else if (this.page === "createassociate") {
      console.log("Creating Associate");
      this.header ="create";
      console.log(this.skillList);
      this.associate = new Associate();
      // this.associateForm.setValue({
      //    skillList:this.skillList
      //  });
    }
    

  }

  onAssociateFormSubmit(){

  }

  saveEmployee(){

  }

  getAllSkills(){
    this.skillsService.getSkills().subscribe(

     data => { console.log("getting skills"); this.skillList = data }
    )
  }

sliderChange(value: any, skill: Skill) {
    skill.skill_level = value;
  }


setRedStatus(){
  
  
}

setGreenStatus(){
 
}

setBlueStatus(){
 
}

setLevels(value){
console.log("Inside levels");
  
}


}
