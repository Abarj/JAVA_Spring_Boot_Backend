import { Product } from "./product";

export class ItemsBill {

    product: Product;
    amount: number = 1;
    invoice: number;

    public calculateInvoice(): number {
        return this.amount * this.product.price
    }
}
