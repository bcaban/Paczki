import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Parcel} from '../common/parcel';
import {HttpClient} from '@angular/common/http';
import {NGXLogger} from 'ngx-logger';
import {ParcelStatus} from '../common/parcel-status';
import {ParcelHistories} from '../common/parcel-histories';
import {ParcelAccess} from '../common/parcel-access';

@Injectable({
  providedIn: 'root'
})
export class ParcelService {
  private PARCELS_URL = 'http://localhost:8080/parcelservice/v1/parcels';
  private STATUS = '/status';
  private CLIENTCODE = '/clientcode';
  private HISTORY = '/history';
  private ADDRESS = '//address';
  private NAME = '/name';
  private ACCESS = '/access';

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

  cancelStatus(parcelId: string): Observable<Object> {
    const parcelURL = this.PARCELS_URL + '/' + parcelId + this.STATUS;
    const updateParcelBody = {status: ParcelStatus.CANCELLED};

    this.logger.info('Cancelling parcel {} status to {} at:  {}', parcelId, parcelURL);

    return this.httpClient.put<Object>(parcelURL, updateParcelBody);
  }

  updateParcelStatus(parcelId: string, newStatus: ParcelStatus): Observable<Object> {
    const parcelURL = this.PARCELS_URL + '/' + parcelId + this.STATUS;
    const updateParcelBody = {status: newStatus};

    this.logger.info('Changing parcel {} status to {} at:  {}', parcelId, newStatus, parcelURL);

    return this.httpClient.put<Object>(parcelURL, updateParcelBody);
  }

  getParcelHistory(parcelId: string): Observable<ParcelHistories> {
    const parcelURL = this.PARCELS_URL + '/' + parcelId + this.HISTORY;

    this.logger.info('Getting parcel history {} from:  {}', parcelId, parcelURL);

    return this.httpClient.get<ParcelHistories>(parcelURL);
  }

  changeDeliveryAddress(parcelId: string, newReceiverCity: string, newReceiverPostCode: string, newReceiverStreet: string): Observable<Parcel> {
    const parcelURL = this.PARCELS_URL + '/' + parcelId + this.ADDRESS;
    const changeDeliveryAddressBody = {receiverCity: newReceiverCity, receiverPostCode: newReceiverPostCode, receiverStreet: newReceiverStreet};

    this.logger.info('Changing parcel {} address to {}, from:  {}', parcelId, newReceiverCity, newReceiverPostCode, newReceiverStreet, parcelURL);

    return this.httpClient.put<Parcel>(parcelURL, changeDeliveryAddressBody);
  }

  setParcelName(parcelId: string, newName: string): Observable<Parcel> {
    const parcelURL = this.PARCELS_URL + '/' + parcelId + this.NAME;
    const changeParcelNameBody = {name: newName};

    this.logger.info('Changing parcel {} name to: {}, from: {}', parcelId, name, parcelURL);

    return this.httpClient.put<Parcel>(parcelURL, changeParcelNameBody);
  }
  getClientCode(parcelId: string): Observable<ParcelAccess> {
    const parcelURL = this.PARCELS_URL + '/' + parcelId + this.CLIENTCODE;
    return this.httpClient.get<ParcelAccess>(parcelURL);
  }

  getParcelAccessStatusClient(parcelId: string, clientAccessCode: string): Observable<ParcelAccess> {
    const parcelURL = this.PARCELS_URL + '/' + parcelId + this.ACCESS;
    const clientAccessBody = { clientCode: clientAccessCode };

    this.logger.info('Checking access status for parcel ' + parcelId + ' with code ' + clientAccessCode + ' from ' + parcelURL);

    return this.httpClient.post<ParcelAccess>(parcelURL, clientAccessBody);
  }

  getParcelAccessStatusAdmin(parcelId: string, adminAccessCode: string): Observable<ParcelAccess> {
    const parcelURL = this.PARCELS_URL + '/' + parcelId + this.ACCESS;
    const adminAccessBody = { adminCode: adminAccessCode };

    this.logger.info('Checking access status for parcel ' + parcelId + ' with code ' + adminAccessCode + ' from ' + parcelURL);

    return this.httpClient.post<ParcelAccess>(parcelURL, adminAccessBody);
  }

  getParcelByNameAndClientAccessCode(name: string, clientAccessCode: string): Observable<Parcel> {
    const parcelURL = this.PARCELS_URL + '?name=' + name + '&accessCode=' + clientAccessCode;
    this.logger.info('Searching for parcel by name ' + name + ' with code ' + clientAccessCode + ' from ' + parcelURL);

    return this.httpClient.get<Parcel>(parcelURL);
  }

  changeTimeToDeliver(parcelId: string, newDaysLeft: number): Observable<Parcel> {
    const parcelURL = this.PARCELS_URL + '/' + parcelId + '/timeToDeliver';
    this.logger.info('Changing parcel {} time to deliver to {}', parcelId, newDaysLeft);

    const newDate = { timeToDeliver: 'P' + newDaysLeft + 'D' };

    return this.httpClient.put<Parcel>(parcelURL, newDate);
  }
}
