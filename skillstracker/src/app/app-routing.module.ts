import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {Routes,RouterModule} from '@angular/router';
import {AddSkillsComponent} from './add-skills/add-skills.component';
import {AssociateSearchComponent} from './associate-search/associate-search.component';
import {AssociateComponent} from './associate/associate.component';

const route:Routes =[
{path:'searchassociate',component:AssociateSearchComponent},
{path:'addskills',component:AddSkillsComponent},
{path:'addemployee',component:AssociateComponent,data:{page:'createassociate'}},
{path:'editemployee/:associateId',component:AssociateComponent,data:{page:'editassociate'}},
{path:'viewemployee/:associateId',component:AssociateComponent,data:{page:'viewassociate'}},
];

@NgModule({
  imports: [RouterModule.forRoot(route,{enableTracing:false})
  ],
  exports:[RouterModule],
  declarations: []
})
export class AppRoutingModule { }
