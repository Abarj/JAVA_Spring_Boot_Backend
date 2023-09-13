import { Component } from '@angular/core';
import { BillService } from './services/bill.service';
import { Bill } from './models/bill';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-bill-details',
  templateUrl: './bill-details.component.html'
})
export class BillDetailsComponent {
  bill: Bill;
  title: string = 'Bill'

  constructor(private billService: BillService, private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      let id =+ params.get('id');
      this.billService.getBill(id).subscribe(bill => this.bill = bill);
    });
  }
}
