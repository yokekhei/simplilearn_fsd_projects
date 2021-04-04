import { Food } from './food';

export interface CartItem {
    food: Food;
    quantity: number;
    totalPrice: number;
    discount: number;
}
