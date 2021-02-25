import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AuthenticationService } from './../../../services/authentication.service';
import { Common } from 'src/app/core/common';
import { DataService } from './../../../services/data.service';

@Component({
  selector: 'app-admin-logout',
  templateUrl: './admin-logout.component.html',
  styleUrls: ['./admin-logout.component.scss']
})
export class AdminLogoutComponent implements OnInit {

  constructor(private dataService: DataService,
              private authService: AuthenticationService,
              private router: Router) { }

  ngOnInit(): void {
    this.authService.removeSession();

    this.dataService.changeLoginUser(
      { email: '', username: '', role: Common.ROLE_ADMIN });
    this.router.navigate(['/admin/login']);
  }

}
