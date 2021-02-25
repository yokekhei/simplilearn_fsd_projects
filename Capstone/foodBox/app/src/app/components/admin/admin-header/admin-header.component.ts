import { Component, OnInit } from '@angular/core';

import { AuthenticationService } from './../../../services/authentication.service';

@Component({
  selector: 'app-admin-header',
  templateUrl: './admin-header.component.html',
  styleUrls: ['./admin-header.component.scss']
})
export class AdminHeaderComponent implements OnInit {

  userName = '';

  constructor(private authService: AuthenticationService) {
    this.userName = this.authService.loginUser?.username || '';
  }

  ngOnInit(): void {
  }

}
