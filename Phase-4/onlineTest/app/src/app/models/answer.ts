import { FormChoice } from './form-choice';
import { Quiz } from './quiz';

export interface Answer {
    quiz: Quiz;
    formChoices: FormChoice[];
}
