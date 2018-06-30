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
    console.log("Inside save");
    console.log(this.associate);
  }
  onAssociateFormClear(){
    console.log("Inside clear");
    this.associate =new Associate();
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

  setStatus(value){
    console.log("Inside status with status value" + value);
    switch (value){
    case 1 : 
      console.log(value+"chosen");
      this.associate.status_green = true;
      this.associate.status_blue = false;
      this.associate.status_red = false; 
      break;
    
    case 2 : 
      console.log(value+"chosen");
      this.associate.status_green = false;
      this.associate.status_red = true;
      this.associate.status_blue = false; 
      break;
    
    case 3 : 
      console.log(value+"chosen");
      this.associate.status_green = false;
      this.associate.status_red = false;
      this.associate.status_blue = true; 
      break;
    
    default : {
      console.log("Default level reached");
    }
    
    
    }
      
    }

setLevels(value){
console.log("Inside levels with level value" + value);
switch (value){
case 1 : 
  console.log(value+"chosen");
  this.associate.level1 = true;
  this.associate.level2 = false;
  this.associate.level3 = false; 
  break;

case 2 : 
  console.log(value+"chosen");
  this.associate.level1 = false;
  this.associate.level2 = true;
  this.associate.level3 = false; 
  break;

case 3 : 
  console.log(value+"chosen");
  this.associate.level1 = false;
  this.associate.level2 = false;
  this.associate.level3 = true; 
  break;

default : {
  console.log("Default level reached");
}


}
  
}
setGender(value){
  console.log("Inside gender with gender value" + value);
  switch (value){
  case 'M' : 
    console.log(value+" chosen");
    this.associate.gender = 'M';
    break;
  
  case 'F' : 
    console.log(value+" chosen");
    this.associate.gender = 'F'; 
    break;
  
  default : {
    console.log("Default gender reached");
  }
  
  
  }
}

}
