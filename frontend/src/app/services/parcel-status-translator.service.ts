import {Injectable} from '@angular/core';
import {ParcelStatus} from '../common/parcel-status';

@Injectable({
  providedIn: 'root'
})
// FIXME CHANGE THIS JUNK TO ANGULAR I18N IN FUTURE
export class ParcelStatusTranslatorService {

  constructor() {
  }

  translateStatusToPolish(parcelStatus: ParcelStatus): string {
    switch (parcelStatus) {
      case ParcelStatus.ID_ADDED:
        return 'nadana';
      case ParcelStatus.IN_TRANSIT:
        return 'w transporcie';
      case ParcelStatus.OUT_FOR_DELIVERY:
        return 'wydana do doręczenia';
      case ParcelStatus.DELIVERED:
        return 'dostarczona';
      case ParcelStatus.MISSING_IN_ACTION:
        return 'zaginęła w akcji';
      case ParcelStatus.CANCELLED:
        return 'paczka anulowana';
    }

    return '???';
  }
}
