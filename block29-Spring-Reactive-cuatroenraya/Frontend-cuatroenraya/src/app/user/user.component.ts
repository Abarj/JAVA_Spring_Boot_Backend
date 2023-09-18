import { Subscription } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { PieceService } from '../piece.service';
import { User } from './entity/user';


@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent {
  show = false;
  subscription!: Subscription;

  constructor(private http: HttpClient,
              private _router: Router,
              private data: PieceService) {
  }
  ngOnInit(): void {
    this.subscription = this.data.currentUser.subscribe()
  }

  onSubmit(userData: {username: string, password: string})
  {
    // Limpia el nombre de usuario almacenado en caché y manda los datos del usuario al controlador del servidor
    
    var user = new User(userData.username, userData.password);
    this.http.post("http://localhost:8080/player", user)
    .subscribe((response) => {
      // Si no coinciden usuario y contraseña muestra el aviso de error
      if(response == false){
        this.show = true
      }
      // Si coinciden usuario y contraseña o se registró un nuevo usuario, guarda en caché el nombre y redirige
      // al componente de partida
      else{
        this.data.changeUser(user.getUserName())
        this._router.navigate(['/session'])
      }
      //!response ? this.show = true:  this._router.navigate(['/partida']);
    });
  }
}
