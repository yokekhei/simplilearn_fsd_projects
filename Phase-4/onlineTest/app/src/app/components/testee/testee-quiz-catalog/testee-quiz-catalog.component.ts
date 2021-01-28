import { ActivatedRoute } from '@angular/router';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';

import { Category } from 'src/app/models/category';
import { CategoryService } from './../../../services/category.service';
import { DataService } from 'src/app/services/data.service';
import { Quiz } from '../../../models/quiz';
import { QuizService } from './../../../services/quiz.service';

@Component({
  selector: 'app-testee-quiz-catalog',
  templateUrl: './testee-quiz-catalog.component.html',
  styleUrls: ['./testee-quiz-catalog.component.scss']
})
export class TesteeQuizCatalogComponent implements OnInit, OnDestroy {

  quizList: { quizzes: Quiz[] }[] = [];
  category?: Category;
  categoryName = '';
  private categoryId = -1;
  private categories: Category[] = [];
  private subscriptionCategories: Subscription;
  private COL_NUM = 4;

  constructor(private activatedRoute: ActivatedRoute, private dataService: DataService,
              private quizService: QuizService, private categoryService: CategoryService) {
    this.subscriptionCategories = this.dataService.categories.subscribe(
      categories => {
        this.categories = categories;
        this.refreshQuizList();
      }
    );
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.categoryId = +params.id;

      if (this.categories.length === 0) {
        this.refreshCategories();
      } else {
        this.refreshQuizList();
      }
    });
  }

  refreshQuizList(): void {
    const result: Category[] = this.categories.filter(
      category => category.id === this.categoryId);

    if (result.length > 0) {
      this.category = result[0];
      this.categoryName = this.category.name;
      this.dataService.changeLatestCategory(this.category);
      this.quizService.getQuizzesByCategory(this.category.id)
        .subscribe(quizzes => {
          this.quizList = [];

          if (quizzes.length > 0) {
            const rowNumber = (quizzes.length / this.COL_NUM) + (quizzes.length % this.COL_NUM) - 1;
            let index = 0;
            let count = quizzes.length;

            for (let row = 0; row <= rowNumber; row++) {
              const rowQuizzes: Quiz[] = [];

              for (let col = 0; col < this.COL_NUM && count > 0; col++) {
                const quiz: Quiz = quizzes[index++];
                rowQuizzes.push(quiz);
                count--;
              }

              this.quizList.push({ quizzes: rowQuizzes });
            }
          }
        });
    }
  }

  refreshCategories(): void {
    this.categoryService.getCategories().subscribe(
      categories => {
        this.categories = categories;
        this.dataService.changeCategories(this.categories);
        this.refreshQuizList();
      }
    );
  }

  getQuizImageUrl(quizId: number | undefined): string {
    if (quizId === undefined) { return ''; }

    return this.quizService.getQuizImageUrl(quizId);
  }

  ngOnDestroy(): void {
    this.subscriptionCategories.unsubscribe();
  }

}
