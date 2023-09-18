import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Client } from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import Swal from 'sweetalert2';

import { Session } from '../session'
import { PieceService } from '../piece.service';
import { Metadata } from '../metadata';

@Component({
  selector: 'app-column',
  templateUrl: './column.component.html',
  styleUrls: ['./column.component.css']
})

export class ColumnComponent {
  public  client!: Client;
  public username!: string;
  public round: number = 1;
  public pieces: number = 0;
  subscription!: Subscription;

  @Input() sessionId!: number;
  @Input() movement!: number;

  constructor(private _router: Router, private data: PieceService) {

  }

  ngOnInit(): void {
    this.subscription = this.data.currentUser.subscribe(username => this.username = username)
    this.client = new Client();
    this.client.webSocketFactory = () => {
      return new SockJS("http://localhost:8080/session-websocket");
    }
    this.client.activate();
    this.client.onConnect = (frame) => {
      this.client.subscribe('/board/movement', e => {
          let s: Session = JSON.parse(e.body) as Session;
          
          if(s.id != null) {
            if(s.id == this.sessionId) {
              if(s.board[0][this.movement] != "0") {
                this.pieces = 6
              }

              for(let i = 0; i < 6; i++) {
                if(s.board[i][this.movement] == "0") {
                  this.cells[i].bg = 'black'
                }
                
                if(s.board[i][this.movement] == s.playerOne) {
                  this.cells[i].bg = 'yellow'
                }

                if(s.board[i][this.movement] == s.playerTwo) {
                  this.cells[i].bg = 'red'
                }
              }
          
              this.round++;

              if(s.status == "WINNER_ONE"){
                Swal.fire('Match ended', `The winner is `+s.playerOne.toString(), 'success',).then(result =>this._router.navigate(["/match"]));
              }

              if(s.status == "WINNER_TWO"){
                Swal.fire('Match ended', `The winner is `+s.playerTwo.toString(), 'success',).then(result =>this._router.navigate(["/match"]));
              }

              if(s.status == "TIE"){
                Swal.fire('Match ended', `The game ended in a draw `, 'success',).then(result =>this._router.navigate(["/match"]));
              }
            }
          }
      });
    }
  }

  cells = [{id: 0, bg: 'black'}, {id: 1, bg: 'black'}, {id: 2,  bg: 'black'}, {id: 3, bg: 'black'},  {id: 4, bg: 'black'},  {id: 5, bg: 'black'}]
  counter = 5;

  addToken(num: number){
    var metadata: Metadata = new Metadata(this.sessionId.toString(), this.username, this.movement, this.round);
    this.client.publish({destination: "/app/movement", body: JSON.stringify(metadata)});
 }
}
