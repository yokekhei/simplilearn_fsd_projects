import { Component, OnInit } from '@angular/core';

import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-tester-header',
  templateUrl: './tester-header.component.html',
  styleUrls: ['./tester-header.component.scss']
})
export class TesterHeaderComponent implements OnInit {

  userName = '';

  constructor(private userService: UserService) {
    this.userName = this.userService.loginUser?.username || '';
  }

  ngOnInit(): void {
  }

}
