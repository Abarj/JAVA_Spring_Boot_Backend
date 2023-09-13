import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Bill } from '../models/bill';
import { Product } from '../models/product';

@Injectable({
  providedIn: 'root'
})
export class BillService {
  private urlEndPoint: string = 'http://localhost:8080/api/bills';

  constructor(private http: HttpClient) { }

  getBill(id: number): Observable<Bill> {
    return this.http.get<Bill>(`${this.urlEndPoint}/${id}`);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.urlEndPoint}/${id}`);
  }

  filterProducts(term: string): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.urlEndPoint}/filter-products/${term}`);
  }

  create(bill: Bill): Observable<Bill> {
    return this.http.post<Bill>(this.urlEndPoint, bill);
  }
}
