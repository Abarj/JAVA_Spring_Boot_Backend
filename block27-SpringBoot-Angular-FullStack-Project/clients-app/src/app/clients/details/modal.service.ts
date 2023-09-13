import { EventEmitter, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ModalService {
  modal: boolean = false;
  private _uploadNotification = new EventEmitter<any>();
  
  constructor() { }

  get uploadNotification(): EventEmitter<any> {
    return this._uploadNotification;
  }

  openModal() {
    this.modal = true;
  }

  closeModal() {
    this.modal = false;
  }
}
