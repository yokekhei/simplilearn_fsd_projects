import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Common } from 'src/app/core/common';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-testee-logout',
  templateUrl: './testee-logout.component.html',
  styleUrls: ['./testee-logout.component.scss']
})
export class TesteeLogoutComponent implements OnInit {

  constructor(private dataService: DataService, private router: Router) { }

  ngOnInit(): void {
    sessionStorage.clear();
    this.dataService.changeLoginUser(
      { email: '', username: Common.GUEST_NAME, role: Common.ROLE_TESTEE });
    this.router.navigate(['/testee/login']);
  }

}
