import { Injectable } from '@angular/core';
import { Session } from '../session';
import { Observable } from 'rxjs';
import Swal from 'sweetalert2';
import { of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SessionService {
    private urlEndPoint:string ='http://localhost:8080/session'
    private httpHeaders = new HttpHeaders({'Content-Type':'application/json'})

    constructor(private http: HttpClient) { }

    getSessions(): Observable<Session[]>{
        return this.http.get<Session[]>('http://localhost:8080/session/all');
    }

    create(session: Session):Observable<Session>{
        return this.http.post<Session>(this.urlEndPoint, session)
    }

    getSession(id: number): Observable<Session>{
        return this.http.get<Session>(`${this.urlEndPoint}/${id}`)
    }

    delete(id: number):Observable<Session>{
        return this.http.delete<Session>(`${this.urlEndPoint}/${id}`)
    }
}