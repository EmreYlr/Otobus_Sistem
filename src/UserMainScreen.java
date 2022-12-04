import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;

public class UserMainScreen extends JDialog {
    private JPanel contentPane;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton submitButton;
    private JRadioButton a12RadioButton;
    private JRadioButton a7RadioButton;
    private JRadioButton a3RadioButton;
    private JRadioButton a2RadioButton;
    private JRadioButton a6RadioButton;
    private JRadioButton a5RadioButton;
    private JRadioButton a4RadioButton;
    private JRadioButton a8RadioButton1;
    private JRadioButton a11RadioButton;
    private JRadioButton a10RadioButton;
    private JRadioButton a9RadioButton;
    private JRadioButton a17RadioButton;
    private JRadioButton a21RadioButton;
    private JRadioButton a23RadioButton;
    private JRadioButton a27RadioButton;
    private JRadioButton a31RadioButton;
    private JRadioButton a35RadioButton;
    private JRadioButton a1RadioButton;
    private JRadioButton a13RadioButton;
    private JRadioButton a14RadioButton;
    private JRadioButton a15RadioButton;
    private JRadioButton a16RadioButton;
    private JList list1;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox comboBox3;
    private JButton button1;
    private ButtonGroup otobusGrup2;
    private ButtonGroup otobusGrup;
    private JButton buttonOK;
    private JButton buttonCancel;


    public UserMainScreen() {
        JFrame frame = new JFrame("");

        frame.setSize(1000, 600);
        frame.add(contentPane);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        DefaultListModel model = new DefaultListModel<>();
        model.addElement("Ankara - Urfa 21/11/22 14:30-12:30");
        model.addElement("Ankara - Urfa 21/11/22 14:30-12:30");
        list1.setModel(model);

        list1.addListSelectionListener(e -> System.out.println(list1.getSelectedValue()));



        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args){
        UserMainScreen a = new UserMainScreen();
    }
}
