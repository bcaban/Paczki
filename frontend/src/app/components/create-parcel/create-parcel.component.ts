import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {NGXLogger} from 'ngx-logger';
import {Parcel} from '../../common/parcel';
import {ParcelService} from '../../services/parcel.service';

@Component({
  selector: 'app-create-parcel',
  templateUrl: './create-parcel.component.html',
  styleUrls: ['./create-parcel.component.css']
})
export class CreateParcelComponent implements OnInit {
  response: string;
  constructor(private router: Router, private parcelService: ParcelService, private logger: NGXLogger) {
  }

  ngOnInit(): void {
  }
  postParcel(senderCity: string, senderPostCode: string, senderStreet: string, receiverCity: string, receiverPostCode: string, receiverStreet: string, weightInKg: number, length: number, width: number, height: number): void {
    const parcel = new Parcel(senderCity, senderPostCode, senderStreet, receiverCity, receiverPostCode, receiverStreet, weightInKg, length, width, height);
    this.parcelService.createParcel(parcel).subscribe(
      response => {
        if (response.receiverCity === ''){
          this.response = 'Nie można nadać paczki.';
        }
        else {
          this.response = 'Paczka została nadana.';
        }
        this.logger.info('Received response: {}', response);
      },
      error => {
        this.logger.info('Cannot create parcel', error);
      }
    );
  }
}

