import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {NGXLogger} from 'ngx-logger';
import {DummyParcelDimensions} from '../common/dummy-parcel-dimensions';
import {ParcelSizeHolder} from '../common/parcel-size-holder';

@Injectable({
  providedIn: 'root'
})
export class PostDimensionsService {
  private PARCELS_URL = 'http://localhost:8080/parcelservice/v1/dimensions';
  constructor(private httpClient: HttpClient, private logger: NGXLogger) {
  }
  postDimensions(dummy: DummyParcelDimensions): Observable<ParcelSizeHolder> {
    const parcelURL = this.PARCELS_URL;
    this.logger.info('Chyba zatrybiło?');
    return this.httpClient.post<ParcelSizeHolder>(parcelURL, dummy);
  }
}
