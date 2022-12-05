import model.Sehir;

import javax.swing.*;
import java.awt.event.*;

public class UserMainScreen extends JDialog {
    private JPanel contentPane;
    private JComboBox fromBox;
    private JComboBox toBox;
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
    private JButton saveButton;
    private JLabel fillError;
    private JRadioButton a18RadioButton;
    private JRadioButton a19RadioButton;
    private JRadioButton a20RadioButton;
    private JRadioButton a22RadioButton;
    private JRadioButton a24RadioButton;
    private JRadioButton a25RadioButton;
    private JRadioButton a28RadioButton;
    private JRadioButton a26RadioButton;
    private JRadioButton a29RadioButton;
    private JRadioButton a30RadioButton;
    private JRadioButton a32RadioButton;
    private JRadioButton a33RadioButton;
    private JRadioButton a34RadioButto;
    private JRadioButton a36RadioButton;
    private JRadioButton a37RadioButton;
    private JRadioButton a38RadioButton;
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
        String temp = "";



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
                DatabaseLayer db = new DatabaseLayer();
                Sehir s1 = new Sehir(34,"İstanbul");
                Sehir s2 = new Sehir(63,"Urfa");
                if(fromBox.getSelectedItem().equals("İstanbul") && toBox.getSelectedItem().equals("Urfa")){
                    fillError.setVisible(false);
                    db.getSefer("İstanbul", "Urfa",s1, list1);
                }else if(fromBox.getSelectedItem().equals("Urfa") && toBox.getSelectedItem().equals("İstanbul")){
                    fillError.setVisible(false);
                    db.getSefer("Urfa", "İstanbul",s2, list1);
                }else if(fromBox.getSelectedItem().equals("Urfa") && toBox.getSelectedItem().equals("Urfa")){
                    fillError.setText("Lütfen Değerleri Farklı Seçiniz!");
                    fillError.setVisible(true);
                }else if(fromBox.getSelectedItem().equals("İstanbul") && toBox.getSelectedItem().equals("İstanbul")){
                    fillError.setText("Lütfen Değerleri Farklı Seçiniz!");
                    fillError.setVisible(true);
                }else{
                    fillError.setText("Bilinmeyen Bir Hata OLuştu");
                    fillError.setVisible(true);
                }
                //ImageIcon a = new ImageIcon("/Users/emre/IdeaProjects/odev/src/female.png");
                //a1RadioButton.setIcon(a);
                //Sefer s = new Sefer(2,1,6,63,14,15,"Emre");
                //DatabaseLayer db = new DatabaseLayer();
                //db.instertSefer(s);
                //a1RadioButton.setOpaque(true);
                //a1RadioButton.setBorderPainted(false);
                //a1RadioButton.setBackground(Color.red);
            }
        });
        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String temp = (String) list1.getSelectedValue();
                //System.out.println(temp.charAt(0));
                DatabaseLayer db = new DatabaseLayer();
                int[] a = db.getOtobus(1);
                System.out.println(a[0] + " " + a[1]);

            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    public static void main(String[] args){
        UserMainScreen usermainscreen = new UserMainScreen();
    }
}
