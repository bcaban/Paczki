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

  constructor(private router: Router, private parcelService: ParcelService, private logger: NGXLogger) {
  }

  ngOnInit(): void {
  }

  search(parcelId: string, clientAccessCode: string): void {
    this.parcelService.getParcelAccessStatusClient(parcelId, clientAccessCode).subscribe(
      parcelAccess => {
        this.logger.info('Received parcel access: {}', parcelAccess);

        this.wasAccessChecked = true;

        if (parcelAccess.access.toLocaleLowerCase() === 'denied') {
          this.canAccessParcel = false;
        } else {
          this.router.navigateByUrl(`/search/${parcelId}`);
        }
      },
      error => {
        this.logger.info('Cannot find parcel access for: {}', parcelId);
        this.router.navigateByUrl(`/search/${parcelId}`);
      }
    );
  }

}
