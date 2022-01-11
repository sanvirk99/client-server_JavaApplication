package ca.cmpt213.a4.client.view;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.*;

/**
 * Responsible for the view of the dialog window when add Item is clicked
 * Organizes Jlabels, Jbuttons, Datepicker and textfield objects using grouplayout
 * Figuring out the aliment was done using many online resources these are the most dependent on
 * https://docs.oracle.com/javase/tutorial/uiswing/layout/group.html
 * https://ssaurel.medium.com/learn-to-make-a-mvc-application-with-swing-and-java-8-3cd24cf7cb10
 */
public class DialogView extends JDialog {
    // DialogView uses Swing framework to display UI to user

    private JDialog frame;


    private JLabel typeLabel;
    private JLabel nameLabel;
    private JLabel notesLabel;
    private JLabel dateLabel;
    private JLabel priceLabel;
    private JLabel quantityLabel;
    private JLabel addLabel;
    private JButton addButton;

    private JTextField nameTextField;
    private JTextField notesTextField;
    private JTextField priceTextField;
    private JTextField quantityTextField;
    private DatePicker datePicker;
    private String[] itemsComboBox = {"Drink", "Food"};
    private String[] quantityLabels = {"Volume :", "Weight :"};
    private JComboBox<String> comboBox;


    public DialogView(String title) {
        frame = new JDialog();
        frame.setTitle(title);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Create UI elements
        typeLabel = new JLabel("Type :");
        nameLabel = new JLabel("Name :");
        notesLabel = new JLabel("Notes :");
        priceLabel = new JLabel("Price :");
        quantityLabel = new JLabel("Volume :");
        dateLabel = new JLabel("Expiry Date :");
        addLabel = new JLabel("Press to Add :");
        addButton = new JButton("Add");
        comboBox = new JComboBox<>(itemsComboBox);
        comboBox.setActionCommand(comboBox.getItemAt(comboBox.getSelectedIndex()));
        datePicker = new DatePicker();
        datePicker.setDateToToday();
        nameTextField = new JTextField();
        notesTextField = new JTextField();
        priceTextField = new JTextField();
        quantityTextField = new JTextField();


        GroupLayout layout = new GroupLayout(frame.getContentPane());
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(typeLabel).addComponent(nameLabel)
                        .addComponent(notesLabel).addComponent(priceLabel).addComponent(quantityLabel).addComponent(dateLabel).addComponent(addLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(comboBox).addComponent(nameTextField)
                        .addComponent(notesTextField).addComponent(priceTextField).addComponent(quantityTextField).addComponent(datePicker).addComponent(addButton))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(typeLabel)
                        .addComponent(comboBox))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(nameLabel)
                        .addComponent(nameTextField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(notesLabel)
                        .addComponent(notesTextField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(priceLabel)
                        .addComponent(priceTextField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(quantityLabel)
                        .addComponent(quantityTextField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(dateLabel)
                        .addComponent(datePicker))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(addLabel)
                        .addComponent(addButton))
        );

        frame.getContentPane().setLayout(layout);
    }

    public JDialog getFrame() {
        return frame;
    }

    public JLabel getTypeLabel() {
        return typeLabel;
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public JLabel getNotesLabel() {
        return notesLabel;
    }

    public JLabel getDateLabel() {
        return dateLabel;
    }

    public JLabel getPriceLabel() {
        return priceLabel;
    }

    public JLabel getQuantityLabel() {
        return quantityLabel;
    }

    public JLabel getAddLabel() {
        return addLabel;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public JTextField getNotesTextField() {
        return notesTextField;
    }

    public JTextField getPriceTextField() {
        return priceTextField;
    }

    public JTextField getQuantityTextField() {
        return quantityTextField;
    }

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public String[] getItemsComboBox() {
        return itemsComboBox;
    }

    public JComboBox<String> getComboBox() {
        return comboBox;
    }

    public String[] getQuantityLabels() {
        return quantityLabels;
    }
}


