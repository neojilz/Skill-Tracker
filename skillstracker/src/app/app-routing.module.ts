import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {Routes,RouterModule} from '@angular/router';
import {AddSkillsComponent} from './add-skills/add-skills.component';
import {AssociateSearchComponent} from './associate-search/associate-search.component';

const route:Routes =[
{path:'searchassociate',component:AssociateSearchComponent},
{path:'addskills',component:AddSkillsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(route,{enableTracing:true})
  ],
  exports:[RouterModule],
  declarations: []
})
export class AppRoutingModule { }
