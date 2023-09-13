import { Component } from '@angular/core';
import { Bill } from './models/bill';
import { Product } from './models/product';
import { ClientService } from '../clients/client.service';
import { ActivatedRoute, Router } from '@angular/router';
import {FormControl, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {Observable} from 'rxjs';
import { map, mergeMap, startWith} from 'rxjs/operators';
import { BillService } from './services/bill.service';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { ItemsBill } from './models/items-bill';
import Swal from 'sweetalert2';



@Component({
  selector: 'app-bills',
  templateUrl: './bills.component.html'
})
export class BillsComponent {
title: string = 'New invoice'
bill: Bill = new Bill();
myControl = new FormControl('');
products: string[] = ['Mesa', 'Sony', 'Tablet'];
filteredProducts: Observable<Product[]>;

constructor(private clientService: ClientService, private activatedRoute: ActivatedRoute, private billService: BillService, private router: Router) {}

ngOnInit() {
  this.activatedRoute.paramMap.subscribe(params => {
    let clientId =+ params.get('clientId');
    this.clientService.getClient(clientId).subscribe(client => this.bill.client = client);
  })

  this.filteredProducts = this.myControl.valueChanges.pipe(
    map(value => (typeof value === 'string' ? value : (value as Product).name)),
    mergeMap(value => value? this._filter(value || ''): [])
  );
}

private _filter(value: string): Observable<Product[]> {
  const filterValue = value.toLowerCase();

  return this.billService.filterProducts(filterValue);
}

showName(product?: Product): string | undefined {
  return product? product.name: undefined;
}

selectProduct(event: MatAutocompleteSelectedEvent): void {
  let product = event.option.value as Product;
  console.log(product);

  if(this.itemExists(product.id)) {
    this.increasesQuantity(product.id)
  } else {
    let newItem = new ItemsBill();
    newItem.product = product;
    this.bill.items.push(newItem);
  }

  this.myControl.setValue('');
  event.option.focus();
  event.option.deselect();
}

updateAmount(id: number, event: any): void {
  let amount: number = event.target.value as number;

  if(amount == 0) {
    return this.deleteBillItem(id);
  }

  this.bill.items = this.bill.items.map((item: ItemsBill) => {
    if(id === item.product.id) {
      item.amount = amount
    }

    return item;
  })
}

itemExists(id: number): boolean {
  let exists = false;

  this.bill.items.forEach((item: ItemsBill) => {
    if(id === item.product.id) {
      exists = true;
    }
  });
  
  return exists;
}

increasesQuantity(id: number): void {
  this.bill.items = this.bill.items.map((item: ItemsBill) => {
    if(id === item.product.id) {
      ++item.amount;
    }

    return item;
  });
}

deleteBillItem(id: number): void {
  this.bill.items = this.bill.items.filter((item: ItemsBill) => id != item.product.id);
}

create(billForm): void {
  console.log(this.bill);

  if(this.bill.items.length == 0) {
    this.myControl.setErrors({'invalid': true});
  }

  if(billForm.form.valid && this.bill.items.length > 0) {
    this.billService.create(this.bill).subscribe(bill => {
      Swal.fire(this.title, `Invoice ${bill.description} created successfully!`, 'success');
      this.router.navigate(['/clients']);
    })
  }
}
}
