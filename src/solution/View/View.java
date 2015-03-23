package solution.View;

import scotlandyard.Colour;
import scotlandyard.Move;
import scotlandyard.MoveDouble;
import scotlandyard.Ticket;
import solution.Controller.Controller;
import solution.View.MapPanel.MapJPanel;
import solution.View.TicketPanel.TicketJPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by MikeCooper on 16/03/15.
 */
public class View extends JFrame {

    private GridBagConstraints gbc;

    private MapJPanel mapJPanel;
    private TicketJPanel ticketJPanel;



    public View() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 800);

        ticketJPanel = new TicketJPanel();
        mapJPanel = new MapJPanel();

        //manual image size
        Dimension d = new Dimension(1200,600);
        mapJPanel.setMaximumSize(d);
        mapJPanel.setMinimumSize(d);
        mapJPanel.setPreferredSize(d);

        gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());

       //todo background
       // loadBackgroundImage();
        setJFrameLayout();


        //pack();

        setVisible(true);
    }


    private void setJFrameLayout() {
        setMapPanel();
        setTicketPanel();
    }

    private void setMapPanel() {

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(mapJPanel, gbc);
    }

    private void setTicketPanel() {
        gbc.anchor = GridBagConstraints.PAGE_END;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(ticketJPanel, gbc);
    }

    private void loadBackgroundImage() {


        BufferedImage background = null;
        try
        {
            background = ImageIO.read(new File("background.jpg"));
        }
        catch( IOException e )
        {
            System.out.println(e);
        }
        this.setContentPane(new ImagePanel(background));

}

    public void setControllerPrivileges(Controller controller) {
        //allow controller to access certain parts -
        ticketJPanel.setControllerPrivileges(controller);
        mapJPanel.buttonListener(controller);
    }

    //added to global variables.
    public void addPlayer(Colour player, Map<Ticket, Integer> tickets) {
        ticketJPanel.addPlayer(player, tickets);
    }

    //adds to j panel
    public void setPlayerPanel() {
        ticketJPanel.addPlayersToJPanel();
    }

    public void setCurrentPlayer(Colour player) {
        ticketJPanel.setCurrentPlayer(player);
    }


    public void updateTicketValues(Colour player, Map<Ticket, Integer> tickets) {
        ticketJPanel.updateTickets(player, tickets);
    }


    //----------------------


    public void activateSpecificButtonsPanel (String buttonName, Boolean b, Colour currentPlayer){
        ticketJPanel.activateSpecificButtons(buttonName,b,currentPlayer);
    }

    public void activateSpecificButtonsMap (String nodeName, Boolean b){
        mapJPanel.activateDeactivateSpecificButtons(nodeName,b);
    }

    public void activateAllButtonsMap(Boolean b){
        mapJPanel.activateDeactivateButtons(b);
    }


    class ImagePanel extends JComponent {
        private Image image;
        public ImagePanel(Image image) {
            this.image = image;
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }
    }




}



