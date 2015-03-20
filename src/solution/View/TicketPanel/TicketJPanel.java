package solution.View.TicketPanel;

import scotlandyard.Colour;
import scotlandyard.Ticket;
import solution.Controller.Controller;
import solution.Controller.Interfaces.ControllerButtonListener;
import solution.Model.GamePlayer.GamePlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by MikeCooper on 17/03/15.
 */
public class TicketJPanel extends JPanel implements ActionListener {


    private GridBagConstraints gbc;

    protected Set<DetectiveJPanel> detectives;
    protected MrXJPanel mrX;
    protected JButton GoButton;
    protected ControllerButtonListener controllerButtonListener;

    public TicketJPanel() {

        GoButton = new JButton("Go");
        GoButton.addActionListener(this);


        detectives = new HashSet<DetectiveJPanel>();
        gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());
    }

    public void addPlayer(Colour player, Map<Ticket, Integer> tickets) {
        if (player.name().equals("Black")) {
            mrX = new MrXJPanel("Black");
        } else {
            detectives.add(new DetectiveJPanel(player.name()));

        }
    }

    public void updateTickets(Colour player, Map<Ticket, Integer> tickets) {
        if (player.name().equals("Black")) {
            mrX.setTicketValue(tickets);
        } else {
            for (DetectiveJPanel detective : detectives) {
                if (player.name().equals(detective.PlayerName.getText())) {
                    detective.setTicketValue(tickets);
                }
            }
        }
    }

    public void addPlayersToJPanel() {
        int x = 0;
        for (DetectiveJPanel detective : detectives) {
            setGridLayout(x, 0, detective, 0);
            x++;
        }
        if (mrX != null) {
            setGridLayout(x, 0, mrX, 0);
        }
        setGridLayout(x + 2, 0, GoButton, 0);
    }

    protected void setGridLayout(int x, int y, Component component, int buffer) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(buffer, 0, 0, 0);

        add(component, gbc);
    }

    public void setControllerPrivileges(Controller controller) {
        for (DetectiveJPanel detective : detectives) {
            detective.buttonListener(controller);
        }
        if (mrX != null) {
            mrX.buttonListener(controller);
        }
        buttonListener(controller);
    }

    public void setCurrentPlayer(Colour currentPlayer) {
        for (DetectiveJPanel detective : detectives) {
            buttonCheck(detective, currentPlayer);
        }
        buttonCheck(mrX, currentPlayer);
    }

    public void buttonCheck(TemplatePlayerJPanel player, Colour currentPlayer) {
        if (currentPlayer.name().equals(player.PlayerName.getText())) {
            player.activateDeactivateButtons(true);
        } else {
            player.activateDeactivateButtons(false);
        }
    }
    //not used.
    public void activateSpecificButtons (String buttonName, Boolean b, Colour currentPlayer){
        for (DetectiveJPanel detective : detectives) {
            if (currentPlayer.name().equals(detective.PlayerName.getText()))
                detective.activateDeactivateSpecificButtons(buttonName,b);
        }
        if(currentPlayer.name().equals(mrX.PlayerName.getText())){
            mrX.activateDeactivateSpecificButtons(buttonName,b);
        }
    }



    public void buttonListener(ControllerButtonListener controllerButtonListener){
        this.controllerButtonListener = controllerButtonListener;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == GoButton){
            System.out.println("button clicked");

            controllerButtonListener.goButtonUsed("GOBUTTON");
        }
    }
}
