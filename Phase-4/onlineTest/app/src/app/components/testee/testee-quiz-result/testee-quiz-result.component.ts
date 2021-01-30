import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import swal from 'sweetalert';

import { Answer } from 'src/app/models/answer';
import { DataService } from 'src/app/services/data.service';
import { FormChoice } from '../../../models/form-choice';
import { Question } from 'src/app/models/question';
import { ResultListItemStyle } from '../../../models/result-list-item-style';
import { ResultView } from '../../../models/result-view';

@Component({
  selector: 'app-testee-quiz-result',
  templateUrl: './testee-quiz-result.component.html',
  styleUrls: ['./testee-quiz-result.component.scss']
})
export class TesteeQuizResultComponent implements OnInit, OnDestroy {

  answer?: Answer;
  quizName = '';
  categoryName = '';
  questions: Question[] = [];
  resultViews: ResultView[] = [];
  correctStat = 0;
  incorrectStat = 0;
  unattemptedStat = 0;
  score = 0;
  private suscriptionAnswer: Subscription;
  private subscriptionLatestCategory: Subscription;

  constructor(private dataService: DataService, private router: Router) {
    this.subscriptionLatestCategory = this.dataService.latestCategory
      .subscribe(latestCategory => {
        this.categoryName = latestCategory.name;
    });

    this.suscriptionAnswer = this.dataService.answer.subscribe(answer => {
      if (answer === undefined || answer.quiz === undefined) {
        this.router.navigate(['/testee/category']);
      } else {
        this.answer = answer;
        this.quizName = this.answer.quiz.name;

        let questionIndex = 0;

        // Construct result views for each choice list item
        for (const question of this.answer.quiz.questions) {
          this.questions.push(question);

          const formChoices: FormChoice[] = this.answer.formChoices.filter(
            c => c.questionIdx === questionIndex);
          const selectedIndex: number = formChoices.indexOf(
            formChoices.filter(c => c.selected)[0]
          );

          let choiceIndex = 0;

          for (const choice of formChoices) {
            const resultView: ResultView = {
              questionIdx: questionIndex,
              choiceIdx: choiceIndex,
              listItemStyle: {
                correct: choiceIndex === question.answerIndex,
                normal: choiceIndex !== selectedIndex,
                tick: (selectedIndex !== -1) &&
                  (choiceIndex === selectedIndex) &&
                  (selectedIndex === question.answerIndex),
                cross: (selectedIndex !== -1) &&
                  (choiceIndex === selectedIndex) &&
                  (selectedIndex !== question.answerIndex),
                unattempt: (selectedIndex === -1) &&
                  (choiceIndex !== selectedIndex) &&
                  (choiceIndex === question.answerIndex)
              }
            };

            choiceIndex++;
            this.resultViews.push(resultView);
          }

          questionIndex++;
        }

        // Summary stats
        this.correctStat = this.resultViews.reduce((accumulator, view) => {
          return accumulator + (view.listItemStyle.tick ? 1 : 0);
        }, 0);

        this.incorrectStat = this.resultViews.reduce((accumulator, view) => {
          return accumulator + (view.listItemStyle.cross ? 1 : 0);
        }, 0);

        this.unattemptedStat = this.resultViews.reduce((accumulator, view) => {
          return accumulator + (view.listItemStyle.unattempt ? 1 : 0);
        }, 0);

        this.score = (this.correctStat / this.questions.length) * 100;

        if (this.score === 100) {
          swal('Congratulations!!!', 'You scored 100%', 'success');
        } else {
          swal('Quiz Result', `Your score is ${Math.round(this.score)}%`, 'info');
        }
      }
    });
  }

  getListItemStyle(questionIdx: number, choiceIdx: number): ResultListItemStyle {
    return this.resultViews.filter(
      v => v.questionIdx === questionIdx && v.choiceIdx === choiceIdx)[0].listItemStyle;
  }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    this.suscriptionAnswer.unsubscribe();
    this.subscriptionLatestCategory.unsubscribe();
  }

}
