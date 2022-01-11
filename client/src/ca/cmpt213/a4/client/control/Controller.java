package ca.cmpt213.a4.client.control;


import ca.cmpt213.a4.client.model.Consumable;
import ca.cmpt213.a4.client.model.Database;
import ca.cmpt213.a4.client.model.GetRequest;
import ca.cmpt213.a4.client.view.MainView;
import ca.cmpt213.a4.client.view.DialogView;
import com.google.gson.JsonArray;

import javax.swing.*;

/**
 * Responsible for the visual changes of the MainView containing the scrollPane
 * Implements action listeners to initiate  model and view operations
 * refreshes list on button clicks
 *
 * @parm MainView needs to control the scroll-pane view and implement action listeners
 * @parm Database needs to communicate what the user desires with the model
 * Figuring out the Control methodology was done using many online resources this is the most dependent on
 * https://ssaurel.medium.com/learn-to-make-a-mvc-application-with-swing-and-java-8-3cd24cf7cb10
 */
public class Controller {

    // private MainView;
    private String listType = null;
    private Database model;
    private JsonArray list;
    private MainView view;
    private DialogView dialog;
    private Object WindowListener;


    public Controller(Database model, MainView view) {

        this.view = view;
        this.model = model;
    }


    public void initController() {
        listAllItems();//start of program
        view.getAll().addActionListener(e -> listAllItems());
        view.getAdd().addActionListener(e -> addItemDialog());
        view.getExpired().addActionListener(e -> listExpired());
        view.getNotExpired().addActionListener(e -> listNotExpired());
        view.getExpiring().addActionListener(e -> listExpiring());

        view.getFrame().addWindowListener(new java.awt.event.WindowAdapter() {
            //https://stackoverflow.com/questions/9093448/how-to-capture-a-jframes-close-button-click-event
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(view.getFrame(),
                        "Are you sure you want to close this window? \n Items will be Saved to Server", "Close Window?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    model.writeToJson();
                    System.exit(0);
                }
            }


        });


    }

    //this is were my command will go for listing items
    public void listAllItems() {
        model.updateList();
        view.getPanels().clear();
        view.getContainer().removeAll();
        view.getRemoveButtons().clear();


        int i = 0;
        for (Consumable item : model) {
            JTextArea description = new JTextArea(item.toString());

            JPanel panel = new JPanel();
            String number = "Item #" + (i + 1) + " (" + item.getType() + ")";
            panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), number));

            JButton button = new JButton("Remove");
            button.setActionCommand(String.valueOf(i));
            button.addActionListener(e -> {

                String x = e.getActionCommand();
                Integer y = Integer.valueOf(x);
                model.removeConsumable(y);
                listAllItems();
                JOptionPane.showMessageDialog(view.getFrame(), "Item Removed");

            });
            panel.add(description);
            panel.add(button);
            view.getRemoveButtons().add(button);
            panel.setVisible(true);
            view.getPanels().add(panel);
            view.getContainer().add(view.getPanels().get(i));
            i++;

        }
        if (i == 0) {

            noItems();
        }

        view.getFrame().revalidate();
        view.getFrame().repaint();
    }

    public void listExpired() {
        model.updateList();
        view.getPanels().clear();
        view.getContainer().removeAll();
        view.getRemoveButtons().clear();
        GetRequest serverList = new GetRequest();

        int i = 0;
        int loop = 0;
        for (Consumable item : model) {

            if (item.getExpiryDays() < 0) {
                JTextArea description = new JTextArea(item.toString());
                JPanel panel = new JPanel();
                String number = "Item #" + (i + 1) + " (" + item.getType() + ")";
                panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), number));

                JButton button = new JButton("Remove");
                button.setActionCommand(String.valueOf(loop));
                button.addActionListener(e -> {

                    String x = e.getActionCommand();
                    Integer y = Integer.valueOf(x);
                    model.removeConsumable(y);
                    listExpired();
                    JOptionPane.showMessageDialog(view.getFrame(), "Item Removed");
                });
                panel.add(description);
                panel.add(button);
                view.getRemoveButtons().add(button);
                panel.setVisible(true);
                view.getPanels().add(panel);
                view.getContainer().add(view.getPanels().get(i));
                i++;
            }
            loop++;
        }
        if (i == 0) {

            noItems();
        }
        view.getFrame().revalidate();
        view.getFrame().repaint();

    }

    public void listNotExpired() {
        model.updateList();
        view.getPanels().clear();
        view.getContainer().removeAll();
        view.getRemoveButtons().clear();

        int i = 0;
        int loop = 0;
        for (Consumable item : model) {

            if (item.getExpiryDays() >= 0) {
                JTextArea description = new JTextArea(item.toString());
                JPanel panel = new JPanel();
                String number = "Item #" + (i + 1) + " (" + item.getType() + ")";
                panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), number));

                JButton button = new JButton("Remove");
                button.setActionCommand(String.valueOf(loop));
                button.addActionListener(e -> {

                    String x = e.getActionCommand();
                    Integer y = Integer.valueOf(x);
                    model.removeConsumable(y);
                    listNotExpired();
                    JOptionPane.showMessageDialog(view.getFrame(), "Item Removed");

                });
                panel.add(description);
                panel.add(button);
                view.getRemoveButtons().add(button);
                panel.setVisible(true);
                view.getPanels().add(panel);
                view.getContainer().add(view.getPanels().get(i));
                i++;
            }
            loop++;
        }
        if (i == 0) {

            noItems();
        }
        view.getFrame().revalidate();
        view.getFrame().repaint();


    }

    public void listExpiring() {
        model.updateList();
        view.getPanels().clear();
        view.getContainer().removeAll();
        view.getRemoveButtons().clear();

        int i = 0;
        int loop = 0;
        for (Consumable item : model) {

            if (item.getExpiryDays() >= 0 && item.getExpiryDays() <= 7) {
                JTextArea description = new JTextArea(item.toString());
                JPanel panel = new JPanel();
                String number = "Item #" + (i + 1) + " (" + item.getType() + ")";
                panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), number));

                JButton button = new JButton("Remove");
                button.setActionCommand(String.valueOf(loop));
                button.addActionListener(e -> {

                    String x = e.getActionCommand();
                    Integer y = Integer.valueOf(x);
                    model.removeConsumable(y);
                    listExpiring();
                    JOptionPane.showMessageDialog(view.getFrame(), "Item Removed");

                });
                panel.add(description);
                panel.add(button);
                view.getRemoveButtons().add(button);
                panel.setVisible(true);
                view.getPanels().add(panel);
                view.getContainer().add(view.getPanels().get(i));
                i++;
            }
            loop++;
        }
        if (i == 0) {

            noItems();
        }
        view.getFrame().revalidate();
        view.getFrame().repaint();

    }

    public void noItems() {

        JTextArea description = new JTextArea("no item to show,list empty or server is not running");
        JPanel panel = new JPanel();
        panel.add(description);
        panel.setVisible(true);
        view.getContainer().add(panel);


    }


    public void addItemDialog() {

        if (this.dialog != null) {

            this.dialog.getFrame().setVisible(false);//in case add is clicked again main frame
        }
        dialog = new DialogView("Add Item");
        DialogControl control = new DialogControl(dialog, model, this);
        control.inaction();
        listAllItems();

    }


}
