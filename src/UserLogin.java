import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class UserLogin{
    private JPanel contentPanel;
    private JButton buttonSubmit;
    private JButton buttonCancel;
    private JPasswordField passwordPF;
    private JTextField usernameTF;
    private JLabel Username;
    private JLabel Password;
    private JLabel signUpHyperText;
    private JLabel fillGaps;
    JFrame frame;
    public UserLogin() {
        frame = new JFrame("Giriş Ekranı");
        frame.setSize(550, 400);
        frame.add(contentPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        signUpHyperText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CreateAccount createAccount = new CreateAccount();
                frame.dispose();
            }
        });

        buttonSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DatabaseLayerAdmin db = new DatabaseLayerAdmin();
                if(usernameTF.getText().equals("") || passwordPF.getText().equals("")){
                    fillGaps.setText("Please fill all blanks !");
                    fillGaps.setBackground(Color.red);
                    fillGaps.setVisible(true);
                }
                else{
                    if(db.checkLogin(usernameTF.getText(), passwordPF.getText())){
                        frame.dispose();
                        fillGaps.setVisible(false);
                        JOptionPane.showMessageDialog(null, "Giriş Başarılı");
                        UserMainScreen usermainscreen = new UserMainScreen();
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Hatalı Giriş!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        contentPanel.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        usernameTF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        signUpHyperText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                signUpHyperText.setText("<html><a href=''>" + "Need an account? Sign up" + "</a></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                signUpHyperText.setText("Need an account? Sign up");
            }
        });
    }
    private void onCancel() {
        frame.dispose();
    }

    public static void main(String[] args) {
        UserLogin userLogin = new UserLogin();
    }
}