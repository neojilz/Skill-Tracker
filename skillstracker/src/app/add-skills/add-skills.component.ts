import { Component, OnInit } from '@angular/core';
import {Skill} from '../models/skill';
import {SkillsService} from '../services/skills.service';
@Component({
  selector: 'app-add-skills',
  templateUrl: './add-skills.component.html',
  styleUrls: ['./add-skills.component.css']
})
export class AddSkillsComponent implements OnInit {

  newSkillName:String;
  skill:Skill;
  isSkillDisabled:boolean=true;
  skillList:Skill[]=[{
    "skill_id":10,
    "skill_name":"HTML"

  },{
    "skill_id":12,
    "skill_name":"Java"
  },
  {
    "skill_id":13,
    "skill_name":"Mule"
  }
  ];

  constructor(private skillsService:SkillsService) { }

  ngOnInit() {
    this.getSkills();
  }

  addSkills(){
    this.skill =  new Skill;
    this.skill.skill_name = this.newSkillName;
    this.skillsService.addSkills(this.skill).subscribe(
      data =>{},
      error => {},
      ()=> {
        this.getSkills();
        this.newSkillName="";
      })
  }


  getSkills(){
    this.skillsService.getSkills().subscribe(
      data => this.skillList, 
      error => {},
      () => {}

    )

  }

  editSkill(){
    this.isSkillDisabled = false;
  }

}
