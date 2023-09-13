import { Injectable } from '@angular/core';
import { formatDate, DatePipe } from '@angular/common';
import { Client } from './client';
import { of, Observable, throwError } from 'rxjs';
import { HttpClient, HttpClientModule, HttpEvent, HttpHeaders, HttpRequest } from '@angular/common/http';
import { map, catchError, tap } from 'rxjs/operators';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';
import { Region } from './region';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private urlEndpoint: string = 'http://localhost:8080/api/clients'

  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})

  constructor(private http: HttpClient, private router: Router) { }

  getRegions(): Observable<Region[]> {
    return this.http.get<Region[]>(this.urlEndpoint + '/regions');
  }

  getClients(page: number): Observable<any> {
    return this.http.get(this.urlEndpoint + '/page/' + page).pipe(
      tap((response: any) => {
        console.log('ClientService: tap 1');
        (response.content as Client[]).forEach( client => {
          console.log(client.name)
        })
      }),
      map((response: any) => {
        (response.content as Client[]).map(client => {
          client.name = client.name.toUpperCase();

          let datePipe = new DatePipe('en-US');
          // client.createAt = datePipe.transform(client.createAt, 'EEEE dd, MMMM yyyy');

          return client;
        })
        return response;
      }),
      tap(response => {
        console.log('ClientService: tap 2');
        (response.content as Client[]).forEach( client => {
          console.log(client.name)
        });
      })
    );
  }

  create(client: Client): Observable<any> {
    return this.http.post<any>(this.urlEndpoint, client, {headers: this.httpHeaders}).pipe(
      catchError(e => {
        if (e.status == 400) {
          return throwError( () => e );
        }

        console.error(e.error.message)
        Swal.fire(e.error.message, e.error.error, 'error');
          return throwError( () => e );
      })
    )
  }

  getClient(id): Observable<Client> {
    return this.http.get<Client>(`${this.urlEndpoint}/${id}`).pipe(
      catchError(e => {
        this.router.navigate(['/clients']);
        console.error(e.error.message)
        Swal.fire('Error updating record', e.error.message, 'error');
          return throwError( () => e );;
      })
    )
  }

  update(client: Client): Observable<any> {
    return this.http.put<any>(`${this.urlEndpoint}/${client.id}`, client, {headers: this.httpHeaders}).pipe(
      catchError(e => {
        if (e.status == 400) {
          return throwError( () => e );
        }

        console.error(e.error.message)
        Swal.fire(e.error.message, e.error.error, 'error');
          return throwError( () => e );;
      })
    )
  }

  delete(id: number): Observable<Client> {
    return this.http.delete<Client>(`${this.urlEndpoint}/${id}`, {headers: this.httpHeaders}).pipe(
      catchError(e => {
        console.error(e.error.message)
        Swal.fire(e.error.message, e.error.error, 'error');
          return throwError( () => e );;
      })
    )
  }

  uploadAvatar(file: File, id): Observable<HttpEvent<{}>> {
    let formData = new FormData();

    formData.append("file", file);
    formData.append("id", id);

    const req = new HttpRequest('POST', `${this.urlEndpoint}/upload`, formData, {
      reportProgress: true
    });

    return this.http.request(req);
  }
}
