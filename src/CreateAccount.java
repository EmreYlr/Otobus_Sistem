import model.Yolcu;

import javax.swing.*;
import java.awt.event.*;

public class CreateAccount  {
    DatabaseLayer db;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nameField;
    private JTextField lastNameField;
    private JTextField phoneNumberField;
    private JPasswordField passwordField;

    public CreateAccount() {
        db = new DatabaseLayer();
        JFrame frame = new JFrame("");
        frame.setSize(550, 400);
        frame.add(contentPane);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });



        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        Yolcu user = new Yolcu(nameField.getText(), lastNameField.getText(), phoneNumberField.getText(), passwordField.getText());
        db.insertYolcu(user);
    }

    private void onCancel() {
        // add your code here if necessary

    }
}
