import {Component, OnInit} from '@angular/core';
import {ParcelService} from '../../services/parcel.service';
import {ParcelStatus} from '../../common/parcel-status';
import {NGXLogger} from 'ngx-logger';
import {Parcel} from '../../common/parcel';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.css']
})
export class AdminPanelComponent implements OnInit {
  keys = Object.keys;
  statuses = ParcelStatus;
  parcel: Parcel = null;
  changeStatusEndedWithSuccess = false;
  changeStatusEndedWithError = false;
  changeStatusEndedWithErrorNoAccess = false;
  changeDeliveryAddressEndedWithError = false;
  changeDeliveryAddressEndedWithErrorNoAccess = false;
  isBadStatus = false;
  wasAddressChangeRequested = false;
  wasAddressChanged = false;
  isWrongAddressInput = false;

  constructor(private parcelService: ParcelService, private logger: NGXLogger) {
  }

  ngOnInit(): void {
  }

  changeStatus(parcelId: string, parcelStatus: string, accessCode: string): void {
    this.changeStatusEndedWithSuccess = false;
    this.changeStatusEndedWithError = false;

    this.parcelService.getParcelAccessStatusAdmin(parcelId, accessCode).subscribe(
      accessStatus => {
        this.logger.info('Received response: {}', accessStatus);

        if (accessStatus.access.toLocaleLowerCase() === 'denied') {
          this.changeStatusEndedWithErrorNoAccess = true;
        } else {
          this.parcelService.updateParcelStatus(parcelId, ParcelStatus[parcelStatus]).subscribe(
            response => {
              this.logger.info('Received response: {}', response);
              this.changeStatusEndedWithSuccess = true;
              this.changeStatusEndedWithError = false;
            },
            error => {
              this.logger.info('Cannot change parcel: {} status', parcelId);
              this.changeStatusEndedWithSuccess = false;
              this.changeStatusEndedWithError = true;
            }
          );
        }
      },
      error => {
        this.logger.info('No parcel of id: {}', parcelId);
      }
    );
  }

  changeDeliveryAddress(parcelId: string, accessCode: string, receiverCity: string, receiverPostCode: string, receiverStreet: string): void {
    this.changeDeliveryAddressEndedWithError = false;

    this.parcelService.getParcelAccessStatusAdmin(parcelId, accessCode).subscribe(
      accessStatus => {
        this.logger.info('Received response: {}', accessStatus);

        if (accessStatus.access.toLocaleLowerCase() === 'denied') {
          this.changeDeliveryAddressEndedWithErrorNoAccess = true;
        } else {
          this.parcelService.getParcel(parcelId).subscribe(
            parcel => {
              this.parcel = parcel;
              if (this.parcel.status !== ParcelStatus.ID_ADDED) {
                this.isBadStatus = true;
              } else {
                if (receiverCity.length === 0 || receiverPostCode.length === 0 || receiverPostCode.length !== 6 || receiverStreet.length === 0) {
                  this.wasAddressChangeRequested = false;
                  this.wasAddressChanged = false;
                  this.isWrongAddressInput = true;
                } else {
                  this.isWrongAddressInput = false;

                  this.logger.info('Changing parcel: {} address to {}', parcelId, receiverCity, receiverPostCode, receiverStreet);

                  this.parcelService.changeDeliveryAddress(parcelId, receiverCity, receiverPostCode, receiverStreet).subscribe(
                    result => {
                      this.wasAddressChangeRequested = true;
                      this.wasAddressChanged = true;
                      this.changeDeliveryAddressEndedWithError = false;

                      this.logger.info('Change parcel {} address to {}, from:  {}', parcelId, receiverCity, receiverPostCode, receiverStreet);

                      this.parcel.receiverCity = result.receiverCity;
                      this.parcel.receiverPostCode = result.receiverPostCode;
                      this.parcel.receiverStreet = result.receiverStreet;
                    },
                    error => {
                      this.wasAddressChangeRequested = true;
                      this.changeDeliveryAddressEndedWithError = true;

                      this.logger.info('Cannot change parcel {} address to: {}', parcelId, receiverCity, receiverPostCode, receiverStreet);
                    }
                  );
                }
              }
            }
          );
        }
      },
      error => {
        this.logger.info('No parcel of id: {}', parcelId);
        this.changeDeliveryAddressEndedWithError = true;
      }
    );
  }

  clearResults(): void {
    this.changeStatusEndedWithSuccess = false;
    this.changeStatusEndedWithError = false;
    this.changeStatusEndedWithErrorNoAccess = false;
    this.changeDeliveryAddressEndedWithError = false;
    this.changeDeliveryAddressEndedWithErrorNoAccess = false;
    this.wasAddressChangeRequested = false;
    this.isWrongAddressInput = false;
  }
}
