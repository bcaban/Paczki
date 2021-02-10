import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {ParcelService} from '../../services/parcel.service';
import {NGXLogger} from 'ngx-logger';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  wasAccessChecked = false;
  canAccessParcel = false;
  accessDenied = false;
  wrongParcelId = false;

  constructor(private router: Router, private parcelService: ParcelService, private logger: NGXLogger) {
  }

  ngOnInit(): void {
  }

  search(parcelId: string, clientAccessCode: string): void {
    this.parcelService.getParcelAccessStatusClient(parcelId, clientAccessCode).subscribe(
      parcelAccess => {
        this.logger.info('Received parcel access: {}', parcelAccess);

        if (parcelAccess.access.toLocaleLowerCase() === 'denied') {
          this.accessDenied = true;
        } else {
          this.router.navigateByUrl(`/search/${parcelId}`);
        }
      },
      error => {
        this.logger.info('Cannot find parcel access for: {}', parcelId);
        this.wrongParcelId = true;
      }
    );
  }

  searchByName(parcelName: string, clientAccessCode: string): void {
    this.parcelService.getParcelByNameAndClientAccessCode(parcelName, clientAccessCode).subscribe(
      parcel => {
        this.logger.info('Received parcel: {}', parcel);
        this.router.navigateByUrl(`/search/${parcel.parcelId}`);
      },
      error => {
        this.logger.info('Cannot find parcel for');

        this.wasAccessChecked = true;
        this.canAccessParcel = false;
      }
    );
  }

  clearResults(): void {
    this.wasAccessChecked = false;
    this.canAccessParcel = false;
    this.accessDenied = false;
    this.wrongParcelId = false;
  }
}
