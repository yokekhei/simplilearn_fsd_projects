import { ResultListItemStyle } from './result-list-item-style';

export interface ResultView {
    questionIdx: number;
    choiceIdx: number;
    listItemStyle: ResultListItemStyle;
}
