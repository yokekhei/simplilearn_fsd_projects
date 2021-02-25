import { Address } from './address';

export interface User {
    email: string;
    password: string;
    username?: string;
    role: string;
    address?: Address;
    enabled?: boolean;
}
