import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {NGXLogger} from 'ngx-logger';
import {Parcel} from '../../common/parcel';
import {ParcelService} from '../../services/parcel.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-create-parcel',
  templateUrl: './create-parcel.component.html',
  styleUrls: ['./create-parcel.component.css']
})
export class CreateParcelComponent implements OnInit {
  response: string;
  registerForm: FormGroup;
  submitted = false;
  constructor(private formBuilder: FormBuilder, private router: Router, private parcelService: ParcelService, private logger: NGXLogger) {
  }

  // tslint:disable-next-line:typedef
  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      senderCity: ['', [Validators.required, Validators.minLength(3)]],
      senderPostCode: ['', [Validators.required, Validators.pattern('[0-9-]{6}')]],
      senderStreet: ['', [Validators.required, Validators.minLength(3)]],
      receiverCity : ['', [Validators.required, Validators.minLength(3)]],
      receiverPostCode: ['', [Validators.required, Validators.pattern('[0-9-]{6}')]],
      receiverStreet: ['', [Validators.required, Validators.minLength(3)]],
      weightInKg: ['', [Validators.required, Validators.min(1)]],
      length: ['', [Validators.required, Validators.min(1)]],
      width: ['', [Validators.required, Validators.min(1)]],
      height: ['', [Validators.required, Validators.min(1)]],
    });
  }

  postParcel(senderCity: string, senderPostCode: string, senderStreet: string, receiverCity: string, receiverPostCode: string, receiverStreet: string, weightInKg: number, length: number, width: number, height: number): void {
      const parcel = new Parcel(senderCity, senderPostCode, senderStreet, receiverCity, receiverPostCode, receiverStreet, weightInKg, length, width, height);
      this.parcelService.createParcel(parcel).subscribe(
        response => {
          if (response.receiverCity === '') {
            this.response = 'Nie można nadać paczki.';
          } else {
            this.response = 'Paczka została nadana.';
          }
          this.logger.info('Received response: {}', response);
        },
        error => {
          this.logger.info('Cannot create parcel', error);
          this.response = 'error';
        }
      );
  }
  // tslint:disable-next-line:typedef
  get f() { return this.registerForm.controls; }

  // tslint:disable-next-line:typedef
  onSubmit() {
    this.submitted = true;
    if (this.registerForm.invalid) {
      this.response = 'Wypełnij wszystkie pola by nadać paczkę';
      return;
    }
    if (!this.registerForm.invalid) {
      this.postParcel(this.registerForm.value.senderCity, this.registerForm.value.senderPostCode, this.registerForm.value.senderStreet, this.registerForm.value.receiverCity, this.registerForm.value.receiverPostCode, this.registerForm.value.receiverStreet, this.registerForm.value.weightInKg, this.registerForm.value.length, this.registerForm.value.width, this.registerForm.value.height);
      alert('Paczka została nadana.');
      return;
    }
  }
}

