import {Component, OnInit} from '@angular/core';
import {NGXLogger} from 'ngx-logger';
import {ParcelService} from '../../services/parcel.service';
import {Parcel} from '../../common/parcel';
import {ParcelAccessCode} from '../../common/parcel-access-code';

@Component({
  selector: 'app-update-access',
  templateUrl: './update-access.component.html',
  styleUrls: ['./update-access.component.css']
})
export class UpdateAccessComponent implements OnInit {
  clientAccessCode: ParcelAccessCode = null;
  updateParcelClientCodeEndedWithError = false;
  updateParcelClientCodeEndedWithSuccess = false;

  constructor(private parcelService: ParcelService, private logger: NGXLogger) {
  }

  ngOnInit(): void {
  }

  updateParcelClientCode(parcelId: string): void {
    this.updateParcelClientCodeEndedWithError = false;
    this.updateParcelClientCodeEndedWithSuccess = false;

    this.parcelService.updateParcelClientCode(parcelId).subscribe(
      result => {
        this.clientAccessCode = result;
        this.updateParcelClientCodeEndedWithSuccess = true;
        this.updateParcelClientCodeEndedWithError = false;
      },
      error => {
        this.updateParcelClientCodeEndedWithError = true;
        this.updateParcelClientCodeEndedWithSuccess = false;
        this.logger.info('No parcel of id: {}', parcelId);
      }
    );
  }

  clearResults(): void {
    this.updateParcelClientCodeEndedWithError = false;
    this.updateParcelClientCodeEndedWithSuccess = false;
  }
}
