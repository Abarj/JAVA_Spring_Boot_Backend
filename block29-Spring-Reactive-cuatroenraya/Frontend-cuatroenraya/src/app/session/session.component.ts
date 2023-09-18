import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import swal from "sweetalert2";
import { Subscription } from 'rxjs';
import { Session } from '../session';
import { PieceService } from '../piece.service';
import { SessionService } from './session.service';


@Component({
  selector: 'app-session',
  templateUrl: './session.component.html',
  styleUrls: ['./session.component.css']
})
export class SessionComponent {
  username!: string;
  subscription!: Subscription;
  public session: Session = new Session();
  sessions: Session[] = [];
  observe: string = '/assets/visiones.png';
  
  constructor(public sessionService: SessionService,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              private data: PieceService,
              private http: HttpClient) {}

  ngOnInit(): void {
    this.subscription = this.data.currentUser.subscribe(user => this.username = user);
    this.loadSessions();

    if(this.username == "default"){
      swal.fire('<strong>Session expired</strong>', `Back to menu`, 'success', ).then((result) => this.router.navigate(["/user"]));
    }
  }


  newSession(idBs: number) {
    this.data.changeId(idBs)
    this.data.changeUser(this.username)

    if(this.session.playerOne != this.username){
      if(this.session.playerTwo == "" || this.session.playerTwo == null) {
        this.session.playerTwo = this.username;
      }
      
      this.http.post("http://localhost:8080/session/playerTwo/" + idBs + "/" + this.username, "").subscribe();
    }

    this.router.navigate(["/board"])
  }

  loadSessions():void{
    this.sessionService.getSessions().subscribe(sessions => {
      this.sessions = sessions
      console.log(sessions);
    })
  }


  public create(): void{
    this.session.playerOne = this.username;
    this.sessionService.create(this.session).subscribe(session => {
        swal.fire('<strong>New game</strong>', `${this.session.playerOne} has created a game successfully`, 'success').then((result) =>
        this.loadSessions()
      );
    })
  }

  delete(session: Session): void{
    if(this.username == session.playerOne) {
      swal.fire({
        title: '<strong>Are you sure about deleting the game?</strong>',
        text: `Surely you want to delete the item with the ID: ${session.id} and the username: ${session.playerOne}`,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: 'rgb(0, 255, 0)',
        cancelButtonColor: '#d33',
  
        confirmButtonText: '¡Yes, delete!',
        cancelButtonText: 'Cancel'
      }).then((result) => {
        if (result.value) {
          this.sessionService.delete(session.id).subscribe(response => {
              this.sessions = this.sessions?.filter(session => session!== session)
              this.loadSessions();
  
              swal.fire(
                '<strong>¡Game eliminated!</strong>',
                'The game has been eliminated',
                'success'
              )}
          )}
      })
    } else {
      swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'You cannot delete a game created by another user!'
      })
    }
  }
}
