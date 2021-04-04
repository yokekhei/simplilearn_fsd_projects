import { CartItem } from './cart-item';

export interface Cart {
    items: CartItem[];
    subTotalPrice?: number;
    totalDiscount?: number;
    deliveryFee?: number;
    totalPrice?: number;
}
