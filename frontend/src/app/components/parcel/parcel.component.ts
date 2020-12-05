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
  parcel: Parcel = new Parcel();

  constructor(private route: ActivatedRoute, private packageService: ParcelService, private logger: NGXLogger) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      if (this.route.snapshot.paramMap.has('parcelId')) {
        this.getParcel();
      } else {
        // TODO redirect to 404
      }
    });
  }

  private getParcel(): void {
    this.parcelId = this.route.snapshot.paramMap.get('parcelId');

    this.logger.info('Getting parcel: {}', this.parcelId);

    this.packageService.getParcel(this.parcelId).subscribe(
      data => {
        this.logger.info('Received parcel:  {}', data);

        this.parcel = data;

        this.logger.info('Mapped to parcel {}', this.parcel);
      }
    );
  }
}
