import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Parcel} from '../common/parcel';
import {HttpClient} from '@angular/common/http';
import {NGXLogger} from 'ngx-logger';

@Injectable({
  providedIn: 'root'
})
export class ParcelService {
  private parcelsURL = 'http://localhost:8080/parcelservice/v1/parcels';

  constructor(private httpClient: HttpClient, private logger: NGXLogger) {
  }

  getParcel(parcelId: string): Observable<Parcel> {
    const parcelURL = this.parcelsURL + '/' + parcelId;

    this.logger.info('Getting parcel from: {}', parcelURL);

    return this.httpClient.get<Parcel>(parcelURL);
  }
}
