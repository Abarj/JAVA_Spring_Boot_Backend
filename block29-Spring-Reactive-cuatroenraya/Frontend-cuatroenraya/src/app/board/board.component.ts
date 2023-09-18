import { Component, Input } from '@angular/core';
import { Subscription } from 'rxjs';
import {  getNgModuleById } from '@angular/core';
import { PieceService } from '../piece.service';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent {
  columns = [0, 1, 2, 3, 4, 5, 6]
  idBs!: number;
  username!: string;
  subscription!: Subscription;
  movement: number = 0;
  exit: string = '/assets/backicon.png'

  constructor(private data: PieceService) {}

  ngOnInit(): void {
    this.subscription = this.data.currentId.subscribe(idBs => this.idBs = idBs)
    this.subscription = this.data.currentUser.subscribe(username => this.username = username)
  }
}
