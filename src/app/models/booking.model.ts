import { Restaurant } from "./restaurant.model";

export interface Booking {
    bookId: number;
    bookName: string;
    bookDate: string;
    bookTime: string;
    person: string;
    occasion: string;
    telephone: string;
    email: string;
    restaurantModel: Restaurant;

    
}
