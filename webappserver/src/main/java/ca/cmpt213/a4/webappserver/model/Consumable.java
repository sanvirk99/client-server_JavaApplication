package ca.cmpt213.a4.webappserver.model;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * defines the common attributes among consumable object Parent class
 **/
public class Consumable implements Comparable<Consumable> {

    protected String type;
    protected String name;
    protected String notes;
    protected double price = 0;
    protected double quantity = 0;
    protected long expiryDays = 0;
    protected LocalDate expiryDate;


    public Consumable(String name, String notes, double price, double quantity, LocalDate expiry) {

    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Consumable() {

    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }


    public String getNotes() {
        return notes;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getExpiryDate() {

        return expiryDate.toString();
    }

    public long getExpiryDays() {
        return expiryDays;
    }


    protected void setExpiryDays() {
        LocalDate present = LocalDate.now();
        this.expiryDays = present.until(expiryDate, ChronoUnit.DAYS);
    }


    public int compareTo(Consumable o) {
        return Long.compare(this.expiryDays, o.expiryDays); //if o is greater 1 ,=0, less -1
    }


}
