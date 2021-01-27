import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import swal from 'sweetalert';

import { Category } from '../../../models/category';
import { CategoryService } from './../../../services/category.service';
import { DataService } from 'src/app/services/data.service';
import { Quiz } from '../../../models/quiz';
import { QuizService } from './../../../services/quiz.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-tester-quizzes',
  templateUrl: './tester-quizzes.component.html',
  styleUrls: ['./tester-quizzes.component.scss']
})
export class TesterQuizzesComponent implements OnInit, OnDestroy {

  quizzes: Quiz[] = [];
  private categories: Category[] = [];
  private subscriptionCategories: Subscription;

  constructor(private dataService: DataService, private categoryService: CategoryService,
              private quizService: QuizService, private userService: UserService,
              private router: Router) {
    this.subscriptionCategories = this.dataService.categories.subscribe(
      categories => this.categories = categories);
  }

  ngOnInit(): void {
    if (this.categories.length === 0) {
      this.categoryService.getCategories().subscribe(
        categories => {
          this.dataService.changeCategories(categories);
        }
      );
    }

    this.getQuizList();
  }

  getDate(strDate?: string): Date {
    if (strDate === undefined) {
      return new Date();
    }

    return new Date(strDate);
  }

  getCategory(id?: number): string {
    if (id === undefined || this.categories.length === 0) {
      return '-';
    }

    return this.categories.filter(category => category.id === id)[0].name;
  }

  getQuizList(): void {
    this.quizService.getQuizzesByTester(this.userService.loginUser?.email || '')
      .subscribe(quizzes => this.quizzes = quizzes);
  }

  onDeleteQuiz(id?: number): void {
    if (id === undefined) { return; }

    swal({
      title: 'Are you sure that you want to remove quiz?',
      text: '',
      icon: 'warning',
      dangerMode: true,
    })
    .then(willDelete => {
      if (willDelete) {
        this.quizService.deleteQuiz(id).subscribe(
          (quiz: Quiz) => swal(`Quiz ${ quiz.name } removed successfully`, '', 'success'),
          (err: any) => swal(err.error.message, '', 'error'),
          () => {
            this.getQuizList();
            this.router.navigate(['/tester/home/quiz']);
          }
        );
      }
    });
  }

  ngOnDestroy(): void {
    this.subscriptionCategories.unsubscribe();
  }

}
