export interface Food {
    id?: number;
    name: string;
    categoryId: number;
    price: number;
    desc: string;
    offerId?: number;
    enabled?: boolean;
    createdDateTime?: string;
}
