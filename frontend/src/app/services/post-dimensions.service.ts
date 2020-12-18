import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Parcel} from '../common/parcel';
import {HttpClient} from '@angular/common/http';
import {NGXLogger} from 'ngx-logger';
import {ParcelSize} from '../common/parcel-size';
import {DummyParcelDimensions} from '../common/dummy-parcel-dimensions';

@Injectable({
  providedIn: 'root'
})
// w parcelservice zobaczyć i analogicznie na POST
export class PostDimensionsService {
  private PARCELS_URL = 'http://localhost:8080/parcelservice/v1';
  private dims = '/dimensions';
  private postlength;
  private postwidth;
  private postdepth;
  constructor(private httpClient: HttpClient, private logger: NGXLogger) {
  }
  getDimensions(htmllength: number, htmlwidth: number, htmldepth: number) {
    this.postlength = htmllength;
    this.postwidth = htmlwidth;
    this.postdepth = htmldepth;
  }
  postDimensions(dummy: DummyParcelDimensions): Observable<Object> {
    const parcelURL = this.PARCELS_URL + this.dims;
    DummyParcelDimensions.dummylength = this.postlength;
    DummyParcelDimensions.dummywidth = this.postwidth;
    DummyParcelDimensions.dummydepth = this.postdepth;
    DummyParcelDimensions
    this.logger.info('Chyba zatrybiło?');
    return this.httpClient.post<Object>(parcelURL, dummy);
  }
}
