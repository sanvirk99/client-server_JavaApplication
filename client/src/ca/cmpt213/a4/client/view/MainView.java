package ca.cmpt213.a4.client.view;


import ca.cmpt213.a4.client.model.Database;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Main window shown to user when application is started
 */
public class MainView extends JFrame {


    private JFrame frame = new JFrame("Items Tracker");

    private JList items;

    private Database data = new Database();

    private JButton all = new JButton("All");
    private JButton expired = new JButton("Expired");
    private JButton notExpired = new JButton("Not Expired");


    private JButton expiring = new JButton("Expiring in 7 days");
    private JButton add = new JButton("Add item");

    private JPanel container = new JPanel();
    private List<JPanel> panels = new ArrayList<JPanel>();
    private List<JButton> removeButtons = new ArrayList<>();
    private JScrollPane listScroll;


    public MainView() {

        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(all);
        buttons.add(expired);
        buttons.add(notExpired);
        buttons.add(expiring);
        buttons.add(add);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        listScroll = new JScrollPane(container);
        listScroll.setPreferredSize(new Dimension(600, 400));
        listScroll.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Consumable Items", TitledBorder.CENTER, TitledBorder.TOP));


        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setVisible(true);
        frame.add(buttons);
        frame.add(listScroll);
        frame.pack();


        //frame.setResizable(false);


    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }


    public JList getItems() {
        return items;
    }

    public void setItems(JList items) {
        this.items = items;
    }

    public JButton getAll() {
        return all;
    }

    public void setAll(JButton all) {
        this.all = all;
    }

    public JButton getExpired() {
        return expired;
    }

    public void setExpired(JButton expired) {
        this.expired = expired;
    }

    public JButton getNotExpired() {
        return notExpired;
    }

    public void setNotExpired(JButton notExpired) {
        this.notExpired = notExpired;
    }

    public JButton getExpiring() {
        return expiring;
    }

    public void setExpiring(JButton expiring) {
        this.expiring = expiring;
    }

    public JButton getAdd() {
        return add;
    }

    public void setAdd(JButton add) {
        this.add = add;
    }

    public JPanel getContainer() {
        return container;
    }

    public void setContainer(JPanel container) {
        this.container = container;
    }

    public List<JPanel> getPanels() {
        return panels;
    }

    public void setPanels(List<JPanel> panels) {
        this.panels = panels;
    }

    public JScrollPane getListScroll() {
        return listScroll;
    }

    public void setListScroll(JScrollPane listScroll) {
        this.listScroll = listScroll;
    }

    public List<JButton> getRemoveButtons() {
        return removeButtons;
    }
}
