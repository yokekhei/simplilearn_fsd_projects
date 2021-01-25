import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ConfigurationService } from './configuration.service';
import { Quiz } from './../model/quiz';

@Injectable({
  providedIn: 'root'
})
export class QuizService {

  private url = '';

  constructor(private configuration: ConfigurationService, private http: HttpClient) {
    this.url = `${this.configuration.getValue('apiUrl')}/quiz`;
  }

  getQuizById(id: number): Observable<Quiz> {
    return this.http.get<Quiz>(`${this.url}/${id}`);
  }

  getQuizzesByCategory(id: number): Observable<Quiz[]> {
    return this.http.get<Quiz[]>(`${this.url}/category/${id}`).pipe(
      map(quizzes => {
        return quizzes.map(quiz => {
          return {
            id: quiz.id,
            name: quiz.name,
            categoryId: quiz.categoryId,
            userId: quiz.userId,
            questions: quiz.questions,
            createdDateTime: quiz.createdDateTime
          };
        });
      })
    );
  }

  getQuizzesByTester(email: string): Observable<Quiz[]> {
    return this.http.get<Quiz[]>(`${this.url}/tester/${email}`).pipe(
      map(quizzes => {
        return quizzes.map(quiz => {
          return {
            id: quiz.id,
            name: quiz.name,
            categoryId: quiz.categoryId,
            userId: quiz.userId,
            questions: quiz.questions,
            createdDateTime: quiz.createdDateTime
          };
        });
      })
    );
  }

  createQuiz(quiz: Quiz): Observable<Quiz> {
    return this.http.post<Quiz>(this.url, quiz);
  }

  updateQuiz(quiz: Quiz): Observable<Quiz> {
    return this.http.put<Quiz>(this.url, quiz);
  }

  deleteQuiz(id: number): Observable<Quiz> {
    return this.http.delete<Quiz>(`${this.url}/${id}`);
  }

}
