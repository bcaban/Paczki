import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ParcelComponent} from './components/parcel/parcel.component';
import {SearchComponent} from './components/search/search.component';
import {AdminPanelComponent} from './components/admin-panel/admin-panel.component';
import { CalculateSizeComponent } from './components/calculate-size/calculate-size.component';

const routes: Routes = [
  {path: 'calculate-size/ifsml', component: CalculateSizeComponent},
  {path: 'search/:parcelId', component: ParcelComponent},
  {path: 'search', component: SearchComponent},
  {path: 'management', component: AdminPanelComponent},
  {path: '**', redirectTo: '/search', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
