import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Parcel} from '../common/parcel';
import {HttpClient} from '@angular/common/http';
import {NGXLogger} from 'ngx-logger';
import {ParcelStatus} from '../common/parcel-status';
import {ParcelHistories} from '../common/parcel-histories';

@Injectable({
  providedIn: 'root'
})
export class ParcelService {
  private PARCELS_URL = 'http://localhost:8080/parcelservice/v1/parcels';
  private STATUS = '/status';
  private HISTORY = '/history';

  constructor(private httpClient: HttpClient, private logger: NGXLogger) {
  }

  createParcel(parcel: Parcel): Observable<Parcel> {
    const parcelURL = this.PARCELS_URL;

    this.logger.info('Sending values to {}:', parcelURL);

    return this.httpClient.post<Parcel>(parcelURL, parcel);
  }

  getParcel(parcelId: string): Observable<Parcel> {
    const parcelURL = this.PARCELS_URL + '/' + parcelId;

    this.logger.info('Getting parcel {} from:  {}', parcelId, parcelURL);

    return this.httpClient.get<Parcel>(parcelURL);
  }

  updateParcelStatus(parcelId: string, newStatus: ParcelStatus): Observable<Object> {
    const parcelURL = this.PARCELS_URL + '/' + parcelId + this.STATUS;
    const updateParcelBody = { status: newStatus };

    this.logger.info('Changing parcel {} status to {} at:  {}', parcelId, newStatus, parcelURL);

    return this.httpClient.put<Object>(parcelURL, updateParcelBody);
  }

  getParcelHistory(parcelId: string): Observable<ParcelHistories> {
    const parcelURL = this.PARCELS_URL + '/' + parcelId + this.HISTORY;

    this.logger.info('Getting parcel history {} from:  {}', parcelId, parcelURL);

    return this.httpClient.get<ParcelHistories>(parcelURL);
  }
}
