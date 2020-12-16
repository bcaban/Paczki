import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {SearchComponent} from './components/search/search.component';
import {FooterComponent} from './components/footer/footer.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {HeaderMenuComponent} from './components/header-menu/header-menu.component';
import {ParcelComponent} from './components/parcel/parcel.component';
import {ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {LoggerModule, NgxLoggerLevel} from 'ngx-logger';

@NgModule({
  declarations: [
    AppComponent,
    SearchComponent,
    FooterComponent,
    HeaderMenuComponent,
    ParcelComponent
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
        })
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
