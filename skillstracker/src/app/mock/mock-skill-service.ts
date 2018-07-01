import { Skill } from '../models/skill';
import { ResponseObj } from '../models/response';
import { Observable } from "rxjs/Rx";
import { MockData} from './mock.data';

export class MockSkillService {

    mockdata=new MockData();
    mockSkillArray= this.mockdata.mockSkillArry;
    getAllSkills(): Observable<Skill[]> {
        return Observable.of(this.mockSkillArray);
    }
    save(skill: Skill): Observable<ResponseObj> {
        this.mockSkillArray.push(skill);
        return  Observable.of(new ResponseObj("Success"));
    }

    delete(skill: Skill): Observable<ResponseObj> {
        var index = this.mockSkillArray.indexOf(skill);
        this.mockSkillArray.splice(index, 1); 
        return  Observable.of(new ResponseObj("Success"));
    }
}