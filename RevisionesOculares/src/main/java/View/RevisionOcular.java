package View;

import BDEntities.Client;
import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.boot.model.naming.DatabaseIdentifier;
import org.hibernate.dialect.Database;
import org.hibernate.query.Query;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

public class RevisionOcular extends JFrame{
    private JPanel panel1;
    private JTable table1;
    private JTextField textField1;
    private JTextField textField2;
    private JPasswordField passwordField1;
    private JComboBox comboBox1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;

    private List<Client> result;

    public RevisionOcular(List<Client> clients){
        result = clients;
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        String[] column = {"NIF", "NOMBRE", "APELLIDOS", "EDAD"};
        FillData(column);

        //DefaultTableModel tableModel = new DefaultTableModel(column, 50);
        //table1.setModel(tableModel);


    }

    private void FillData(String[] column){

        DefaultTableModel dtm = new DefaultTableModel(column, 50);
        /*
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("NIF");
        dtm.addColumn("NOMBRE");
        dtm.addColumn("APELLIDOS");
        dtm.addColumn("EDAD");

        Font f = new Font("Georgia", Font.BOLD, 16);
        JTableHeader header = ledgerTable.getTableHeader();
        header.setFont(f);
        ledgerTable.setRowHeight(25);
        */

        for (int i = 0; i < result.size(); i++) {
            dtm.addRow(new Object[]{result.get(i).getNif(), result.get(i).getNombre(), result.get(i).getApellidos(), result.get(i).getEdad()});
        }
        table1.setModel(dtm);
    }
}

