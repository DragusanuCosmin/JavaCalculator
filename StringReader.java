import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StringReader extends JPanel {
    private JLabel label;
    private JTextField textField;
    private String userInput;

    public StringReader() {
        setLayout(new BorderLayout());
        label = new JLabel("Function of type y=function(x):");
        textField = new JTextField();
        setBackground(Color.BLACK);
        label.setForeground(Color.white);
        textField.setForeground(Color.white);
        textField.setBackground(Color.BLACK);
        textField.setForeground(Color.white);
        JButton Submit = new JButton("Submit");
        Submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userInput = textField.getText();
            }
        });
        add(label, BorderLayout.WEST);
        add(textField, BorderLayout.CENTER);
        add(Submit, BorderLayout.EAST);
    }

    public String getUserInput() {
        return userInput;
    }
}