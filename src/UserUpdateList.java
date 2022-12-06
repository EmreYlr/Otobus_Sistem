import javax.swing.*;

public class UserUpdateList extends JDialog {
    private JPanel contentPane;
    private JButton silButton;
    private JButton duzenleButton;
    private JTable table;
    private JButton araButton;
    private JTextField araField;
    private JLabel tcField;

    public UserUpdateList() {
        JFrame frame;
        frame = new JFrame("Kullanıcı Listesi");
        frame.setSize(900, 350);
        frame.add(contentPane);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }

    public static void main(String[] args){
        UserUpdateList userupdatelist = new UserUpdateList();
    }

}
