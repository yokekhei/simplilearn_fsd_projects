import { Component, OnInit } from '@angular/core';
import { Common } from 'src/app/core/common';

@Component({
  selector: 'app-user-header',
  templateUrl: './user-header.component.html',
  styleUrls: ['./user-header.component.scss']
})
export class UserHeaderComponent implements OnInit {

  userName = Common.GUEST_NAME;
  isGuest = true;

  constructor() { }

  ngOnInit(): void {
  }

}
