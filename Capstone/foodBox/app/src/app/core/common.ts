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

    static CURRENCY_USD = 'usd';
    static CURRENCY_EUR = 'eur';
    static CURRENCY_MYR = 'myr';

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

    static paddy(value: number, padLen: number, padChar: string): string {
        const padStr = typeof padChar !== 'undefined' ? padChar : '0';
        const pad = new Array(1 + padLen).join(padStr);
        return (pad + value).slice(-pad.length);
    }

    static formatHtmlDate(localeDate: Date): string {
        // M/d/yyyy to yyyy-MM-dd
        const localeDateStr = localeDate.toLocaleDateString();
        const inputs: string[] = localeDateStr.split('/');

        const month = Common.paddy(+inputs[0], 2, '0');
        const day = Common.paddy(+inputs[1], 2, '0');
        const year = inputs[2];

        return `${year}-${month}-${day}`;
    }

    static formatServiceDate(htmlDate: string): string {
        // yyyy-MM-dd to dd-MM-yyyy
        const inputs: string[] = htmlDate.split('-');
        const year = inputs[0];
        const month = inputs[1];
        const day = inputs[2];

        return `${day}-${month}-${year}`;
    }

}
