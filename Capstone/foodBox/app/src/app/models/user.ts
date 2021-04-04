import { Address } from './address';

export interface User {
    email: string;
    password: string;
    username?: string;
    phone?: string;
    role: string;
    address?: Address;
    enabled?: boolean;
    createdDateTime?: string;
}
