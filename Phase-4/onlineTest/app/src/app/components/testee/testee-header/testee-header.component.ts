import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-testee-header',
  templateUrl: './testee-header.component.html',
  styleUrls: ['./testee-header.component.scss']
})
export class TesteeHeaderComponent implements OnInit {

  userName = 'Guest';
  categories: string[];
  isGuest: boolean;

  constructor() {
    this.categories = [];
    this.isGuest = true;
  }

  ngOnInit(): void {
    this.categories.push('Mathematics');
    this.categories.push('Language');
  }

}
