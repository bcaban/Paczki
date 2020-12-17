import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-calculate-size',
  templateUrl: './calculate-size.component.html',
  styleUrls: ['./calculate-size.component.css']
})
export class CalculateSizeComponent implements OnInit {
  dim: string = '';
  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }
  calcsml(length: number, width: number, depth: number): void {
    if (length < 0 || width < 0 || depth < 0) {
      this.dim = 'Wprwadzono nieprawidłowe wymiary paczki.';
    }
    if (length > 0 && width > 0 && depth > 0){
      if (length > 60 || width > 60 || depth > 60) {
        this.dim = 'L';
        }
      else if (length <= 30 && width <= 30 && depth <= 30){
        this.dim = 'S';
        }
      else {
        this.dim = 'M';
      }
    }
  }
}
