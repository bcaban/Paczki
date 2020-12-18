import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Parcel} from '../common/parcel';
import {HttpClient} from '@angular/common/http';
import {NGXLogger} from 'ngx-logger';
import {ParcelSize} from '../common/parcel-size';
// import { Http , Response , RequestOptions , Headers } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CalculateSize {
  private PARCELS_URL = 'http://localhost:8080/parcelservice/v1/parcels';
  private size = '/size';

  constructor(private httpClient: HttpClient, private logger: NGXLogger) {
  }

  getParcel(parcelId: string): Observable<Parcel> {
    const parcelURL = this.PARCELS_URL + '/' + parcelId;

    this.logger.info('Getting parcel {} from:  {}', parcelId, parcelURL);

    return this.httpClient.get<Parcel>(parcelURL);
  }

  updateParcelSize(parcelId: string, newSize: ParcelSize): Observable<Object> {
    const parcelURL = this.PARCELS_URL + '/' + parcelId + this.size;
    const updateParcelBody = { size: newSize };

    this.logger.info('Changing parcel {} size to {} at:  {}', parcelId, newSize, parcelURL);

    return this.httpClient.put<Object>(parcelURL, updateParcelBody);
  }
}
// w parcelservice zobaczyć i analogicznie na POST
