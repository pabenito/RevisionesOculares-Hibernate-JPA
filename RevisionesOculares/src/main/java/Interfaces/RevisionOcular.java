package Interfaces;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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

    public RevisionOcular(){
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] column = {"si", "No"};
        DefaultTableModel tableModel = new DefaultTableModel(column, 50);
        table1.setModel(tableModel);
    }
}

