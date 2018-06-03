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


@NgModule({
  declarations: [
    AppComponent,
    AssociateSearchComponent,
    AddSkillsComponent,
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
