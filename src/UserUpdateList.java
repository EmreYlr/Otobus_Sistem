import model.Koltuk;
import model.Kullanci;
import model.Yolcu;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UserUpdateList extends JDialog {
    private JPanel contentPane;
    private JButton silButton;
    private JButton duzenleButton;
    private JButton araButton;
    private JTable table1;
    private JSplitPane rootPanel;
    private Yolcu selectYolcu;
    private Koltuk selectKoltuk;
    private int selectedIndex;
    //public String[] getdata = new String[7];
    public UserUpdateList() {
        JFrame frame;
        frame = new JFrame("Kullanıcı Listesi");
        frame.setSize(900, 300);
        frame.add(contentPane);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        DatabaseLayer db = new DatabaseLayer();
        List<Yolcu> yolcu = new ArrayList<>();
        List<Koltuk> koltuk = new ArrayList<>();

        db.getYolcu(yolcu);
        db.getKoltuk(koltuk);
        YolcuTableModel yolcutablemodel = new YolcuTableModel(yolcu,koltuk);
        table1.setModel(yolcutablemodel);
        table1.setAutoCreateRowSorter(true);
        duzenleButton.setEnabled(false);
        silButton.setEnabled(false);

        table1.getSelectionModel().addListSelectionListener(e->{
            duzenleButton.setEnabled(true);
            silButton.setEnabled(true);
        });
        duzenleButton.addActionListener(new ActionListener() {
            String[] temp = new String[7];
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!table1.getSelectionModel().isSelectionEmpty()){
                    selectedIndex = table1.convertRowIndexToModel(table1.getSelectedRow());
                    selectYolcu = yolcu.get(selectedIndex);
                    selectKoltuk = koltuk.get(selectedIndex);
                    if(selectYolcu != null){
                        temp[0] = (selectYolcu.getTc());
                        temp[1] = (selectYolcu.getIsim());
                        temp[2] = (selectYolcu.getSoyisim());
                        temp[3] = (selectYolcu.getTelefon());
                        temp[4] = (String.valueOf(selectYolcu.getCinsiyet()));
                        temp[5] = (String.valueOf(selectKoltuk.getSefer_id()));
                        temp[6] = (String.valueOf(selectKoltuk.getKoltukNo()));
                        new UserUpdateScreen(temp,frame);
                    }
                }
            }

        });
        silButton.addActionListener(new ActionListener() {
            String[] temp = new String[3];
            DatabaseLayer db = new DatabaseLayer();
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!table1.getSelectionModel().isSelectionEmpty()){
                    selectedIndex = table1.convertRowIndexToModel(table1.getSelectedRow());
                    selectYolcu = yolcu.get(selectedIndex);
                    selectKoltuk = koltuk.get(selectedIndex);
                    if(selectYolcu != null){
                        temp[0] = (selectYolcu.getTc());
                        temp[1] = (String.valueOf(selectKoltuk.getSefer_id()));
                        temp[2] = (String.valueOf(selectKoltuk.getKoltukNo()));
                        int result = JOptionPane.showConfirmDialog(frame,"Silmek İstediğinizden Emin Misiniz?", "UYARI!",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE);
                        if(result == JOptionPane.YES_NO_OPTION){
                            db.yolcuSil(temp);
                            frame.dispose();
                            new UserUpdateList();
                        }

                    }
                }
            }
        });
    }

    private static class YolcuTableModel extends AbstractTableModel{
        private final String[] COLUMNS = {"TC","İSİM", "SOYİSİM", "TELEFON", "CİNSİYET", "SEFER NO", "KOLTUK NO"};
        private List<Yolcu> yolcu;
        private List<Koltuk> koltuk;
        public YolcuTableModel(List<Yolcu> yolcu, List<Koltuk> koltuk) {
            this.yolcu = yolcu;
            this.koltuk = koltuk;
        }
        @Override
        public int getRowCount() {
            return yolcu.size();
        }
        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex){
                case 0 -> yolcu.get(rowIndex).getTc();
                case 1 -> yolcu.get(rowIndex).getIsim();
                case 2 -> yolcu.get(rowIndex).getSoyisim();
                case 3 -> yolcu.get(rowIndex).getTelefon();
                case 4 -> yolcu.get(rowIndex).getCinsiyet();
                case 5 -> koltuk.get(rowIndex).getSefer_id();
                case 6 -> koltuk.get(rowIndex).getKoltukNo();
                default -> "-";
            };
        }
        @Override
        public String getColumnName(int column) {
            return COLUMNS[column];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if(getValueAt(0,columnIndex)!= null){
                return getValueAt(0,columnIndex).getClass();
            }else{
                return Object.class;
            }
        }
    }

    public static void main(String[] args) {
        UserUpdateList userupdatelist = new UserUpdateList();
    }
}




