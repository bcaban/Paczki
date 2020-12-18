import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {CalculateSize} from '../../services/size.service';
import {PostDimensionsService} from '../../services/post-dimensions.service';

@Component({
  selector: 'app-calculate-size',
  templateUrl: './calculate-size.component.html',
  styleUrls: ['./calculate-size.component.css']
})
export class CalculateSizeComponent implements OnInit {
  dim: string;
  constructor(private router: Router, private postdim: PostDimensionsService) {
  }

  ngOnInit(): void {
  }

  calcsml(length: number, width: number, depth: number): void {
    this.postdim.getDimensions(length, width, depth).subscribe(
      response => {
        this.logger.info('Received response: {}', response);
        this.changeSizeEndedWithSuccess = true;
        this.changeSizeEndedWithError = false;
      },
      error => {
        this.logger.info('Cannot send dimensions');
        this.changeSizeEndedWithSuccess = false;
        this.changeSizeEndedWithError = true;
      }
    );
  }
}
