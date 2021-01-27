import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import swal from 'sweetalert';

import { Category } from '../../../models/category';
import { DataService } from 'src/app/services/data.service';
import { Quiz } from '../../../models/quiz';
import { QuizService } from '../../../services/quiz.service';

@Component({
  selector: 'app-tester-quiz-update',
  templateUrl: './tester-quiz-update.component.html',
  styleUrls: ['./tester-quiz-update.component.scss']
})
export class TesterQuizUpdateComponent implements OnInit, OnDestroy {

  validated = false;
  message = '';
  quiz?: Quiz;
  private categories: Category[] = [];
  private subscriptionCategories: Subscription;

  constructor(private activatedRoute: ActivatedRoute, private router: Router,
              private quizService: QuizService, private dataService: DataService) {
    this.subscriptionCategories = this.dataService.categories.subscribe(
      categories => this.categories = categories);
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.quizService.getQuizById(+params.id).subscribe(
        (quiz: Quiz) => {
          const reconstructedQuiz: Quiz = this.constructQuiz(quiz);

          if (this.isValidated(reconstructedQuiz)) {
            this.quiz = quiz;
            this.validated = true;
          }
        },
        (err: any) => swal(err.error.message, '', 'error')
      );
    });
  }

  isValidated(json: Quiz): boolean {
    // TODO::More accurate validation required here
    if (json.name && json.categoryName && json.userId && json.questions &&
      (json.questions.length > 0) && json.questions[0].desc && (json.questions[0].answerIndex >= 0) &&
      json.questions[0].choices && (json.questions[0].choices.length > 0) &&
      json.questions[0].choices[0].desc) {
      this.message = 'Quiz JSON format is successfully validated';
      return true;
    }

    this.message = 'Quiz JSON format validation failed';
    return false;
  }

  constructQuiz(quiz: Quiz): Quiz {
    delete quiz.createdDateTime;

    quiz.categoryName = this.categories.filter(
      category => category.id === quiz.categoryId)[0].name;
    delete quiz.categoryId;

    for (const question of quiz.questions) {
      delete question.id;

      for (const choice of question.choices) {
        delete choice.id;
      }
    }

    return quiz;
  }

  onValidate(event: Event): void {
    if (event === null) { return; }

    const data: string = (event.target as HTMLTextAreaElement).value || '{}';
    const json: Quiz = JSON.parse(data);

    if (this.isValidated(json)) {
      this.quiz = json;
      this.validated = true;
    } else {
      this.validated = false;
    }
  }

  onUpdateQuiz(): void {
    if (this.quiz === undefined) {
      return;
    }

    const input: Quiz = this.quiz;
    input.categoryId = this.categories.filter(
      category => category.name === input.categoryName)[0].id;
    delete input.categoryName;

    this.quizService.updateQuiz(input).subscribe(
      (quiz: Quiz) => this.quiz = quiz,
      (err: any) => swal(err.error.message, '', 'error'),
      () => this.router.navigate(['/tester/home/quiz'])
    );
  }

  ngOnDestroy(): void {
    this.subscriptionCategories.unsubscribe();
  }

}
