import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BuildingFormComponent } from './lore/containers/building-form/building-form.component';
import { BuildingListComponent } from './lore/containers/building-list/building-list.component';
import { HumanFormComponent } from './lore/containers/human-form/human-form.component';
import { HumanListComponent } from './lore/containers/human-list/human-list.component';

const routes: Routes = [
	{ path: 'buildings', component: BuildingListComponent },
	{ path: 'building/:id', component: BuildingFormComponent },
	{ path: 'humans', component: HumanListComponent },
	{ path: 'humans/:id', component: HumanFormComponent },
	{ path: '**', redirectTo: '/humans', pathMatch: 'full' },
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule],
})
export class AppRoutingModule {}
