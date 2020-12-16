import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {ParcelService} from '../../services/parcel.service';
import {Parcel} from '../../common/parcel';
import {NGXLogger} from 'ngx-logger';

@Component({
  selector: 'app-parcel',
  templateUrl: './parcel.component.html',
  styleUrls: ['./parcel.component.css']
})
export class ParcelComponent implements OnInit {
  parcelId: string = 'not_found';
  parcel: Parcel = null;
  wasParcelSearched = false;
  wasParcelFound = false;

  constructor(private route: ActivatedRoute, private parcelService: ParcelService, private logger: NGXLogger) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      if (this.route.snapshot.paramMap.has('parcelId')) {
        this.getParcel();
      }
    });
  }

  private getParcel(): void {
    this.parcelId = this.route.snapshot.paramMap.get('parcelId');

    this.logger.info('Getting parcel: {}', this.parcelId);

    this.parcelService.getParcel(this.parcelId).subscribe(
      parcel => {
        this.wasParcelSearched = true;
        this.wasParcelFound = true;

        this.logger.info('Received parcel: {}', parcel);

        this.parcel = parcel;
      },
      error => {
        this.wasParcelSearched = true;
        this.wasParcelFound = false;

        this.logger.info('Cannot find parcel: {}', this.parcelId);
      }
    );
  }
}
