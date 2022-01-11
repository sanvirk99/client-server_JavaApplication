package ca.cmpt213.a4.client;


import ca.cmpt213.a4.client.control.*;
import ca.cmpt213.a4.client.model.*;
import ca.cmpt213.a4.client.view.*;


import javax.swing.*;

/**
 * Start of the program main method outside the three MVC packages invoked with SwingUtilities.
 */
public class Main {

    public static void main(String[] args) {


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Database model = new Database();
                MainView view = new MainView();
                Controller c = new Controller(model, view);
                c.initController();
            }

        });

    }
}
