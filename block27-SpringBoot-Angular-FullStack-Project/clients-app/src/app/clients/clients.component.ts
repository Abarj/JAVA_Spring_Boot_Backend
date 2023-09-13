import { Component } from '@angular/core';
import { Client } from './client';
import { ClientService } from './client.service';
import { ModalService } from './details/modal.service';
import Swal from 'sweetalert2';
import { tap } from 'rxjs/operators';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html'
})
export class ClientsComponent {
  clients: Client[];
  paginator: any;
  selectedClient: Client;

  constructor(private clientsService: ClientService,
              private activatedRoute: ActivatedRoute,
              private modalService: ModalService) {}
  
  ngOnInit() {

    this.activatedRoute.paramMap.subscribe( params => {
      let page: number = +params.get('page');

      if (!page) {
        page = 0;
      }
      
      this.clientsService.getClients(page).pipe(
        tap(response => {
          console.log('ClientComponent: tap 3');
          (response.content as Client[]).forEach( client => {
            console.log(client.name)
          });
        })
      ).subscribe(response => {
        this.clients = response.content as Client[];
        this.paginator = response;
      });
    });

    this.modalService.uploadNotification.subscribe( client => {
      this.clients = this.clients.map(originalClient => {
        if(client.id == originalClient.id) {
          originalClient.avatar = client.avatar;
        }
        return originalClient;
      })
    })
  }

  delete(client: Client): void {
    Swal.fire({
      title: 'Are you sure?',
      text: `Are you sure you want to delete the next client?: ${client.name} ${client.lastName}`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.isConfirmed) {
        this.clientsService.delete(client.id).subscribe(
          response => {
            this.clients = this.clients.filter(cli => cli !== client)
            Swal.fire(
              'Deleted!',
              `Client ${client.name} ${client.lastName} has been deleted successfully.`,
              'success'
            )
          }
        )
      }
    })
  }

  openModal(client: Client) {
    this.selectedClient = client;
    this.modalService.openModal();
  }
}
