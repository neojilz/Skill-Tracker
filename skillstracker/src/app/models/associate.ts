import { Skill } from "./skill";

export class Associate{
    constructor(){}
    associate_id:number;
    name:String;
    email:String;
    mobile:String;
    pic:Blob;
    status_green:boolean;
    status_red:boolean;
    status_blue:boolean;
    level1:boolean;
    level2:boolean;
    level3:boolean;
    gender:String;
    remark:String;
    strength:String;
    weakness:String;
    skills:Skill[];
}