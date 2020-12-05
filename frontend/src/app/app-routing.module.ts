import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PackageComponent} from './components/package/package.component';
import {SearchComponent} from './components/search/search.component';

const routes: Routes = [
  {path: 'search/:packageId', component: PackageComponent},
  {path: 'search', component: SearchComponent},
  {path: '**', redirectTo: '/search', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
