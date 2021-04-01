import { Address } from './address';

export interface LoginUser {
    email: string;
    username: string;
    role: string;
    phone?: string;
    address?: Address;
}
