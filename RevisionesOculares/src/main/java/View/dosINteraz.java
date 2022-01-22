package View;

import BDEntities.Client;
import BDEntities.Eye;
import Controller.Controller2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

import java.util.Calendar;

import static java.lang.System.exit;

public class dosINteraz extends JFrame{
    private Client ClienteSeleccionado;
    private Eye Seleccionado;

    private JPanel panel2;
    private JTextField tOD_ESFERA;
    private JButton bBorrar;
    private JButton bActualizar;
    private JTextField textField2;
    private JTable table1;
    private JTextField tOD_CILINDRO;
    private JTextField tOD_ADICION;
    private JTextField tOD_AGUDEZA;
    private JTextField tID_AGUDEZA;
    private JTextField tID_ADICION;
    private JTextField tID_CILINDRO;
    private JTextField tID_ESFERA;
    private JButton bAñadir;
    private JButton bLimpiar;
    private JButton bSalir;
    //private JCalendar jcalendar;

    public dosINteraz(Client cs, List<Eye> eyes) {


        setContentPane(panel2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Controller2 controller = new Controller2();
        //LOAD
        String[] columns = {"ID", "NIF", "CONSULTA", "OD_ESFERA", "OD_CILINDRO", "OD_ADICION", "OD_AGUDEZA", "OI_ESFERA", "OI_CILINDRO", "OI_ADICION", "OI_AGUDEZA"};
        controller.FillTable(columns, eyes, this.getTable1());
        controller.showCliente(cs, this.getTextField2());

        //-----------------------

        //SELECTION CHANGED
        Eye seleccionado = new Eye();
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //int idx = table1.rowAtPoint(e.getPoint());
                int idx = table1.getSelectedRow();
                seleccionado.setId(Integer.valueOf((int) table1.getValueAt(idx, 0)));
                seleccionado.setNif(String.valueOf(table1.getValueAt(idx, 1)));
                seleccionado.setConsulta(Date.valueOf(table1.getValueAt(idx, 2)));
                seleccionado.setOdEsfera(Double.valueOf((double) table1.getValueAt(idx, 3)));
                seleccionado.setOdCilindro(Double.valueOf((double) table1.getValueAt(idx, 4)));
                seleccionado.setOdAdicion(Double.valueOf((double) table1.getValueAt(idx, 5)));
                seleccionado.setOdAgudeza(Double.valueOf((double) table1.getValueAt(idx, 6)));
                seleccionado.setOiEsfera(Double.valueOf((double) table1.getValueAt(idx, 7)));
                seleccionado.setOiCilindro(Double.valueOf((double) table1.getValueAt(idx, 8)));
                seleccionado.setOiAdicion(Double.valueOf((double) table1.getValueAt(idx, 9)));
                seleccionado.setOiAgudeza(Double.valueOf((double) table1.getValueAt(idx, 10)));

            }
        });

        controller.mostrarSeleccionado(seleccionado, this);
        //-------------------------
        //BOTÓN PULSADO
        bLimpiar.addActionListener(e -> controller.onClean(this));
        bAñadir.addActionListener(e -> controller.onAdd(this));
        bBorrar.addActionListener(e -> controller.onDel(this));
        bSalir.addActionListener(e -> controller.onExit(this));
        bActualizar.addActionListener(e -> controller.onMod(this));


    }
    //---------------------

    //GETTERS Y SETTERS
    public JTextField gettOD_CILINDRO() {
        return tOD_CILINDRO;
    }

    public JTextField gettOD_ESFERA() {
        return tOD_ESFERA;
    }

    public JTextField gettOD_ADICION() {
        return tOD_ADICION;
    }

    public JTextField gettOD_AGUDEZA() {
        return tOD_AGUDEZA;
    }

    public JTextField gettID_AGUDEZA() {
        return tID_AGUDEZA;
    }

    public JTextField gettID_ADICION() {
        return tID_ADICION;
    }

    public JTextField gettID_CILINDRO() {
        return tID_CILINDRO;
    }

    public JTextField gettID_ESFERA() {
        return tID_ESFERA;
    }

    public void settOD_CILINDRO(JTextField tOD_CILINDRO) {
        this.tOD_CILINDRO = tOD_CILINDRO;
    }

    public void settOD_ADICION(JTextField tOD_ADICION) {
        this.tOD_ADICION = tOD_ADICION;
    }

    public void settOD_AGUDEZA(JTextField tOD_AGUDEZA) {
        this.tOD_AGUDEZA = tOD_AGUDEZA;
    }

    public void settID_AGUDEZA(JTextField tID_AGUDEZA) {
        this.tID_AGUDEZA = tID_AGUDEZA;
    }

    public void settID_ADICION(JTextField tID_ADICION) {
        this.tID_ADICION = tID_ADICION;
    }

    public void settID_CILINDRO(JTextField tID_CILINDRO) {
        this.tID_CILINDRO = tID_CILINDRO;
    }

    public void settID_ESFERA(JTextField tID_ESFERA) {
        this.tID_ESFERA = tID_ESFERA;
    }

    public void settOD_ESFERA(JTextField tOD_ESFERA) {
        this.tOD_ESFERA = tOD_ESFERA;
    }

    public JTable getTable1() {
        return table1;
    }

    public void setTable1(JTable table1) {
        this.table1 = table1;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public Client getClienteSeleccionado() {
        return ClienteSeleccionado;
    }

    public void setClienteSeleccionado(Client clienteSeleccionado) {
        ClienteSeleccionado = clienteSeleccionado;
    }

    public Eye getSeleccionado() {
        return Seleccionado;
    }

    public void setSeleccionado(Eye seleccionado) {
        Seleccionado = seleccionado;
    }
     // SETTER Y GETTER DE JCALENDAR
}