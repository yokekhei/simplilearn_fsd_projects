import { FormChoice } from './form-choice';
import { Review } from './review';

export interface QuizStepView {
    tabDisplay: string;
    review: Review;
    choices: FormChoice[];
}
