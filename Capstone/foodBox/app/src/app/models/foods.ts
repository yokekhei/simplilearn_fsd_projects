import { PageInfo } from './page-info';
import { Food } from './food';

export interface Foods {
    list: Food[];
    pageInfo: PageInfo;
}
