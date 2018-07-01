import { Injectable } from '@angular/core';
import { Response, Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

import {  ResponseObj} from '../models/response';
import { Associate } from '../models/associate';
import { MockData} from './mock.data';

export class MockEmployeeService {
 
    mockdata=new MockData();
    mockAssociateDetails= this.mockdata.mockAssociateDetails;

    getAllEmployees(): Observable<Associate[]> {
        return Observable.of(this.mockAssociateDetails);
    }

    saveEmployee(details: Associate, file: File) {
        return  Observable.of(new ResponseObj("Success"));
    }

    getEmployeeById(id: number): Observable<Associate> {
        return Observable.of(this.mockAssociateDetails[0]);
    }

    deleteEmployee(details: Associate) {
        return  Observable.of(new ResponseObj("Success"));
    } 
    

    
}