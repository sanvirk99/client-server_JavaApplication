package ca.cmpt213.a4.webappserver.controllers;


import ca.cmpt213.a4.webappserver.control.*;
import ca.cmpt213.a4.webappserver.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/***
 * Control response to Rest messages
 */
@RestController
public class DataController {

    private Database list = new Database("FoodListA2.json");

    @GetMapping("/ping")
    public String getHelloMessage() {
        return "System is up!";
    }

    @GetMapping("/exit")
    public void save() {

        list.writeToJson();
    }


    @GetMapping("/listAll")
    public ArrayList<Consumable> listAll() {

        return list.items;

    }

    @GetMapping("/listExpired")
    public ArrayList<Consumable> listExpired() {
        return list.listExpired();

    }

    @GetMapping("/listNotExpired")
    public ArrayList<Consumable> listNotExpired() {
        return list.listNotExpired();
    }

    @GetMapping("/listExpiringIn7Days")
    public ArrayList<Consumable> listExpiring7Days() {
        return list.listExpiring7Days();
    }


    @PostMapping("/addItem")
    public ArrayList<Consumable> addItem(
            @RequestBody InputItem obj
    ) {
        LocalDate date = LocalDate.parse(obj.getDate());

        list.addConsumable(obj.getType(), obj.getName(), obj.getNotes(),
                obj.getPrice(), obj.getQuantity(), date);

        return list.items;

    }

    //will be dependent on my indicator wiht button click
    @PostMapping("/removeItem")
    public ArrayList<Consumable> removeItem(
            @RequestBody ItemIndex toDelete
    ) {
        //System.out.println("index to delete " + toDelete.getIndex());
        list.removeConsumable(toDelete.getIndex());
        return list.items;

    }


    // Create Exception Handle
    @ResponseStatus(value = HttpStatus.BAD_REQUEST,
            reason = "Request ID not found.")
    @ExceptionHandler(IllegalArgumentException.class)
    public void badIdExceptionHandler() {
        // Nothing to do
    }
}