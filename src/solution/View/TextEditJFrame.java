package solution.View;

import solution.Controller.Interfaces.ControllerButtonListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by MikeCooper on 19/03/15.
 */
public class TextEditJFrame extends JFrame implements ActionListener{


        protected JTextField input;
        protected JTextArea output;

        protected GridBagConstraints gbc;
        protected ControllerButtonListener controllerButtonListener;

        public TextEditJFrame() {

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(1000, 400);

            input = new JTextField();
            input.setPreferredSize(new Dimension(400, 20));
            input.setMinimumSize(new Dimension(200, 20));

            output = new JTextArea("test");

            input.addActionListener(this);

            gbc = new GridBagConstraints();
            setLayout(new GridBagLayout());

            setLayout();
            pack();

            setVisible(true);
        }


        public void setOutputNewline (String value) {
            output.append("/n"+value);
        }

        public void setOutput (String value) {
            clearInput();
            output.setText("/n"+value);
        }

        public void clearInput () {
            input.setText("");
        }

        private void setLayout () {
            setInput();
            setOutput();
        }

        private void setInput() {
            gbc.anchor = GridBagConstraints.FIRST_LINE_START;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 0;
            add(input, gbc);
        }

        private void setOutput() {
            gbc.anchor = GridBagConstraints.FIRST_LINE_END;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(10,0,0,0);
            gbc.gridx = 0;
            gbc.gridy = 1;
            add(output, gbc);
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();

            if (source == input) {
                setOutput(input.getText());
                clearInput();
            }
        }
}
