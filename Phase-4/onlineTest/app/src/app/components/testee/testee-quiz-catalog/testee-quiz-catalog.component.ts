import { ActivatedRoute } from '@angular/router';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';

import { Category } from 'src/app/models/category';
import { DataService } from 'src/app/services/data.service';
import { Quiz } from '../../../models/quiz';
import { QuizService } from './../../../services/quiz.service';

@Component({
  selector: 'app-testee-quiz-catalog',
  templateUrl: './testee-quiz-catalog.component.html',
  styleUrls: ['./testee-quiz-catalog.component.scss']
})
export class TesteeQuizCatalogComponent implements OnInit, OnDestroy {

  quizzes: Quiz[] = [];
  category: Category;
  private categories: Category[] = [];
  private subscriptionCategories: Subscription;

  constructor(private activatedRoute: ActivatedRoute, private dataService: DataService,
              private quizService: QuizService) {
    this.category = { id: 0, name: 'Default Category' };
    this.subscriptionCategories = this.dataService.categories.subscribe(
      categories => this.categories = categories);
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      if (this.categories.length > 0) {
        const result: Category[] = this.categories.filter(
          category => category.id === +params.id);

        if (result.length > 0) {
          this.category = result[0];
          this.dataService.changeLatestCategory(this.category);
          this.refreshQuizList();
        }
      }
    });
  }

  refreshQuizList(): void {
    this.quizService.getQuizzesByCategory(this.category.id)
      .subscribe(quizzes => this.quizzes = quizzes);
  }

  ngOnDestroy(): void {
    this.subscriptionCategories.unsubscribe();
  }

}
