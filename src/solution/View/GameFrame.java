package solution.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by gebruiker on 23/03/2015.
 */
public class GameFrame extends JFrame{


    public GameFrame(View view,InitFrame initFrame){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 800);
        setBackground();
        this.add(view, CENTER_ALIGNMENT);
        this.add(initFrame, CENTER_ALIGNMENT);
        setVisible(true);
    }





    private void setBackground(){
        Image img = null;
        try
        {
            img = ImageIO.read(new File("background.jpg"));
        }
        catch( IOException e )
        {
            System.out.println(e);
        }

        //ImageIcon background = new ImageIcon("background.jpg");
        assert img != null;
        Image scaleImage = img.getScaledInstance(1500, 1000, Image.SCALE_SMOOTH);
        ImageIcon background = new ImageIcon(scaleImage);
        setContentPane(new JLabel(background));
    }

}
