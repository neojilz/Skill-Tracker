import { TestBed, inject } from '@angular/core/testing';
import { MockBackend, MockConnection } from '@angular/http/testing';
import { SkillsService } from './skills.service';
import { Http, HttpModule, Response, ResponseOptions, RequestMethod, RequestOptions, ResponseType, BaseRequestOptions } from '@angular/http';
import { Skill } from '../models/skill';

describe('SkillsService', () => {
  let subject: SkillsService;
  let backend: MockBackend;
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SkillsService,
        MockBackend,
        BaseRequestOptions,
        {
          provide: Http,
          useFactory: (mockBackEnd: MockBackend, defaultOptions: RequestOptions) => {
            return new Http(mockBackEnd, defaultOptions);
          },
          deps: [MockBackend, BaseRequestOptions]
        }]
    });
  });

  beforeEach(inject([SkillsService, MockBackend], (service, mockBackend) => {
    subject = service;
    backend = mockBackend;
  }))


  it('should be created', inject([SkillsService], (service: SkillsService) => {
    expect(service).toBeTruthy();
  }));

  it('should get all skills from get skills service', (done) => {
    let skills: Skill[] = [{
      skill_id: 20,
      skill_name: "Mule",
      skill_level: 18
    }]

    backend.connections.subscribe((connection:MockConnection) => {
      expect(connection.request.url).toEqual('http://localhost:8082/SkillTracker/getallskills');
      expect(connection.request.method).toEqual(RequestMethod.Get);
      let options =  new ResponseOptions({
        body:skills,
        status:200
      });
      connection.mockRespond(new Response(options));
    });
    
    subject.getSkills().subscribe(response => {
      this.list =response;
      expect(response).toEqual(skills);
      done();
    })
  })
});


