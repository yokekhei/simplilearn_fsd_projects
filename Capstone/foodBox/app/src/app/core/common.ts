export class Common {

    static GUEST_NAME = 'Guest';

    static ROLE_ADMIN = 'A';
    static ROLE_USER = 'U';

    static SORT_BY_NAME = 'name';
    static SORT_BY_CATEGORY = 'category';
    static SORT_BY_PRICE = 'price';
    static SORT_BY_OFFER = 'offer';
    static SORT_BY_DATE = 'createdDateTime';
    static SORT_BY_ENABLED = 'enabled';

    static SORT_DIRECTION_ASC = 'asc';
    static SORT_DIRECTION_DESC = 'desc';

    static OFFER_TYPE_PCT = 'PCT';
    static OFFER_TYPE_CSH = 'CSH';

    static ALL_VALUES = 9999;

    static getOfferDiscountType(type: string | undefined): string {
        switch (type) {
            case 'PCT':
                return 'Percentage';

            case 'CSH':
                return 'Cash';

            default:
                return 'N/A';
        }
    }

}
