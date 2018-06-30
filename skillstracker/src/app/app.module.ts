import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {Route,Router,RouterModule} from '@angular/router';
import {FormsModule,ReactiveFormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './/app-routing.module';
import { AssociateSearchComponent } from './associate-search/associate-search.component';
import { AddSkillsComponent } from './add-skills/add-skills.component';

import {SkillsService } from './services/skills.service';
import { AssociateComponent } from './associate/associate.component';
import { AssociateEmailPipe } from './pipes/associate-email.pipe';
import { AssociateIdPipe } from './pipes/associate-id.pipe';
import { AssociateNamePipe } from './pipes/associate-name.pipe';
import { AssociatePhonePipe } from './pipes/associate-phone.pipe';
import { AssociateSkillPipe } from './pipes/associate-skill.pipe';
import { SkillPipe } from './pipes/skill.pipe';


@NgModule({
  declarations: [
    AppComponent,
    AssociateSearchComponent,
    AddSkillsComponent,
    AssociateComponent,
    AssociateEmailPipe,
    AssociateIdPipe,
    AssociateNamePipe,
    AssociatePhonePipe,
    AssociateSkillPipe,
    SkillPipe,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule
  ],
  providers: [SkillsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
