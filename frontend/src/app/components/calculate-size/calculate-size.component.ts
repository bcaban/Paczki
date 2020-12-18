import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-calculate-size',
  templateUrl: './calculate-size.component.html',
  styleUrls: ['./calculate-size.component.css']
})
export class CalculateSizeComponent implements OnInit {
  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }
}
