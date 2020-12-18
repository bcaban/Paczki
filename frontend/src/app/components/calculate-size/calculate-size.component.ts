import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {CalculateSize} from '../../services/size.service';
import {PostDimensionsService} from '../../services/post-dimensions.service';
import {DummyParcelDimensions} from '../../common/dummy-parcel-dimensions';
import {NGXLogger} from 'ngx-logger';

@Component({
  selector: 'app-calculate-size',
  templateUrl: './calculate-size.component.html',
  styleUrls: ['./calculate-size.component.css']
})
export class CalculateSizeComponent implements OnInit {
  dim: string;
  constructor(private router: Router, private postdim: PostDimensionsService, private logger: NGXLogger) {
  }

  ngOnInit(): void {
  }

  calcsml(length: number, width: number, depth: number): void {
    const dummyParcelDimensions = new DummyParcelDimensions();
    dummyParcelDimensions.dummylength = length;
    dummyParcelDimensions.dummywidth = width;
    dummyParcelDimensions.dummydepth = depth;
    this.postdim.postDimensions(dummyParcelDimensions).subscribe(
      response => {
        this.logger.info('Received response: {}', response);
      },
      error => {
        this.logger.info('Cannot send dimensions');
      }
    );
  }
}
