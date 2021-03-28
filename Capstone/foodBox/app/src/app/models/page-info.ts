export interface PageInfo {
    page: number;
    size: number;
    numberOfElements?: number;
    totalElements?: number;
    totalPages: number;
    sortBy?: string;
    direction?: string;
}
