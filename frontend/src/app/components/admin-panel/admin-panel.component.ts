import {Component, OnInit} from '@angular/core';
import {ParcelService} from '../../services/parcel.service';
import {ParcelStatus} from '../../common/parcel-status';
import {NGXLogger} from 'ngx-logger';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.css']
})
export class AdminPanelComponent implements OnInit {

  constructor(private parcelService: ParcelService, private logger: NGXLogger) {
  }

  ngOnInit(): void {
  }

  changeStatus(parcelId: string, parcelStatus: ParcelStatus): void {
    this.parcelService.updateParcelStatus(parcelId, parcelStatus).subscribe(
      status => {
        this.logger.info('Received response: {}', status);
        // Todo
      },
      error => {
        this.logger.info('Cannot change parcel: {} status', parcelId);
        // Todo
      }
    );
  }
}
