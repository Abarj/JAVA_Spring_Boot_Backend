import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-init',
  templateUrl: './init.component.html',
  styleUrls: ['./init.component.css']
})
export class InitComponent {
  direction: string = '/assets/images/image.png'

  constructor(private router: Router) {}

  ngOnInit(): void {
    
  }
}
