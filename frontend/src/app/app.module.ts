import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {SearchComponent} from './components/search/search.component';
import {FooterComponent} from './components/footer/footer.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {HeaderMenuComponent} from './components/header-menu/header-menu.component';
import {PackageComponent} from './components/package/package.component';
import {ReactiveFormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    SearchComponent,
    FooterComponent,
    HeaderMenuComponent,
    PackageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
