export interface ChargeRequest {
    description: string;
    amount: number;
    currency: string;
    stripeEmail: string;
    stripeToken: string;
}
