import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ConfigurationService } from './configuration.service';
import { Quiz } from '../models/quiz';

@Injectable({
  providedIn: 'root'
})
export class QuizService {

  private url = '';

  constructor(private configuration: ConfigurationService, private http: HttpClient) {
    this.url = `${this.configuration.getValue('apiUrl')}/quiz`;
  }

  getQuizImageUrl(id: number): string {
    return `${this.url}/${id}/image`;
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

  createQuizWithImage(quiz: Quiz, image: File | null): Observable<Quiz> {
    const formData: FormData = new FormData();
    formData.append('quiz', new Blob([JSON.stringify(quiz)], {type: 'application/json'}));
    formData.append('image', image || '');

    return this.http.post<Quiz>(`${this.url}/image`, formData);
  }

  updateQuiz(quiz: Quiz): Observable<Quiz> {
    return this.http.put<Quiz>(this.url, quiz);
  }

  updateQuizWithImage(quiz: Quiz, image: File | null): Observable<Quiz> {
    const formData: FormData = new FormData();
    formData.append('quiz', new Blob([JSON.stringify(quiz)], {type: 'application/json'}));
    formData.append('image', image || '');

    return this.http.put<Quiz>(`${this.url}/image`, formData);
  }

  deleteQuiz(id: number): Observable<Quiz> {
    return this.http.delete<Quiz>(`${this.url}/${id}`);
  }

}
