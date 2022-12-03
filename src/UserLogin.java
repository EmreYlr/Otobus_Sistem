import javax.swing.*;
import java.awt.event.*;

public class UserLogin{
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;

    private JPasswordField passwordPF;
    private JTextField usernameTF;
    private JLabel Username;
    private JLabel Password;
    JFrame frame;
    public UserLogin() {
        frame = new JFrame("");
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

        usernameTF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }

    private void onOK() {
        System.out.println(usernameTF.getText() + "" + passwordPF.getText());
        if (usernameTF.getText().equals("oguzhan") && passwordPF.getText().equals("annen")){
            CreateAccount createAccount = new CreateAccount();
            frame.dispose();
        }
    }

    private void onCancel() {
    }

    public static void main(String[] args) {
        UserLogin userLogin = new UserLogin();
    }
}