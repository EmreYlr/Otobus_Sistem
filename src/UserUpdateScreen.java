import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserUpdateScreen extends JDialog {
    private JPanel contentPane;
    private JTextField tcField;
    private JTextField isimField;
    private JRadioButton erkekRadioButton;
    private JRadioButton kadinRadioButton;
    private JButton duzenleButton;
    private JTextField telefonField;
    private JTextField soyadField;
    private JTextField seferNoField;
    private JTextField koltukNoField;
    private JButton buttonOK;
    private JButton buttonCancel;
    JFrame frame;
    public UserUpdateScreen(String[] temp) {
        frame = new JFrame("Kullanıcı Düzenle");
        frame.setSize(300, 300);
        frame.add(contentPane);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tcField.setText(temp[0]);
        isimField.setText(temp[1]);
        soyadField.setText(temp[2]);
        telefonField.setText(temp[3]);
        seferNoField.setText(temp[5]);
        koltukNoField.setText(temp[6]);
        if(temp[4].equals("erkek")){
            erkekRadioButton.setSelected(true);
        }else{
            kadinRadioButton.setSelected(true);
        }


        duzenleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] tempUpdate = new String[7];
                tempUpdate[0] = tcField.getText();
                tempUpdate[1] = isimField.getText();
                tempUpdate[2] = soyadField.getText();
                tempUpdate[3] = telefonField.getText();
                tempUpdate[5] = seferNoField.getText();
                tempUpdate[6] = koltukNoField.getText();
                if(erkekRadioButton.isSelected()){
                    tempUpdate[4] = "erkek";
                }else{
                    tempUpdate[4] = "kadın";
                }
                DatabaseLayer db = new DatabaseLayer();
                db.updateYolcu(temp,tempUpdate);
                frame.dispose();
                new UserUpdateList();
            }
        });
    }
    public static void main(String[] args){
        //UserUpdateScreen userupdatescreen = new UserUpdateScreen(null);
    }

}