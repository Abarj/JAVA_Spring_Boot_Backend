import { Client } from "src/app/clients/client";
import { ItemsBill } from "./items-bill";

export class Bill {

    id: number;
    description: string;
    observations: string;
    items: ItemsBill[] = [];
    client: Client;
    total: number;
    createAt: string;

    calculateTotal(): number {
        this.total = 0;
        this.items.forEach((item: ItemsBill) => {
            this.total += item.calculateInvoice()
        })
        return this.total;
    }
}