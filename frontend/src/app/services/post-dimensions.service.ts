import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {NGXLogger} from 'ngx-logger';
import {DummyParcelDimensions} from '../common/dummy-parcel-dimensions';

@Injectable({
  providedIn: 'root'
})
export class PostDimensionsService {
  private PARCELS_URL = 'http://localhost:8080/parcelservice/v1/dimensions';
  constructor(private httpClient: HttpClient, private logger: NGXLogger) {
  }
  postDimensions(dummy: DummyParcelDimensions): Observable<Object> {
    const parcelURL = this.PARCELS_URL;
    this.logger.info('Chyba zatrybiło?');
    return this.httpClient.post<Object>(parcelURL, dummy);
  }
}
