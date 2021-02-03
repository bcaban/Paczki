import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ParcelComponent} from './components/parcel/parcel.component';
import {SearchComponent} from './components/search/search.component';
import {AdminPanelComponent} from './components/admin-panel/admin-panel.component';
import { CalculateSizeComponent } from './components/calculate-size/calculate-size.component';
import {CreateParcelComponent} from './components/create-parcel/create-parcel.component';
import {UpdateAccessComponent} from './components/update-access/update-access.component';
import {ContactComponent} from './components/contact/contact.component';
import {ServicesComponent} from './components/company-services/services.component';
import {AboutUsComponent} from './components/about-us/about-us.component';

const routes: Routes = [
  {path: 'about-us', component: AboutUsComponent},
  {path: 'company-services', component: ServicesComponent},
  {path: 'contact', component: ContactComponent},
  {path: 'create-parcel/postsml', component: CreateParcelComponent},
  {path: 'calculate-size/ifsml', component: CalculateSizeComponent},
  {path: 'update-access', component: UpdateAccessComponent},
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
