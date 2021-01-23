import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Common } from 'src/app/core/common';
import { DataService } from 'src/app/services/data.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-tester-logout',
  templateUrl: './tester-logout.component.html',
  styleUrls: ['./tester-logout.component.scss']
})
export class TesterLogoutComponent implements OnInit {

  constructor(private dataService: DataService, private userService: UserService,
              private router: Router) { }

  ngOnInit(): void {
    this.userService.removeSession();

    this.dataService.changeLoginUser(
      { email: '', username: '', role: Common.ROLE_TESTER });
    this.router.navigate(['/tester/login']);
  }

}
