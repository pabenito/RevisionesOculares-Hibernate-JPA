package View;

import BDEntities.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class RevisionOcular extends JFrame{
    private JPanel panel1;
    private JTable table1;
    private JTextField textField1;
    private JTextField textField2;
    private JPasswordField passwordField1;
    private JComboBox comboBox1;
    private JButton revisionesButton;
    private JButton añadirButton;
    private JButton borrarButton;
    private JButton salirButton;
    private JButton actualizarButton;
    private JButton limpiarButton;

    private List<Client> clients;

    public RevisionOcular(List<Client> cls){
        clients = cls;
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] column = {"NIF", "NOMBRE", "APELLIDOS", "EDAD"};
        FillTable(column);

    }

    // he creado este método de tal manera que se pueda copiar y pegar en la otra interfaz.
    private void FillTable(String[] column){

        //DefaultTableModel dtm = new DefaultTableModel(column, 0);

        DefaultTableModel dtm = new DefaultTableModel();
        for (int i = 0; i < column.length; i++) {
            dtm.addColumn(column[i]);
        }
        /*
        Font f = new Font("Georgia", Font.BOLD, 16);
        JTableHeader header = ledgerTable.getTableHeader();
        header.setFont(f);
        ledgerTable.setRowHeight(25);
        */

        for (int i = 0; i < clients.size(); i++) {
            dtm.addRow(new Object[]{clients.get(i).getNif(), clients.get(i).getNombre(), clients.get(i).getApellidos(), clients.get(i).getEdad()});
        }
        table1.setModel(dtm);
    }

    //me imagino que para que se actualice la interfazen tiempo real,
    // habrá que hacerlo con action listener u otra cosa
    private void FillFields(){
        int index = table1.getSelectedRow();
        textField1.setText(clients.get(index).getNif());
    }
}

