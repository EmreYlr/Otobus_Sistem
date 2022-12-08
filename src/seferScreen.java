import javax.swing.*;
import java.awt.event.*;

public class seferScreen extends JDialog {
    private JPanel contentPane;

    public seferScreen() {
        JFrame frame = new JFrame("Otobüs Sistemi");
        frame.setSize(900, 300);
        frame.add(contentPane);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
