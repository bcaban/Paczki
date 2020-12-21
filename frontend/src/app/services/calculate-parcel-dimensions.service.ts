import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {NGXLogger} from 'ngx-logger';
import {ParcelDimensions} from '../common/parcel-dimensions';
import {ParcelSizeHolder} from '../common/parcel-size-holder';

@Injectable({
  providedIn: 'root'
})
export class CalculateParcelDimensionsService {
  private PARCELS_URL = 'http://localhost:8080/parcelservice/v1/dimensions';
  constructor(private httpClient: HttpClient, private logger: NGXLogger) {
  }
  postDimensions(dummy: ParcelDimensions): Observable<ParcelSizeHolder> {
    const parcelURL = this.PARCELS_URL;
    this.logger.info('Sending values to {}:', parcelURL);
    return this.httpClient.post<ParcelSizeHolder>(parcelURL, dummy);
  }
}
