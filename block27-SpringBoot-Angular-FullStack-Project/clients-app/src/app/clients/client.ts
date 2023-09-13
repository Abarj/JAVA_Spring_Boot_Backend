import { Bill } from "../bills/models/bill";
import { Region } from "./region";

export class Client {

    id: number;
    name: string;
    lastName: string;
    createAt: string;
    email: string;
    avatar: string;
    region: Region;
    bills: Bill[] = [];
}
