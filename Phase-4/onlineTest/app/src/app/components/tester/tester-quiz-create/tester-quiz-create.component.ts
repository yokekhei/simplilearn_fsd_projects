import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import swal from 'sweetalert';

import { Category } from '../../../models/category';
import { DataService } from 'src/app/services/data.service';
import { Quiz } from '../../../models/quiz';
import { QuizService } from './../../../services/quiz.service';

@Component({
  selector: 'app-tester-quiz-create',
  templateUrl: './tester-quiz-create.component.html',
  styleUrls: ['./tester-quiz-create.component.scss']
})
export class TesterQuizCreateComponent implements OnInit, OnDestroy {

  data = '';
  jsonFileName = '';
  validated = false;
  message = '';
  quiz: Quiz;
  imageFileName = '';
  private selectedImages?: FileList;
  private categories: Category[] = [];
  private subscriptionCategories: Subscription;

  constructor(private dataService: DataService, private quizService: QuizService,
              private router: Router) {
    this.subscriptionCategories = this.dataService.categories.subscribe(
      categories => this.categories = categories);
    this.quiz = {
      name: 'Quiz name',
      categoryName: 'Category name',
      userId: 'Your login email address',
      questions: [
        {
          desc: 'Question 1',
          choices: [
            {
              desc: 'Choice 1 (This is the answer)'
            },
            {
              desc: 'Choice 2'
            },
            {
              desc: 'Choice 3'
            },
            {
              desc: 'Choice 4'
            }
          ],
          answerIndex: 0
        },
        {
          desc: 'Question 2',
          choices: [
            {
              desc: 'Choice 1'
            },
            {
              desc: 'Choice 2'
            },
            {
              desc: 'Choice 3'
            },
            {
              desc: 'Choice 4 (This is the answer)'
            }
          ],
          answerIndex: 3
        }
      ]
    };
  }

  ngOnInit(): void {
  }

  onFileSelected(): void {
    const inputNode: any = document.querySelector('#file');
    const reader = new FileReader();

    reader.onload = (e: any) => {
      this.validated = false;
      this.data = e.target.result;

      if (this.isValidated(this.data)) {
        this.validated = true;
        this.constructQuiz(this.data);
      }
    };

    this.jsonFileName = inputNode.files[0].name;

    reader.readAsText(inputNode.files[0]);
  }

  isValidated(fileData: string): boolean {
    if (fileData) {
      try {
        const json: Quiz = JSON.parse(fileData);

        // TODO::More accurate validation required here
        if (json.name && json.categoryName && json.userId && json.questions &&
              (json.questions.length > 0) && json.questions[0].desc &&
              (json.questions[0].answerIndex >= 0) &&
              json.questions[0].choices && (json.questions[0].choices.length > 0) &&
              json.questions[0].choices[0].desc) {
                if (this.categories.filter(category => category.name === json.categoryName).length === 0) {
                  this.message = 'Category name does not match';
                  return false;
                }

                this.message = 'Quiz JSON format is successfully validated';
                return true;
        }
      } catch (e) { }
    }

    this.message = 'Quiz JSON format validation failed';
    return false;
  }

  constructQuiz(fileData: string): void {
    this.quiz = JSON.parse(fileData);
    this.quiz.categoryId = this.categories.filter(
      category => category.name === this.quiz.categoryName)[0].id;
    delete this.quiz.categoryName;
  }

  onAddQuiz(): void {
    if (this.selectedImages !== undefined) {
      return this.addQuizWithImage(this.selectedImages.item(0) as File);
    }

    this.quizService.createQuiz(this.quiz).subscribe(
      (quiz: Quiz) => this.quiz = quiz,
      (err: any) => swal(err.error.message, '', 'error'),
      () => this.router.navigate(['/tester/home/quiz'])
    );
  }

  onImageSelected(event: any): void {
    this.selectedImages = event.target.files;
    this.imageFileName = event.target.files[0].name;
  }

  addQuizWithImage(image: File): void {
    this.quizService.createQuizWithImage(this.quiz, image).subscribe(
      (quiz: Quiz) => this.quiz = quiz,
      (err: any) => swal(err.error.message, '', 'error'),
      () => this.router.navigate(['/tester/home/quiz'])
    );
  }

  ngOnDestroy(): void {
    this.subscriptionCategories.unsubscribe();
  }

}
