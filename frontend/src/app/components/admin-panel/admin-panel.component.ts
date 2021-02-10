import {Component, OnInit} from '@angular/core';
import {ParcelService} from '../../services/parcel.service';
import {ParcelStatus} from '../../common/parcel-status';
import {NGXLogger} from 'ngx-logger';
import {Parcel} from '../../common/parcel';
import {Router} from "@angular/router";
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';

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
  getDeliveryAddressEndedWithErrorNoAccess = false;
  getDeliveryAddressEndedWithError = false;
  getDeliveryAddressWithSuccess = false;

  isNameLengthTooShort = false;
  wasParcelNameChanged = false;
  selectedDate = new Date();
  wasParcelDateOfDeliverChanged = false;
  wasParcelDateOfDeliverChangeRequested = false;
  daysToDeliver = 0;
  expectedParcelDeliveryDate: Date = new Date();

  constructor(private router: Router, private parcelService: ParcelService, private logger: NGXLogger, private modalService: NgbModal) {
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

  getDeliveryAddress(parcelId: string, accessCode: string): void {
    this.getDeliveryAddressWithSuccess = false;
    this.getDeliveryAddressEndedWithError = false;
    this.parcelService.getParcelAccessStatusAdmin(parcelId, accessCode).subscribe(
      accessStatus => {
        this.logger.info('Received response: {}', accessStatus);

        if (accessStatus.access.toLocaleLowerCase() === 'denied') {
          this.getDeliveryAddressEndedWithErrorNoAccess = true;
        } else {
          const newRelativeUrl = this.router.createUrlTree(['/search/' + parcelId]);
          const baseUrl = window.location.href.replace(this.router.url, '');
          window.open(baseUrl + newRelativeUrl, '_blank');

          this.getDeliveryAddressWithSuccess = true;
        }
      },
      error => {
        this.logger.info('No parcel of id: {}', parcelId);
        this.getDeliveryAddressEndedWithError = true;
        this.getDeliveryAddressWithSuccess = false;
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
  open(content, parcelId, accessCode): void {
    this.wasParcelDateOfDeliverChanged = false;
    this.wasParcelDateOfDeliverChangeRequested = false;
    this.parcelService.getParcelAccessStatusAdmin(parcelId, accessCode).subscribe(
      accessStatus => {
        this.logger.info('Received response: {}', accessStatus);

        if (accessStatus.access.toLocaleLowerCase() === 'denied') {
          this.changeDeliveryAddressEndedWithErrorNoAccess = true;
        } else {
          this.modalService.open(content,
            {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
            if (result === 'DATE_PICKED') {
              const diff = Math.abs(this.selectedDate.getTime() - new Date().getTime());
              const diffDays = Math.ceil(diff / (1000 * 3600 * 24));

              if (this.selectedDate < new Date()) {
                this.wasParcelDateOfDeliverChangeRequested = true;
                return;
              }

              if (diffDays < 0 || diffDays > 14) {
                this.wasParcelDateOfDeliverChangeRequested = true;
                return;
              }

              this.daysToDeliver = diffDays;

              const today = new Date();
              today.setDate(new Date().getDate() + diffDays);
              this.expectedParcelDeliveryDate = today;

              this.parcelService.changeTimeToDeliver(parcelId, diffDays).subscribe(
                updated => {
                  this.wasParcelDateOfDeliverChanged = true;
                  this.wasParcelDateOfDeliverChangeRequested = true;
                  this.logger.info('Changed parcel {} time to deliver to {}', this.parcel, diffDays);

                  this.parcel.timeToDeliver = result.timeToDeliver;
                },
                error => {
                  this.wasParcelDateOfDeliverChangeRequested = true;
                  this.logger.info('Cannot change parcel {} time to deliver to: {}', parcelId, diffDays);
                }
              );
            } else {
              this.selectedDate = new Date();
            }
          }, (reason) => {
            this.selectedDate = new Date();
          });
        }
      }
    );
}
}
