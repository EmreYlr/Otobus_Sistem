import model.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class seferScreen extends JDialog {
    private JPanel contentPane;
    private JSplitPane rootPanel;
    private JButton duzenleButton;
    private JButton silButton;
    private JTable table1;
    private JButton ekleButton;
    private JTextField kalkisField;
    private JTextField varisField;
    private JTextField kalkisTarihField;
    private JTextField varisTarihField;
    private JTextField kaptanField;
    private JTextField plakaField;
    private JComboBox kalkisBox;
    private JComboBox varisBox;
    private JComboBox gunBox;
    private JComboBox ayBox;
    private JComboBox yearBox;
    private JComboBox saatBox;
    private JComboBox dkBox;
    private JComboBox varisGunBox;
    private JComboBox varisAyBox;
    private JComboBox varisYilBox;
    private JComboBox varisSaatBox;
    private JComboBox varisDkBox;
    private JLabel errorLabel;
    private Sefer selectSefer;
    private Otobus selectOtobus;
    private int selectedIndex;

    public seferScreen() {
        JFrame frame = new JFrame("Sefer Listesi");
        frame.setSize(900, 500);
        frame.add(contentPane);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        DatabaseLayerUser db = new DatabaseLayerUser();
        List<Sefer> sefer = new ArrayList<>();
        List<Otobus> otobus = new ArrayList<>();
        db.getSeferList(sefer);
        db.getOtobusList(otobus);
        SeferTableModel sefertablemodel = new SeferTableModel(sefer,otobus);
        table1.setModel(sefertablemodel);
        table1.setAutoCreateRowSorter(true);
        duzenleButton.setEnabled(false);
        silButton.setEnabled(false);
        DefaultTableCellRenderer left = new DefaultTableCellRenderer();
        left.setHorizontalAlignment(JLabel.LEFT);
        table1.getColumnModel().getColumn(0).setPreferredWidth(20);
        table1.getColumnModel().getColumn(1).setPreferredWidth(20);
        table1.getColumnModel().getColumn(2).setPreferredWidth(20);
        table1.getColumnModel().getColumn(3).setPreferredWidth(120);
        table1.getColumnModel().getColumn(4).setPreferredWidth(120);
        table1.getColumnModel().getColumn(6).setPreferredWidth(50);
        table1.getColumnModel().getColumn(7).setPreferredWidth(50);
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        table1.getColumnModel().getColumn(0).setCellRenderer(left);
        table1.getColumnModel().getColumn(1).setCellRenderer(left);
        table1.getColumnModel().getColumn(2).setCellRenderer(left);
        table1.getColumnModel().getColumn(7).setCellRenderer(left);

        table1.getSelectionModel().addListSelectionListener(e->{
            duzenleButton.setEnabled(true);
            silButton.setEnabled(true);
        });
        duzenleButton.addActionListener(new ActionListener() {
            String[] temp = new String[8];
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!table1.getSelectionModel().isSelectionEmpty()){
                    selectedIndex = table1.convertRowIndexToModel(table1.getSelectedRow());
                    selectSefer = sefer.get(selectedIndex);
                    selectOtobus = otobus.get(selectedIndex);
                    if(selectSefer != null){
                        temp[0] = String.valueOf(selectSefer.getId());
                        temp[1] = (selectSefer.getPlakaKoduKalkis() == 34 ? "??stanbul":"Urfa");
                        temp[2] = (selectSefer.getPlakaKoduVaris() == 34 ? "??stanbul":"Urfa");
                        temp[3] = (selectSefer.getKalkisTarihi());
                        temp[4] = (selectSefer.getVarisTarihi());
                        temp[5] = (selectSefer.getKaptan());
                        temp[6] = (selectOtobus.getPlaka());
                        temp[7] = String.valueOf(selectOtobus.getKapasite());
                        new seferScreenUpdate(temp,frame);
                    }
                }
            }
        });
        silButton.addActionListener(new ActionListener() {
            String[] temp = new String[2];
            DatabaseLayerUser db = new DatabaseLayerUser();
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!table1.getSelectionModel().isSelectionEmpty()){
                    selectedIndex = table1.convertRowIndexToModel(table1.getSelectedRow());
                    selectSefer = sefer.get(selectedIndex);
                    selectOtobus = otobus.get(selectedIndex);
                    if(selectSefer != null){
                        temp[0] = String.valueOf(selectSefer.getId());
                        temp[1] = selectOtobus.getPlaka();
                        int result = JOptionPane.showConfirmDialog(frame,"Silmek ??stedi??inizden Emin Misiniz?", "UYARI!",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE);
                        if(result == JOptionPane.YES_NO_OPTION){
                            db.seferSil(temp);
                            frame.dispose();
                            new seferScreen();
                        }

                    }
                }
            }
        });
        ekleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(kaptanField.getText().equals("") || plakaField.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "L??tfen Bo??luklar?? Doldurunuz!");
                }else{
                    DatabaseLayerUser db = new DatabaseLayerUser();
                    Sefer s = new Sefer();
                    Otobus o = new Otobus();
                    o.setPlaka(plakaField.getText());
                    o.setKapasite(38);
                    db.instertOtobus(o);
                    s.setOtobus_id(db.getOtobusId(o));
                    s.setPlakaKoduKalkis(kalkisBox.getSelectedItem().equals("??stanbul") ? 34:63);
                    s.setPlakaKoduVaris(varisBox.getSelectedItem().equals("??stanbul") ? 34:63);
                    s.setKalkisTarihi(gunBox.getSelectedItem() + "/" + ayBox.getSelectedItem() + "/" + yearBox.getSelectedItem() + " " + saatBox.getSelectedItem() + ":" + dkBox.getSelectedItem());
                    s.setVarisTarihi(varisGunBox.getSelectedItem() + "/" + varisAyBox.getSelectedItem() + "/" + varisYilBox.getSelectedItem() + " " + varisSaatBox.getSelectedItem() + ":" + varisDkBox.getSelectedItem());
                    s.setKaptan(kaptanField.getText());
                    db.instertSefer(s);
                    s.setId(db.getSeferId(db.getOtobusId(o)));
                    sefer.add(s);
                    otobus.add(o);
                    sefertablemodel.fireTableDataChanged();
                }
            }
        });
    }
    private static class SeferTableModel extends AbstractTableModel {
        private final String[] COLUMNS = {"ID", "KALKI??", "VARI??", "KALKI?? TAR??H", "VARI?? TAR??H", "KAPTAN","PLAKA","KAPAS??TE"};

        private List<Sefer> sefer;
        private List<Otobus> otobus;

        public SeferTableModel(List<Sefer> sefer, List<Otobus> otobus) {
            this.sefer = sefer;
            this.otobus = otobus;
        }

        @Override
        public int getRowCount() {
            return sefer.size();
        }
        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex){
                case 0 -> sefer.get(rowIndex).getId();
                case 1 -> sefer.get(rowIndex).getPlakaKoduKalkis() == 34 ? "??stanbul":"Urfa";
                case 2 -> sefer.get(rowIndex).getPlakaKoduVaris()  == 63 ? "Urfa":"istanbul";
                case 3 -> sefer.get(rowIndex).getKalkisTarihi();
                case 4 -> sefer.get(rowIndex).getVarisTarihi();
                case 5 -> sefer.get(rowIndex).getKaptan();
                case 6 -> otobus.get(rowIndex).getPlaka();
                case 7 -> otobus.get(rowIndex).getKapasite();
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
    public static void main(String[] args){
        new seferScreen();
    }
}
