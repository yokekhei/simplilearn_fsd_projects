import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

import { Review } from '../../../models/review';

@Component({
  selector: 'app-testee-quiz-review',
  templateUrl: './testee-quiz-review.component.html',
  styleUrls: ['./testee-quiz-review.component.scss']
})
export class TesteeQuizReviewComponent implements OnInit {

  @Input() review?: Review;
  @Output() tabChange: EventEmitter<number> = new EventEmitter<number>();

  constructor() { }

  ngOnInit(): void {
  }

  onReviewSelected(): void {
    if (this.review === undefined) { return; }

    this.tabChange.emit(this.review?.questionNo - 1);
  }

}
