package ca.cmpt213.a4.client.model;

import java.time.LocalDate;

import static java.lang.Math.abs;

/**
 * Subclass of parent class Consumable gives unique attribute of a Food consumable
 */
public class Food extends Consumable {


    private double weight; //valid only for food items


    public Food(String name, String notes, double price, double quantity, LocalDate expiry) {

        this.name = name;
        this.notes = notes;
        this.price = price;
        this.quantity = quantity;
        this.expiryDate = expiry;
        setExpiryDays();
    }

    @Override
    public String toString() {

        String ret =
                "Name: " + name + "\n" +
                        "Notes: " + notes + "\n" +
                        "Price: " + price + "\n" +
                        "Weight: " + quantity + "\n" +
                        "Expiry Date: " + expiryDate + "\n";

        if (getExpiryDays() > 0) {

            ret += "This item will expire in " + abs(getExpiryDays()) + " day(s).\n";

        } else if (getExpiryDays() < 0) {

            ret += "This item is expired for " + abs(getExpiryDays()) + " day(s).\n";

        } else {

            ret += "This item will expire today.\n";

        }


        return ret;
    }

    public String getType() {

        return "Food";
    }


}
