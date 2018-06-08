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
  editedSkillId:number;
  editText:String = "Edit";
  skillList:Skill[];
  // skillList:Skill[]=[{
  //   "skill_id":10,
  //   "skill_name":"HTML"

  // },{
  //   "skill_id":12,
  //   "skill_name":"Java"
  // },
  // {
  //   "skill_id":13,
  //   "skill_name":"Mule"
  // }
  // ];

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
      data => this.skillList =data,  
      error => {},
      () => {}

    )

  }

  editSkill(editingSkill:Skill){
    if(this.editedSkillId === editingSkill.skill_id){
      this.skill = new Skill();
      this.skill.skill_id=editingSkill.skill_id;
      this.skill.skill_name=editingSkill.skill_name;
      this.skillsService.addSkills(editingSkill).subscribe(
          data => {},
          error => {},
          () => {
            this.editText = "Edit";
            this.editedSkillId=0;
            this.getSkills();
          }
      )

    }
    else {
      this.editedSkillId = editingSkill.skill_id;
      this.editText = "Update";
    }
   
  }
  deleteSkill(deletingSkill:Skill){
    this.skillsService.deleteSkill(deletingSkill).subscribe(
      data => {},
      error => {},
      () =>{
        this.getSkills();
      }
    )
  }

}
