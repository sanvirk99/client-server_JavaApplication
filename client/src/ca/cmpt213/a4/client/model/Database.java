package ca.cmpt213.a4.client.model;

import com.google.gson.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Stores list of objects received from server
 * Updates list after remove and add by receiving new one from server
 * Custom Post And Get objects used to communicate with server inside this class
 */
public class Database implements Iterable<Consumable> {


    private ArrayList<Consumable> items = new ArrayList<>();
    private String filename = "";

    public Database() {
        //filename = args;
        try {

            GetRequest list=new GetRequest();
            JsonArray jsonFoodArray = list.getJsonArray();

            //use type and everything top define all type needed by add consumable
            for (JsonElement foodElement : jsonFoodArray) {


                JsonObject obj = foodElement.getAsJsonObject();
                LocalDate date = LocalDate.parse(obj.get("expiryDate").getAsString());
                String name=obj.get("name").getAsString();
                String notes=obj.get("notes").getAsString();
                double quantity=obj.get("quantity").getAsDouble();
                double price=obj.get("price").getAsDouble();


                switch (obj.get("type").getAsString()) {

                    case "Food":
                        items.add(new Food(name, notes, price, quantity, date));
                        break;
                    case "Drink":
                        items.add(new Drink(name, notes, price, quantity, date));
                        break;
                    default:

                }


            }

            items.sort(Consumable::compareTo);


        } catch (Exception e) {

                System.out.println("Error in IO between Server, make sure server is running");
        }


    }

    //clear current list fetch new from server
    public void updateList() {
        //filename = args;
        try {

            items.clear();
            GetRequest list=new GetRequest();
            JsonArray jsonFoodArray = list.getJsonArray();

            //use type and everything top define all type needed by add consumable
            for (JsonElement foodElement : jsonFoodArray) {


                JsonObject obj = foodElement.getAsJsonObject();
                LocalDate date = LocalDate.parse(obj.get("expiryDate").getAsString());
                String name=obj.get("name").getAsString();
                String notes=obj.get("notes").getAsString();
                double quantity=obj.get("quantity").getAsDouble();
                double price=obj.get("price").getAsDouble();


                switch (obj.get("type").getAsString()) {

                    case "Food":
                        items.add(new Food(name, notes, price, quantity, date));
                        break;
                    case "Drink":
                        items.add(new Drink(name, notes, price, quantity, date));
                        break;
                    default:

                }


            }

            items.sort(Consumable::compareTo);


        } catch (Exception e) {

            System.out.println("Error in IO between Server, make sure server is running");
        }


    }


    //when ever this call occurs get new list from server i call the post method from here not the controller
    public void addConsumable(String type, String name, String notes,Double price, Double quantity, LocalDate expiry) {

        PostRequest add=new PostRequest();
        add.PostRequestAdd(type, name, notes, price, quantity, expiry);
        updateList();

    }

    //no offset for remove
    //sorting not required
    //when ever this calls occurs get new list
    //when transporting json attach index to controller wont have t
    public void removeConsumable(int index) {

        PostRequest remove=new PostRequest();
        remove.PostRequestRemove(index);
        updateList();

    }


    public void writeToJson() {

        PostRequest close=new PostRequest();
        close.PostRequestClose();
    }




    @Override
    public Iterator<Consumable> iterator() {
        return items.iterator();
    }

    public int getSize() {

        return items.size();

    }

    public Consumable getItem(int index) {

        if (index <= getSize() && index >= 0) {

            return items.get(index);

        }

        return null;
    }


}
