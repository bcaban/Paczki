import {ParcelAccess} from './parcel-access';

export class Parcel {
  name: string;
  parcelId: string;
  status: string;
  senderCity: string;
  senderPostCode: string;
  senderStreet: string;
  receiverCity: string;
  receiverPostCode: string;
  receiverStreet: string;
  weightInKg: number;
  height: number;
  length: number;
  width: number;
  size: string;
  timeToDeliver: string;
  parcelAccess: ParcelAccess;
  constructor(senderCity: string, senderPostCode: string, senderStreet: string, receiverCity: string, receiverPostCode: string, receiverStreet: string, weightInKg: number, length: number, width: number, height: number) {
    this.senderCity = senderCity;
    this.senderPostCode = senderPostCode;
    this.senderStreet = senderStreet;
    this.receiverCity = receiverCity;
    this.receiverPostCode = receiverPostCode;
    this.receiverStreet = receiverStreet;
    this.weightInKg = weightInKg;
    this.height = height;
    this.length = length;
    this.width = width;
  }
}
