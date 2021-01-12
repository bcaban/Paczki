import {Component, OnInit} from '@angular/core';
import {ParcelService} from '../../services/parcel.service';
import {ParcelStatus} from '../../common/parcel-status';
import {NGXLogger} from 'ngx-logger';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.css']
})
export class AdminPanelComponent implements OnInit {
  keys = Object.keys;
  statuses = ParcelStatus;
  changeStatusEndedWithSuccess = false;
  changeStatusEndedWithError = false;
  changeStatusEndedWithErrorNoAccess = false;

  constructor(private parcelService: ParcelService, private logger: NGXLogger) {
  }

  ngOnInit(): void {
  }

  changeStatus(parcelId: string, parcelStatus: string, accessCode: string): void {
    this.changeStatusEndedWithSuccess = false;
    this.changeStatusEndedWithError = false;

    this.parcelService.getParcelAccessStatusClient(parcelId, accessCode).subscribe(
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

  clearResults(): void {
    this.changeStatusEndedWithSuccess = false;
    this.changeStatusEndedWithError = false;
    this.changeStatusEndedWithErrorNoAccess = false;
  }
}
