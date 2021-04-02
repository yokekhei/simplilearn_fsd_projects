import { LoginUser } from './login-user';
import { OrderItem } from './order-item';

export interface Order {
    id?: number;
    chargeId: string;
    userId: string;
    items: OrderItem[];
    price: number;
    discount: number;
    deliveryFee: number;
    createdDateTime?: string;
}
