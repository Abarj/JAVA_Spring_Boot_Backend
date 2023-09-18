import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ColumnComponent } from './column/column.component';
import { FooterComponent } from './footer/footer.component';
import { InitComponent } from './init/init.component';
import { BoardComponent } from './board/board.component';
import { ChatComponent } from './board/chat/chat.component';
import { UserComponent } from './user/user.component';
import { SessionComponent } from './session/session.component';
import { ActivatedRoute, Routes, RouterModule } from '@angular/router';
import { PieceService } from './piece.service';
import { CommonModule } from '@angular/common';

const routes: Routes = [
  { path: '', redirectTo:'/init', pathMatch:'full'},
  { path: "board", component: BoardComponent},
  { path: "init", component: InitComponent},
  { path: 'user', component: UserComponent },
  { path: 'session', component: SessionComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    ColumnComponent,
    FooterComponent,
    InitComponent,
    BoardComponent,
    ChatComponent,
    UserComponent,
    SessionComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    CommonModule,
    FormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [PieceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
