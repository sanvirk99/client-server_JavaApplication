package ca.cmpt213.a4.webappserver.control;

import ca.cmpt213.a4.webappserver.model.*;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Read and write to Json file
 * Store Consumable in sorted list
 */
public class Database implements Iterable<Consumable> {

    //made public design decision
    public ArrayList<Consumable> items = new ArrayList<>();
    private String filename;

    public Database(String args) {
        filename = args;
        try {

            JsonElement fileElement = JsonParser.parseReader(new FileReader(filename));
            JsonArray jsonFoodArray = fileElement.getAsJsonArray();

            for (JsonElement foodElement : jsonFoodArray) {

                JsonObject obj = foodElement.getAsJsonObject();
                LocalDate date = LocalDate.parse(obj.get("expiryDate").getAsString());
                String name = obj.get("name").getAsString();
                String notes = obj.get("notes").getAsString();
                double quantity = obj.get("quantity").getAsDouble();
                double price = obj.get("price").getAsDouble();


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


        } catch (FileNotFoundException e) {


        }


    }


    public boolean addConsumable(String type, String name, String notes, Double price, Double quantity, LocalDate expiry) {

        try {

            switch (type) {

                case "Food":
                    items.add(new Food(name, notes, price, quantity, expiry));
                    break;
                case "Drink":
                    items.add(new Drink(name, notes, price, quantity, expiry));
                    break;
                default:
                    return false;
            }

            items.sort(Consumable::compareTo);
            return true;

        } catch (Exception e) {


            return false;
        }

    }

    //no offset for remove
    //sorting not required
    //index to remove curl cmd error check
    public void removeConsumable(int index) {


        try {

            items.remove(index);

        } catch (IndexOutOfBoundsException e) {

        }


    }


    public void writeToJson() {


        Gson myGson = new GsonBuilder().registerTypeAdapter(LocalDate.class,
                new TypeAdapter<LocalDate>() {
                    @Override
                    public void write(JsonWriter jsonWriter,
                                      LocalDate localDate) throws IOException {
                        jsonWriter.value(localDate.toString());
                    }

                    @Override
                    public LocalDate read(JsonReader jsonReader) throws IOException {
                        return LocalDate.parse(jsonReader.nextString());
                    }
                }).setPrettyPrinting().create();


        String json = myGson.toJson(items);


        // referenced from https://www.w3schools.com/java/java_files_create.asp
        try {
            File myObj = new File(filename);
            myObj.createNewFile();

        } catch (IOException e) {
            System.out.println("An error occurred creating file.");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(json);
            myWriter.close();
            // System.out.println("Successfully wrote to the file.");

        } catch (IOException e) {
            System.out.println("An error occurred writing to file.");
            e.printStackTrace();
        }

        System.out.println("json file written or updated");
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

    public ArrayList<Consumable> listExpired() {

        ArrayList<Consumable> listExpired = new ArrayList<>();

        for (Consumable item : items) {

            if (item.getExpiryDays() < 0) {

                listExpired.add(item);

            }


        }


        return listExpired;
    }

    public ArrayList<Consumable> listNotExpired() {

        ArrayList<Consumable> listNotExpired = new ArrayList<>();

        for (Consumable item : items) {

            if (item.getExpiryDays() >= 0) {

                listNotExpired.add(item);

            }


        }
        return listNotExpired;
    }

    public ArrayList<Consumable> listExpiring7Days() {

        ArrayList<Consumable> listNotExpired = new ArrayList<>();

        for (Consumable item : items) {

            if (item.getExpiryDays() >= 0 && item.getExpiryDays() <= 7) {

                listNotExpired.add(item);

            }


        }
        return listNotExpired;
    }

}
