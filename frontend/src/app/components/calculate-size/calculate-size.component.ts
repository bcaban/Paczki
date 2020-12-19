import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {PostDimensionsService} from '../../services/post-dimensions.service';
import {DummyParcelDimensions} from '../../common/dummy-parcel-dimensions';
import {NGXLogger} from 'ngx-logger';

@Component({
  selector: 'app-calculate-size',
  templateUrl: './calculate-size.component.html',
  styleUrls: ['./calculate-size.component.css']
})
export class CalculateSizeComponent implements OnInit {
  click: string;
  was_sent: string;
  size: string;
  constructor(private router: Router, private postDimensionsService: PostDimensionsService, private logger: NGXLogger) {
  }

  ngOnInit(): void {
  }

  calcsml(length: number, width: number, depth: number): void {
    const dummyParcelDimensions = new DummyParcelDimensions();
    dummyParcelDimensions.dummylength = length;
    dummyParcelDimensions.dummywidth = width;
    dummyParcelDimensions.dummydepth = depth;
    this.click = 'clicked';
    this.postDimensionsService.postDimensions(dummyParcelDimensions).subscribe(
      response => {
        this.was_sent = 'yep, dummy sent';
        this.size = response.parcelSize;
        this.logger.info('Received response: {}', response);
      },
      error => {
        this.logger.info('Cannot send dimensions', error);
      }
    );
  }
}
