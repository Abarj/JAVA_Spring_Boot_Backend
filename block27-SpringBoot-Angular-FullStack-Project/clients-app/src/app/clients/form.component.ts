import { Component, OnInit } from '@angular/core';
import { Client } from './client'
import { ClientService } from './client.service';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import Swal from 'sweetalert2';
import { Observable } from 'rxjs';
import { Region } from './region';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html'
})
export class FormComponent implements OnInit {
  public client: Client = new Client();
  public regions: Region[];
  public title: string = "Add new client";
  public errors: string[];

  constructor(private clientService: ClientService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {}
 
  ngOnInit() { 
    this.loadClient();
    this.clientService.getRegions().subscribe(regions => {
      this.regions = regions;
    })
  }

  public loadClient(): void {
    this.activatedRoute.params.subscribe(params => {
      let id = params['id']
      if (id) {
        this.clientService.getClient(id).subscribe((client) => this.client = client)
      }
    })
  }

  public create(): void {
    console.log(this.client);
    this.clientService.create(this.client).subscribe(
      json => {
        this.router.navigate(['/clients'])
        Swal.fire('Client ', `${json.message}: ${json.client.name} ${json.client.lastName}`, 'success')
      },
      err => {
        this.errors = err.error.errors as string[]
        console.error('Error code: ' + err.status);
        console.error(err.error.errors);
      }
    )
  }

  public update(): void {
    console.log(this.client);
    
    this.client.bills = null;

    this.clientService.update(this.client).subscribe(
      json => {
        this.router.navigate(['/clients'])
        Swal.fire('Client ', `${json.message}: ${json.client.name} ${json.client.lastName}`, 'success')
      },
      err => {
        this.errors = err.error.errors as string[]
        console.error('Error code: ' + err.status);
        console.error(err.error.errors);
      }
    )
  }

  compareRegion(o1: Region, o2: Region): boolean {
    if(o1 === undefined && o2 === undefined) {
      return true;
    }
    
    return o1 == null || o2 == null || o1 == undefined || o2 == undefined? false: o1.id === o2.id;
  }
}
