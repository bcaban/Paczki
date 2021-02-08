import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {SearchComponent} from './components/search/search.component';
import {FooterComponent} from './components/footer/footer.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {HeaderMenuComponent} from './components/header-menu/header-menu.component';
import {ParcelComponent} from './components/parcel/parcel.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {LoggerModule, NgxLoggerLevel} from 'ngx-logger';
import { AdminPanelComponent } from './components/admin-panel/admin-panel.component';
import { CalculateSizeComponent } from './components/calculate-size/calculate-size.component';
import { CreateParcelComponent } from './components/create-parcel/create-parcel.component';
import {DlDateTimeDateModule, DlDateTimePickerModule} from 'angular-bootstrap-datetimepicker';
import { UpdateAccessComponent } from './components/update-access/update-access.component';

@NgModule({
  declarations: [
    AppComponent,
    SearchComponent,
    FooterComponent,
    HeaderMenuComponent,
    ParcelComponent,
    CalculateSizeComponent,
    AdminPanelComponent,
    CreateParcelComponent,
    UpdateAccessComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    NgbModule,
    ReactiveFormsModule,
    LoggerModule.forRoot({
      serverLoggingUrl: '/api/logs',
      level: NgxLoggerLevel.DEBUG,
      serverLogLevel: NgxLoggerLevel.ERROR
    }),
    DlDateTimeDateModule,  // <--- Determines the data type of the model
    DlDateTimePickerModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
