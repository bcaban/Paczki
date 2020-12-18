import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Parcel} from '../common/parcel';
import {HttpClient} from '@angular/common/http';
import {NGXLogger} from 'ngx-logger';
import {ParcelSize} from '../common/parcel-size';
import {DummyParcelDimensions} from '../common/dummy-parcel-dimensions';

@Injectable({
  providedIn: 'root'
})
// w parcelservice zobaczyć i analogicznie na POST
export class PostDimensionsService {
  private PARCELS_URL = 'http://localhost:8080/parcelservice/v1';
  private dims = '/dimensions';
  constructor(private httpClient: HttpClient, private logger: NGXLogger) {
  }
  postDimensions(dummy: DummyParcelDimensions): Observable<Object> {
    const parcelURL = this.PARCELS_URL + this.dims;
    this.logger.info('Chyba zatrybiło?');
    return this.httpClient.post<Object>(parcelURL, dummy);
  }
}
