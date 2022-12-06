import javax.swing.*;
import java.awt.event.*;

public class UserUpdateScreen extends JDialog {
    private JPanel contentPane;
    private JTextField tcField;
    private JButton getirButton;
    private JTextField isimField;
    private JRadioButton erkekRadioButton;
    private JRadioButton kadinRadioButton;
    private JButton duzenleButton;
    private JTextField telefonField;
    private JTextField soyadField;
    private JButton buttonOK;
    private JButton buttonCancel;
    JFrame frame;
    public UserUpdateScreen() {
        frame = new JFrame("Kullanıcı Düzenle");
        frame.setSize(400, 300);
        frame.add(contentPane);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    public static void main(String[] args){
        UserUpdateScreen userupdatescreen = new UserUpdateScreen();
    }

}
