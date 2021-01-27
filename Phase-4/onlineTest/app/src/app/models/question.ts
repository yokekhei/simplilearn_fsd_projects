import { Choice } from './choice';

export interface Question {
    id?: number;
    desc: string;
    choices: Choice[];
    answerIndex: number;
}
