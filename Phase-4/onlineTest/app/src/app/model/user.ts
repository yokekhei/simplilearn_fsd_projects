export interface User {
    email: string;
    password: string;
    username?: string;
    role: string;
    enabled?: boolean;
}
