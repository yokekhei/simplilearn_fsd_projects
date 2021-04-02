import { Food } from 'src/app/models/food';

export interface OrderItem {
    id?: number;
    food: Food;
    quantity: number;
    price: number;
    discount: number;
}
