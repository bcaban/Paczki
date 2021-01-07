import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {CalculateParcelDimensionsService} from '../../services/calculate-parcel-dimensions.service';
import {ParcelDimensions} from '../../common/parcel-dimensions';
import {NGXLogger} from 'ngx-logger';

@Component({
  selector: 'app-create-parcel',
  templateUrl: './create-parcel.component.html',
  styleUrls: ['./create-parcel.component.css']
})
export class CreateParcelComponent implements OnInit {
  size: string;
  constructor(private router: Router, private postDimensionsService: CalculateParcelDimensionsService, private logger: NGXLogger) {
  }

  ngOnInit(): void {
  }
// tslint:disable-next-line:max-line-length
  postsml(receiverCity: string, receiverPostCode: string, receiverStreet: string, senderCity: string, senderPostCode: string, senderStreet: string, weightInKg: number, length: number, width: number, height: number): void {
    const dummyParcelDimensions = new ParcelDimensions(length, width, height);
    this.postDimensionsService.postDimensions(dummyParcelDimensions).subscribe(
      response => {
        if (response.parcelSize === 'NONE'){
          this.size = 'Podano nieprawidłowe dane';
        }
        else {
          this.size = response.parcelSize;
        }
        this.logger.info('Received response: {}', response);
      },
      error => {
        this.logger.info('Cannot send dimensions', error);
      }
    );
  }
}

