import { Question } from './question';

export interface Quiz {
    id?: number;
    name: string;
    categoryId?: number;
    categoryName?: string;
    userId: string;
    questions: Question[];
    createdDateTime?: string;
}
