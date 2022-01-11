package ca.cmpt213.a4.client.control;


import ca.cmpt213.a4.client.view.*;
import ca.cmpt213.a4.client.model.*;

import javax.swing.*;
import java.time.LocalDate;

/**
 * Controls the inputs and visual changes of the DialogView
 * Implements action listeners, user-input and checks error in user input
 */
public class DialogControl {
    private DialogView dialog;
    private Database model;
    private Controller mainControl;

    public DialogControl(DialogView dialog, Database model, Controller mainControl){

        this.dialog=dialog;
        this.model=model;
        this.mainControl=mainControl;

    }

    public void inaction(){
        dialog.getComboBox().setActionCommand(dialog.getComboBox().getItemAt(dialog.getComboBox().getSelectedIndex()));
        dialog.getComboBox().addActionListener(ActionListener -> {

            dialog.getQuantityLabel().setText(dialog.getQuantityLabels()[dialog.getComboBox().getSelectedIndex()]);
            dialog.getComboBox().setActionCommand(dialog.getComboBox()
                    .getItemAt(dialog.getComboBox().getSelectedIndex()));

        });
        dialog.getAddButton().addActionListener(e-> addItemConsumable());


    }

    public void addItemConsumable(){

        String type=null;
        String name="";
        String notes=null;
        Double price=null;
        Double quantity=null;
        LocalDate date=LocalDate.now();

        boolean exception=false;
        //error checking
        try {
            type = dialog.getComboBox().getItemAt(dialog.getComboBox().getSelectedIndex());
            name = dialog.getNameTextField().getText();
            notes = dialog.getNotesTextField().getText();
            price = Double.parseDouble(dialog.getPriceTextField().getText());
            quantity = Double.parseDouble(dialog.getQuantityTextField().getText());
           date = dialog.getDatePicker().getDate();
           if(name.isEmpty()){
               throw new Exception("empty name");
           }

        }catch(Exception e){


            exception=true;
            JOptionPane.showMessageDialog(this.dialog.getFrame(),"One or more entry Invalid\n" +
                    "1.Enter number for numeric field\n" +
                    "2.Name can't be empty\n"+
                    "3.Incorrect Date Format\n" +
                    "  Select date by clicking button beside Date field\n" +
                    "\nPlease Enter again");

        }

        if(exception==false) {


            model.addConsumable(type, name, notes, price, quantity, date);
            mainControl.listAllItems();
            JOptionPane.showMessageDialog(this.dialog.getFrame(),"Item Added");

        }

    }







}
