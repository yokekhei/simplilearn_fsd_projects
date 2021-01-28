import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import swal from 'sweetalert';

import { Answer } from 'src/app/models/answer';
import { Category } from '../../../models/category';
import { DataService } from 'src/app/services/data.service';
import { FormChoice } from '../../../models/form-choice';
import { Question } from 'src/app/models/question';
import { Quiz } from '../../../models/quiz';
import { QuizService } from './../../../services/quiz.service';
import { QuizStepView } from '../../../models/quiz-step-view';

@Component({
  selector: 'app-testee-quiz',
  templateUrl: './testee-quiz.component.html',
  styleUrls: ['./testee-quiz.component.scss']
})
export class TesteeQuizComponent implements OnInit, OnDestroy {

  quiz?: Quiz;
  quizName = '';
  categoryName = '';
  questions: Question[] = [];
  currentTab = 0;
  stepViews: QuizStepView[] = [];
  prevBtnDisplay = 'none';
  nextBtnDisplay = 'none';
  nextBtnText = 'Next';
  nextBtnType = 'button';
  private subscriptionLatestCategory: Subscription;
  private latestCategory?: Category;
  private suscriptionAnswer: Subscription;

  constructor(private activatedRoute: ActivatedRoute,
              private router: Router,
              private quizService: QuizService,
              private dataService: DataService) {
    this.subscriptionLatestCategory = this.dataService.latestCategory
      .subscribe(latestCategory => {
        this.latestCategory = latestCategory;
        this.categoryName = latestCategory.name;
    });

    this.suscriptionAnswer = this.dataService.answer.subscribe(
      answer => {
        if (this.quiz !== undefined && answer.quiz.id === this.quiz.id) {
          this.router.navigate([`/testee/quiz/${this.quiz?.id}/result`]);
        }
      }
    );
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.quizService.getQuizById(+params.id).subscribe(
        (quiz: Quiz) => {
          this.quiz = quiz;
          this.quizName = this.quiz.name;
          this.questions = this.quiz.questions;

          let questionNumber = 1;
          for (const question of this.quiz.questions) {
            const formChoices: FormChoice[] = [];

            let choiceIdx = 0;
            for (const choice of question.choices) {
              formChoices.push({
                questionIdx: questionNumber - 1,
                choiceIdx: choiceIdx++,
                selected: false
              });
            }

            this.stepViews.push({
              tabDisplay: 'none',
              review: {
                questionNo: questionNumber++,
                isAnswered: false
              },
              choices: formChoices
            });
          }

          this.showTab(this.currentTab);
        },
        (err: any) => this.router.navigate([`/testee/category/${this.latestCategory?.id}`])
      );
    });
  }

  getCurrentQuestionNo(): number {
    return (this.currentTab >= this.questions.length ?
              this.questions.length - 1 :
              this.currentTab) + 1;
  }

  showTab(idx: number): void {
    if (idx >= this.questions.length) {
      return;
    }

    for (const view of this.stepViews) {
      view.tabDisplay = 'none';
    }

    this.stepViews[idx].tabDisplay = 'block';
    this.prevBtnDisplay = (idx === 0) ? 'none' : 'inline';
    this.nextBtnDisplay = (idx >= 0) ? 'inline' : 'none';

    if (this.quiz !== undefined && idx === this.quiz.questions.length - 1) {
      this.nextBtnText = 'Submit';
      this.nextBtnType = 'submit';
    } else {
      this.nextBtnText = 'Next';
      this.nextBtnType = 'button';
    }
  }

  nextPrev(n: number): void {
    if (this.currentTab < this.questions.length) {
      this.stepViews[this.currentTab].choices.forEach(c => {
        if (c.selected) {
          this.stepViews[this.currentTab].review.isAnswered = true;
        }
      });
    }

    if (this.currentTab >= this.questions.length) {
      this.currentTab = this.questions.length - 1;
    }

    this.currentTab = this.currentTab + n;
    this.showTab(this.currentTab);
  }

  onTabChange(tabIndex: number): void {
    this.currentTab = tabIndex;
    this.showTab(this.currentTab);
  }

  onChoiceChange(questionIdx: number, choiceIdx: number): void {
    this.stepViews[questionIdx].choices.forEach(c => c.selected = (c.choiceIdx === choiceIdx));
  }

  onSubmit(): void {
    if (this.currentTab < this.questions.length) { return; }

    if (this.quiz !== undefined) {
      swal({
        title: 'Are you sure that you want to submit answers now?',
        text: '',
        icon: 'warning',
        dangerMode: false,
      })
      .then(willSubmit => {
        if (willSubmit) {
          const choices: FormChoice[] = [];
          this.stepViews.forEach(tab => {
            tab.choices.forEach(c => choices.push(c));
          });

          const answer: Answer = {
            quiz: this.quiz || { name: '', userId: '', questions: [] },
            formChoices: choices
          };

          this.dataService.changeAnswer(answer);
        }
      });
    }
  }

  ngOnDestroy(): void {
    this.subscriptionLatestCategory.unsubscribe();
    this.suscriptionAnswer.unsubscribe();
  }

}
