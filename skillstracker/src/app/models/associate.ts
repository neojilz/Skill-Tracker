import { Skill } from "./skill";

export class Associate{
    constructor(){}
    associateId:number;
    name:String;
    email:String;
    mobile:String;
    pic:Blob;
    statusGreen:boolean;
    statusRed:boolean;
    statusBlue:boolean;
    level1:boolean;
    level2:boolean;
    level3:boolean;
    gender:String;
    remark:String;
    strength:String;
    weakness:String;
    skills:Skill[];
    spokenLevel : number=0;
	communicationLevel: number=0;
	logicLevel: number=0;
	aptitudeLevel: number=0;
    confidenceLevel: number=0;
    strongSkills: string;
}