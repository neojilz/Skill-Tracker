import { Injectable } from '@angular/core';
import { Skill } from '../models/skill';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { Response, Http, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import { SERVICE_URI } from '../configuration';


@Injectable()
export class SkillsService {


  _addSkillsURL = SERVICE_URI+"/addskills";
  _getSkillsURL = SERVICE_URI+"/getskills";

  constructor(private _http: Http) { }

  addSkills(skill: Skill): Observable<any> {
    return this._http.post(this._addSkillsURL, skill)
      .map(res => {
        this.responseHandling(res);
      });
  }

  getSkills():Observable<Skill[]>{
    return this._http.get(this._getSkillsURL)
    .map((response:Response)=><Skill[]> response.json())
  }




  responseHandling(res) {
    console.log(res);
    if (res.status === 200) {
      console.log("Successfully saved");}
    else {
        throw new Error('Save Failed');
      }
    }
  }


