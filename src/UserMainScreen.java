import model.Koltuk;
import model.Kullanci;
import model.Sehir;
import model.Yolcu;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserMainScreen extends JDialog {
    private JPanel contentPane;
    private JComboBox fromBox;
    private JComboBox toBox;
    private JButton submitButton;
    private JRadioButton a12;
    private JRadioButton a7;
    private JRadioButton a3;
    private JRadioButton a2;
    private JRadioButton a5;
    private JRadioButton a4;
    private JRadioButton a8;
    private JRadioButton a11;
    private JRadioButton a10;
    private JRadioButton a9;
    private JRadioButton a17;
    private JRadioButton a21;
    private JRadioButton a23;
    private JRadioButton a27;
    private JRadioButton a31;
    private JRadioButton a35;
    private JRadioButton a1;
    private JRadioButton a13;
    private JRadioButton a14;
    private JRadioButton a15;
    private JRadioButton a16;
    private JRadioButton a18;
    private JRadioButton a19;
    private JRadioButton a20;
    private JRadioButton a22;
    private JRadioButton a24;
    private JRadioButton a25;
    private JRadioButton a28;
    private JRadioButton a26;
    private JRadioButton a29;
    private JRadioButton a30;
    private JRadioButton a32;
    private JRadioButton a33;
    private JRadioButton a34;
    private JRadioButton a36;
    private JRadioButton a37;
    private JRadioButton a38;
    private JList seferList;
    private JTextField adField;
    private JTextField soyadField;
    private JComboBox cinsiyetBox;
    private JButton saveButton;
    private JLabel fillError;
    private JRadioButton a6;
    private JTextField telefonField;
    private JLabel kontrolField;
    private JPanel ticketPanel;
    private JPanel otobusPanel;
    private JButton exitField;
    private JButton kullaniciDuzenleButton;
    private JButton yeniSeferButton;
    private JTextField tcField;
    private JButton yenileButton;
    private JButton seferEkleButton;
    private ButtonGroup otobusGrup;
    int seferId;


    public UserMainScreen() {
        List<JRadioButton> list=new ArrayList<JRadioButton>();
        koltuk(list);
        JFrame frame = new JFrame("Otob??s Sistemi");
        frame.setSize(1000, 600);
        frame.add(contentPane);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


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
                DatabaseLayerUser db = new DatabaseLayerUser();
                Sehir s = new Sehir(34, "??stanbul");
                Sehir s1 = new Sehir(63, "Urfa");
                if(fromBox.getSelectedItem().equals("??stanbul") && toBox.getSelectedItem().equals("Urfa")){
                    fillError.setVisible(false);
                    db.getSefer("??stanbul", "Urfa", s,seferList);
                }else if(fromBox.getSelectedItem().equals("Urfa") && toBox.getSelectedItem().equals("??stanbul")){
                    fillError.setVisible(false);
                    db.getSefer("Urfa", "??stanbul",s1, seferList);
                }else if(fromBox.getSelectedItem().equals("Urfa") && toBox.getSelectedItem().equals("Urfa")){
                    fillError.setText("L??tfen De??erleri Farkl?? Se??iniz!");
                    fillError.setVisible(true);
                }else if(fromBox.getSelectedItem().equals("??stanbul") && toBox.getSelectedItem().equals("??stanbul")){
                    fillError.setText("L??tfen De??erleri Farkl?? Se??iniz!");
                    fillError.setVisible(true);
                }else{
                    fillError.setText("Bilinmeyen Bir Hata OLu??tu");
                    fillError.setVisible(true);
                }
            }
        });
        seferList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ImageIcon male = new ImageIcon("/Users/emre/IdeaProjects/odev/src/male.png");
                ImageIcon female = new ImageIcon("/Users/emre/IdeaProjects/odev/src/female.png");
                for (JRadioButton b: list){
                    b.setEnabled(true);
                }
                String temp = (String) seferList.getSelectedValue();
                String[] a = temp.split("\\s");
                seferId = Integer.parseInt(a[0]);
                DatabaseLayerUser db = new DatabaseLayerUser();
                HashMap<Integer, Integer> test = db.getOtobus(seferId);
                HashMap<Integer, String> test2 =  db.getCinsiyet(test);

                for (Integer i : test2.keySet()) {
                    if(test2.get(i).equals("erkek")){
                        for (JRadioButton b: list){
                            if((Integer.parseInt(b.getText())) == i){
                                b.setDisabledIcon(male);
                                b.setEnabled(false);
                            }
                        }
                    }else{
                        for (JRadioButton b: list){
                            if((Integer.parseInt(b.getText())) == i){
                                b.setDisabledIcon(female);
                                b.setEnabled(false);
                            }
                        }
                    }

                }
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseLayerUser db = new DatabaseLayerUser();
                if(seferList.isSelectionEmpty()){
                    kontrolField.setText("L??tfen Sefer Se??iniz!");
                    kontrolField.setVisible(true);
                }
                else if(seciliKoltuk(list) == 0){
                    kontrolField.setText("L??tfen Koltuk Numaras?? Se??iniz!");
                    kontrolField.setVisible(true);
                }
                else if(adField.getText().equals("") || soyadField.getText().equals("") || telefonField.getText().equals("") || tcField.getText().equals("") || cinsiyetBox.getSelectedItem() == null){
                    kontrolField.setText("L??tfen Bo??luklar?? Doldurunuz!");
                    kontrolField.setVisible(true);
                }
                else{
                    kontrolField.setVisible(false);
                    if(cinsiyetBox.getSelectedItem().equals("Erkek")){
                        Yolcu yolcu = new Yolcu(adField.getText(), soyadField.getText(),telefonField.getText(),null,null, tcField.getText(), Kullanci.Cinsiyet.erkek,null);
                        db.insertYolcu(yolcu);
                        Koltuk koltuk = new Koltuk(db.getYolcuId(yolcu), seferId, seciliKoltuk(list));
                        db.insertKoltuk(koltuk);
                    }
                    else {
                        Yolcu yolcu = new Yolcu(adField.getText(), soyadField.getText(),telefonField.getText(),null,null, tcField.getText(), Kullanci.Cinsiyet.kadin,null);
                        db.insertYolcu(yolcu);
                        Koltuk koltuk = new Koltuk(db.getYolcuId(yolcu), seferId, seciliKoltuk(list));
                        db.insertKoltuk(koltuk);
                    }
                    JOptionPane.showMessageDialog(null, "Kay??t Ba??ar??l??");
                    frame.dispose();
                    UserMainScreen userMainScreen = new UserMainScreen();
                }
            }
        });

        exitField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(frame,"????k???? Yapmak ??stedi??inizden Emin Misiniz?", "UYARI!",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if(result == JOptionPane.YES_NO_OPTION){
                    frame.dispose();
                    new UserLogin();
                }
            }
        });
        kullaniciDuzenleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserUpdateList();
            }
        });
        yenileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new UserMainScreen();
            }
        });
        seferEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new seferScreen();
            }
        });

    }
    void koltuk(List <JRadioButton> list){
        list.add(a1);
        list.add(a2);
        list.add(a3);
        list.add(a4);
        list.add(a5);
        list.add(a6);
        list.add(a7);
        list.add(a8);
        list.add(a9);
        list.add(a10);
        list.add(a11);
        list.add(a12);
        list.add(a13);
        list.add(a14);
        list.add(a15);
        list.add(a16);
        list.add(a17);
        list.add(a18);
        list.add(a19);
        list.add(a20);
        list.add(a21);
        list.add(a22);
        list.add(a23);
        list.add(a24);
        list.add(a25);
        list.add(a26);
        list.add(a27);
        list.add(a28);
        list.add(a29);
        list.add(a30);
        list.add(a31);
        list.add(a32);
        list.add(a33);
        list.add(a34);
        list.add(a35);
        list.add(a36);
        list.add(a37);
        list.add(a38);
    }
    int seciliKoltuk(List<JRadioButton> list){
        int i = 1;
        for (JRadioButton b: list){
            if(b.isSelected()){
                return i;
            }
            i++;
        }
        return 0;

    }

    public static void main(String[] args){
        UserMainScreen usermainscreen = new UserMainScreen();
    }
}
