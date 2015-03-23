package solution.View;

import solution.Controller.Interfaces.ControllerButtonListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by gebruiker on 23/03/2015.
 */
public class InitFrame extends JPanel implements ActionListener{
    private JButton buttonPlay = new JButton("play");
    private JButton buttonPlus = new JButton("+");
    private JButton buttonMinus = new JButton("-");
    private JLabel numberOfPlayers = new JLabel("3");

    private GridBagConstraints gbc = new GridBagConstraints();
    protected ControllerButtonListener controllerButtonListener;

    public InitFrame(){
        //setBackground();
        setSize(1300, 800);
        setLayout(new GridBagLayout());
        buttonPlay.addActionListener(this);
        buttonMinus.addActionListener(this);
        buttonPlus.addActionListener(this);


        setPanelLayout();

        this.setOpaque(false);

        setVisible(true);

        //controllerButtonListener.minusButtonPressed();

    }

    public void addListener(ControllerButtonListener controllerButtonListener){
        this.controllerButtonListener = controllerButtonListener;
    }

//    private void setBackground(){
//        Image img = null;
//        try
//        {
//            img = ImageIO.read(new File("background.jpg"));
//        }
//        catch( IOException e )
//        {
//            System.out.println(e);
//        }
//
//        //ImageIcon background = new ImageIcon("background.jpg");
//        assert img != null;
//        Image scaleImage = img.getScaledInstance(1500, 1000, Image.SCALE_SMOOTH);
//        ImageIcon background = new ImageIcon(scaleImage);
//        setContentPane(new JLabel(background));
//    }


    public void actionPerformed(ActionEvent e){

        Object source = e.getSource();

        if(source == buttonPlay){
            controllerButtonListener.playButtonPressed();
        }
        else if(source == buttonMinus){
            controllerButtonListener.minusButtonPressed();
        }
        else if(source == buttonPlus){
            controllerButtonListener.plusButtonPressed();
        }


    }


    private void setPanelLayout(){
        setGridLayout(0, 1, buttonMinus, 0);
        setGridLayout(1, 1, buttonPlay, 0);
        setGridLayout(2, 1, buttonPlus, 0);
        setGridLayout(1, 0, numberOfPlayers, 0);
    }

    protected void setGridLayout(int x, int y, Component component, int buffer) {
        if(component instanceof JLabel) { gbc.fill = GridBagConstraints.NORTH;}
        else {gbc.fill = GridBagConstraints.HORIZONTAL;}
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(0,buffer,0,buffer);

        add(component, gbc);
    }


    public void setNumberOfPlayers(int n) {
        this.numberOfPlayers.setText(String.valueOf(n));
    }

    public int getNumberOfPlayers() {
        return Integer.parseInt(numberOfPlayers.getText());
    }

    public void setVisible(){
        setVisible(true);
    }

    public void setInvisible(){
        setVisible(false);
    }

}
