import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class seferScreenUpdate extends JDialog {
    private JPanel contentPane;
    private JTextField idField;
    private JTextField kalkisField;
    private JTextField varisField;
    private JButton duzenleButton;
    private JTextField kalkisTarihField;
    private JTextField varisTarihField;
    private JTextField kaptanField;
    private JTextField plakaField;
    private JTextField kapasiteField;

    public seferScreenUpdate(String[] temp, JFrame x) {
        JFrame frame = new JFrame("Sefer Düzenle");
        frame.setSize(300, 350);
        frame.add(contentPane);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        idField.setText(temp[0]);
        kalkisField.setText(temp[1]);
        varisField.setText(temp[2]);
        kalkisTarihField.setText(temp[3]);
        varisTarihField.setText(temp[4]);
        kaptanField.setText(temp[5]);
        plakaField.setText(temp[6]);
        kapasiteField.setText(temp[7]);

        duzenleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(idField.getText().equals("") || kalkisField.getText().equals("") || varisField.getText().equals("") || kalkisTarihField.getText().equals("")
                        || varisTarihField.getText().equals("") || kaptanField.getText().equals("") || plakaField.getText().equals("") || kaptanField.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Lütfen Boşlukları Doldurunuz!");
                }else{
                    String[] tempUpdate = new String[8];
                    tempUpdate[0] = idField.getText();
                    tempUpdate[1] = kalkisField.getText();
                    tempUpdate[2] = varisField.getText();
                    tempUpdate[3] = kalkisTarihField.getText();
                    tempUpdate[4] = varisTarihField.getText();
                    tempUpdate[5] = kaptanField.getText();
                    tempUpdate[6] = plakaField.getText();
                    tempUpdate[7] = kapasiteField.getText();

                    DatabaseLayerUser db = new DatabaseLayerUser();
                    int result = JOptionPane.showConfirmDialog(frame,"Kaydetmek İstediğinizden Emin Misiniz?", "UYARI!",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if(result == JOptionPane.YES_NO_OPTION){
                        db.updateSefer(temp,tempUpdate);
                        frame.dispose();
                        x.dispose();
                        new seferScreen();
                    }
                }

            }
        });
    }


}
