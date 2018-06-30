import { Injectable } from '@angular/core';
import { Response, Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

import { SERVICE_URI } from '../configuration';
import { ResponseObj } from '../models/response';
import { Associate } from '../models/associate';

@Injectable()
export class EmployeeService {

  constructor(private http: Http) { }

  
  _addEmployee = SERVICE_URI+"/save";
  _getAllEmp = SERVICE_URI+"/getall"
  _getAssociateById = SERVICE_URI+"/getassociate/"; 
  _deleteEmployee = SERVICE_URI+"/delete/";

  getAllEmployees(): Observable<Associate[]> {
    return this.http.get(this._getAllEmp).map(this.extractData)
      .catch(this.handleErrorObservable);
  }

  saveEmployee(details: Associate, file: File) {
    const formData: FormData = new FormData();
    formData.append('file', file, file.name);
    formData.append('associateDetails', JSON.stringify(details));
    return this.http.post(this._addEmployee, formData).map(this.extractData)
      .catch(this.handleErrorObservable);
  }

  getEmployeeById(id: number): Observable<Associate> {
    return this.http.get(this._getAssociateById + id).map(this.extractData)
      .catch(this.handleErrorObservable);
  }

  deleteEmployee(details: Associate) {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.post(this._deleteEmployee, details, options).map(this.extractData)
      .catch(this.handleErrorObservable);
  }
  private extractData(res: Response) {
    let body = res.json();
    return body || {};
  }

  private handleErrorObservable(error: Response | any) {
    console.error("This is error: " + error.message || error);
    return Observable.throw(error.message || error);
  }
}