import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreateAccount  {
    DatabaseLayerAdmin db;
    private JPanel contentPanel;
    private JButton buttonSumbit;
    private JButton buttonCancel;
    private JTextField nameField;
    private JTextField lastNameField;
    private JTextField userNameField;
    private JPasswordField passwordField;
    private JLabel fillGaps;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JLabel userNameText;

    public CreateAccount() {
        db = new DatabaseLayerAdmin();
        JFrame frame = new JFrame("Kayıt Ekranı");
        frame.setSize(550, 400);
        frame.add(contentPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        buttonSumbit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                db = new DatabaseLayerAdmin();
                    if((nameField.getText().equals(""))||(lastNameField.getText().equals("")) || (userNameField.getText().equals(""))
                    || passwordField.getText().equals("") || !maleRadioButton.isSelected() && !femaleRadioButton.isSelected()){
                        fillGaps.setText("Please fill all blanks !");
                        fillGaps.setBackground(Color.red);
                        fillGaps.setVisible(true);

                    }else{
                        if(maleRadioButton.isSelected()){
                            Kullanci user = new Admin(nameField.getText(), lastNameField.getText(), userNameField.getText(), passwordField.getText(), Kullanci.Cinsiyet.erkek);
                            db.insertAdmin(user);

                        }else{
                            Kullanci user = new Admin(nameField.getText(), lastNameField.getText(), userNameField.getText(), passwordField.getText(), Kullanci.Cinsiyet.kadin);
                            db.insertAdmin(user);
                        }
                        fillGaps.setVisible(false);
                        JOptionPane.showMessageDialog(null, "Hesap başarılı bir şekilde oluşturuldu.");
                        frame.dispose();
                        UserLogin login = new UserLogin();

                    }
            }
        });
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UserLogin login = new UserLogin();
                frame.dispose();
            }
        });

    }

}
