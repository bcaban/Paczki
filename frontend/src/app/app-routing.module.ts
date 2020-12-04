import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ParcelComponent} from './components/parcel/parcel.component';
import {SearchComponent} from './components/search/search.component';

const routes: Routes = [
  {path: 'search/:parcelId', component: ParcelComponent},
  {path: 'search', component: SearchComponent},
  {path: '**', redirectTo: '/search', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
