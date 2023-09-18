import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable()
export class PieceService {

  public idBs = new BehaviorSubject(0);
  public user = new BehaviorSubject("default")
  
  currentId = this.idBs.asObservable();
  currentUser = this.user.asObservable();
  
  constructor() { }

  changeId(id: number){
    this.idBs.next(id)
  }
  changeUser(user: string){
    this.user.next(user)
  }
}