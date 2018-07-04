import { Associate } from '../models/associate';
import { Skill } from '../models/skill';

export class MockData {
    pic1:Blob= new Blob();
    public mockAssociateDetails: Associate[] = [
        { "associateId": 16516, "name": "jijo", "email": "jijokrishnan@gmail.com", "mobile": "4564645645", "gender": "Male", "pic": this.pic1, "statusGreen": false, "statusBlue": true, "statusRed": false, "level1": false, "level2": true, "level3": false, "remark": "asda", "spokenLevel": 9, "communicationLevel": 10, "logicLevel": 8, "aptitudeLevel": 18, "confidenceLevel": 10, "strength": "asda", "weakness": "sdasd", "skills": [{ "skill_id": 238, "skill_name": "HTML", "skill_level": 10 }],"strongSkills": "" },
        { "associateId": 16517, "name": "jani", "email": "jijokrishnan@gmail.com", "mobile": "4564645645", "gender": "Female", "pic": this.pic1, "statusGreen": true, "statusBlue": false, "statusRed": false, "level1": true, "level2": false, "level3": false, "remark": "asda", "spokenLevel": 9, "communicationLevel": 10, "logicLevel": 8, "aptitudeLevel": 18, "confidenceLevel": 10, "strength": "asda", "weakness": "sdasd", "skills": [{ "skill_id": 238, "skill_name": "HTML", "skill_level": 10 }],"strongSkills": "" },
        { "associateId": 16518, "name": "Juni", "email": "jijokrishnan@gmail.com", "mobile": "4564645644", "gender": "Female", "pic": this.pic1, "statusGreen": false, "statusBlue": false, "statusRed": true, "level1": false, "level2": false, "level3": true, "remark": "asda", "spokenLevel": 9, "communicationLevel": 10, "logicLevel": 8, "aptitudeLevel": 18, "confidenceLevel": 10, "strength": "asda", "weakness": "sdasd", "skills": [{ "skill_id": 238, "skill_name": "HTML", "skill_level": 10 }],"strongSkills": "Jenkins,Spring boot,Bootstrap,Spring,Angular 2" },
  ];

    public mockSkillArry: Skill[] = [
        { "skill_id": 238, "skill_name": "HTML", "skill_level": 10 },
        { "skill_id": 235, "skill_name": "CSS", "skill_level": 11 }
    ];

}