import { Component, OnInit } from '@angular/core';
import { Associate } from '../models/associate';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { SkillsService } from '../services/skills.service';
import { EmployeeService } from '../services/employee.service';
import { Skill } from '../models/skill';
import { ChangeDetectorRef } from '@angular/core';
// import {MatSliderModule} from '@angular/material/slider';
@Component({
  selector: 'app-associate',
  templateUrl: './associate.component.html',
  styleUrls: ['./associate.component.css']
})
export class AssociateComponent implements OnInit {

  associate: Associate = new Associate();
  page: String;
  associateId: string;
  header: String;
  level: String = "";
  status: String = "";
  skillList: Skill[];
  selectedFile: File;
  imageUrl: string = "/assets/images/placeholder_img.png";
  temp:any;
  status_blue:boolean;status_red:boolean;status_green:boolean;
  viewOnly:boolean = false;


  constructor(
    //This introduced for the snapshot error.
    private route: ActivatedRoute,
    private router: Router,
    private skillsService: SkillsService,
    private employeeService: EmployeeService,
    private changeDetectorRef: ChangeDetectorRef
    //Add services here
  ) { }

  ngAfterViewChecked() {
    this.changeDetectorRef.detectChanges();
  }


  ngOnInit() {
    this.getAllSkills();
    this.associateId = this.route.snapshot.paramMap.get('associateId');
    this.page = this.route.snapshot.data.page;
    if (this.page === "editassociate") {
      this.header = "update";
      var associateId = parseInt(this.associateId);
      this.populateAssociateDetails(associateId);
      // this.saveEmployee();
    }
    else if (this.page === "createassociate") {
      console.log("Creating Associate");
      this.header = "create";
      console.log(this.skillList);
      this.associate = new Associate();

    }else if(this.page === "viewassociate"){
      this.header = "update";
      var associateId = parseInt(this.associateId);
      this.populateAssociateDetails(associateId);
      this.viewOnly = true;
    }


  }
  populateAssociateDetails(associateId: number) {
    this.employeeService.getEmployeeById(associateId).subscribe(
      data => { 
        this.temp =data;
        this.associate = this.temp;
        this.skillList =  this.associate.skills;
        this.imageUrl = "data:image/png;base64," + this.associate.pic;
        // this.editcallback();
      },
      error => { },
      () => {
       
       }
    );
  }
 
 

  onAssociateFormSubmit() {
    console.log("Inside save");
    console.log(this.associate);
    this.saveEmployee();
  }
  onAssociateFormClear() {
    console.log("Inside clear");
    this.associate = new Associate();
  }

  onAssociateFormUpdateSubmit() {
    console.log("Inside update");
    console.log(this.associate);
    this.setImage();
    this.saveEmployee();
  }

  

  saveEmployee() {
    this.associate.skills = this.skillList;
    this.employeeService.saveEmployee(this.associate, this.selectedFile).subscribe(
      message => {
        if (message.message == "Success")
          this.router.navigate(["/searchassociate"])
      }
    );
  }

  getAllSkills() {
    this.skillsService.getSkills().subscribe(

      data => { console.log("getting skills"); this.skillList = data }
    )
  }


  setImage() {
    if (this.selectedFile == undefined) {
      let arr = this.imageUrl.split(','), mime = arr[0].match(/:(.*?);/)[1],
        bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
      while (n--) {
        u8arr[n] = bstr.charCodeAt(n);
      }
      this.selectedFile = new File([u8arr], "Filename.png", { type: mime });
    }
  }

  sliderChange(value: any, skill: Skill) {
    skill.skill_level = value;
  }


  adSkillsliderChange(value: number, type: string) {
    console.log("Inside the Additional skill Slider");
    switch (type) {
      case 'spokenLevel':
        console.log(value + "spokenLevel");
        this.associate.spokenLevel = value;
        break;

      case 'communicationLevel':
        console.log(value + "communicationLevel");
        this.associate.communicationLevel = value;
        break;

      case 'logicLevel':
        console.log(value + "logicLevel");
        this.associate.logicLevel = value;
        break;

      case 'aptitudeLevel':
        console.log(value + "aptitudeLevel");
        this.associate.aptitudeLevel = value;
        break;

      case 'confidenceLevel':
        console.log(value + "confidenceLevel");
        this.associate.confidenceLevel = value;
        break;


      default: {
        console.log("Default level reached");
      }


    }
  }
  setStatus(value) {
    console.log("Inside status with status value" + value);
    switch (value) {
      case 1:
        console.log(value + "chosen");
        this.associate.statusGreen = true;
        this.associate.statusBlue = false;
        this.associate.statusRed = false;
        break;

      case 2:
        console.log(value + "chosen");
        this.associate.statusGreen = false;
        this.associate.statusRed = true;
        this.associate.statusBlue = false;
        break;

      case 3:
        console.log(value + "chosen");
        this.associate.statusGreen = false;
        this.associate.statusRed = false;
        this.associate.statusBlue = true;
        break;

      default: {
        console.log("Default level reached");
      }


    }

  }
//For edit flows
setStatusOnEdit(status:number){

  }

  setLevels(value) {
    console.log("Inside levels with level value" + value);
    switch (value) {
      case 1:
        console.log(value + "chosen");
        this.associate.level1 = true;
        this.associate.level2 = false;
        this.associate.level3 = false;
        break;

      case 2:
        console.log(value + "chosen");
        this.associate.level1 = false;
        this.associate.level2 = true;
        this.associate.level3 = false;
        break;

      case 3:
        console.log(value + "chosen");
        this.associate.level1 = false;
        this.associate.level2 = false;
        this.associate.level3 = true;
        break;

      default: {
        console.log("Default level reached");
      }


    }

  }
  setGender(value) {
    console.log("Inside gender with gender value" + value);
    switch (value) {
      case 'M':
        console.log(value + " chosen");
        this.associate.gender = 'M';
        break;

      case 'F':
        console.log(value + " chosen");
        this.associate.gender = 'F';
        break;

      default: {
        console.log("Default gender reached");
      }


    }
  }

  handleFileInput(file: FileList) {
    this.selectedFile = file[0];
    var reader = new FileReader();
    reader.readAsDataURL(this.selectedFile);
    reader.onload = (event: any) => {
      this.imageUrl = event.target.result;
    }

  }

}
