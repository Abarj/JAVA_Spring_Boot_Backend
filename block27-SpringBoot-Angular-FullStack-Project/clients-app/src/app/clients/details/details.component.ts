import { Component, OnInit, Input } from '@angular/core';
import { Client } from '../client';
import { ClientService } from '../client.service';
import { ModalService } from './modal.service';
import Swal from 'sweetalert2';
import { HttpEventType } from '@angular/common/http';
import { BillService } from 'src/app/bills/services/bill.service';
import { Bill } from 'src/app/bills/models/bill';

@Component({
  selector: 'client-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent {
  @Input() client: Client;
  title: string = "Client details";
  public selectedAvatar: File;
  public progress: number = 0;

  constructor(private clientService: ClientService,
              public modalService: ModalService,
              private billService: BillService) {}

  ngOnInit() {
  }

  selectAvatar(event) {
    this.selectedAvatar = event.target.files[0];
    this.progress = 0;
    console.log(this.selectedAvatar);

    if(this.selectedAvatar.type.indexOf('image') < 0) {
      Swal.fire('Error uploading', 'Image format is required', 'error');
      this.selectedAvatar = null;
    }
  }

  uploadAvatar() {
    if(!this.selectedAvatar) {
      Swal.fire('Error uploading', 'Image is required', 'error');
    } else {
      this.clientService.uploadAvatar(this.selectedAvatar, this.client.id).subscribe(event => {
        if(event.type === HttpEventType.UploadProgress) {
          this.progress = Math.round((event.loaded / event.total) * 100);
        } else if(event.type === HttpEventType.Response) {
          let response: any = event.body;
          this.client = response.client as Client;

          this.modalService.uploadNotification.emit(this.client);
          Swal.fire('Avatar uploaded successfully!', response.message, 'success');
        }
      });
    }
  }

  closeModal() {
    this.modalService.closeModal();
    this.selectedAvatar = null;
    this.progress = 0;
  }

  delete(bill: Bill): void {
    Swal.fire({
      title: 'Are you sure?',
      text: `Are you sure you want to delete the next bill?: ${bill.description}`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.isConfirmed) {
        this.billService.delete(bill.id).subscribe(
          response => {
            this.client.bills = this.client.bills.filter(b => b !== bill)
            Swal.fire(
              'Deleted!',
              `Bill ${bill.description} has been deleted successfully.`,
              'success'
            )
          }
        )
      }
    })
  }
}
