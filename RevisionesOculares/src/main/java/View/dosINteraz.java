package View;

import BDEntities.Client;
import BDEntities.Eye;
import Controller.Controller2;
import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import static java.lang.System.exit;

public class dosINteraz extends JFrame{
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
    private JCalendar JCalendar1;

    private String [] columns;
    private List <Eye> eyes;
    private Eye seleccionado;
    private Client clienteSeleccionado;

    public dosINteraz(Client client, List<Eye> eys) {
        this.eyes = eys;
        clienteSeleccionado = client;
        seleccionado = null;

        setContentPane(panel2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        columns = new String[]{"ID", "NIF", "CONSULTA", "OD_ESFERA", "OD_CILINDRO", "OD_ADICION", "OD_AGUDEZA", "OI_ESFERA", "OI_CILINDRO", "OI_ADICION", "OI_AGUDEZA"};

        Controller2 controller = new Controller2();
        controller.FillTable(this);
        bLimpiar.addActionListener(e -> controller.onClean(this));
        bAñadir.addActionListener(e -> controller.onAdd(this));
        bBorrar.addActionListener(e -> controller.onDel(this));
        bSalir.addActionListener(e -> controller.onExit(this));
        bActualizar.addActionListener(e -> controller.onMod(this));

        dosINteraz dIn = this;
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //controller.FillFields(table1, seleccionado, tNIF, tNombre, tApellidos, comboBox1);
                controller.FillFields(dIn);
            }
        });
    }

    public JTable getTable1() {
        return table1;
    }

    public void setTable1(JTable table1) {
        this.table1 = table1;
    }

    public Eye getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Eye seleccionado) {
        this.seleccionado = seleccionado;
    }

    public JTextField gettOD_ESFERA() {
        return tOD_ESFERA;
    }

    public void settOD_ESFERA(JTextField tOD_ESFERA) {
        this.tOD_ESFERA = tOD_ESFERA;
    }

    public JTextField gettOD_CILINDRO() {
        return tOD_CILINDRO;
    }

    public void settOD_CILINDRO(JTextField tOD_CILINDRO) {
        this.tOD_CILINDRO = tOD_CILINDRO;
    }

    public JTextField gettOD_ADICION() {
        return tOD_ADICION;
    }

    public void settOD_ADICION(JTextField tOD_ADICION) {
        this.tOD_ADICION = tOD_ADICION;
    }

    public JTextField gettOD_AGUDEZA() {
        return tOD_AGUDEZA;
    }

    public void settOD_AGUDEZA(JTextField tOD_AGUDEZA) {
        this.tOD_AGUDEZA = tOD_AGUDEZA;
    }

    public JTextField gettID_AGUDEZA() {
        return tID_AGUDEZA;
    }

    public void settID_AGUDEZA(JTextField tID_AGUDEZA) {
        this.tID_AGUDEZA = tID_AGUDEZA;
    }

    public JTextField gettID_ADICION() {
        return tID_ADICION;
    }

    public void settID_ADICION(JTextField tID_ADICION) {
        this.tID_ADICION = tID_ADICION;
    }

    public JTextField gettID_CILINDRO() {
        return tID_CILINDRO;
    }

    public void settID_CILINDRO(JTextField tID_CILINDRO) {
        this.tID_CILINDRO = tID_CILINDRO;
    }

    public JTextField gettID_ESFERA() {
        return tID_ESFERA;
    }

    public void settID_ESFERA(JTextField tID_ESFERA) {
        this.tID_ESFERA = tID_ESFERA;
    }

    public JCalendar getJCalendar1() {
        return JCalendar1;
    }

    public void setJCalendar1(JCalendar JCalendar1) {
        this.JCalendar1 = JCalendar1;
    }

    public String[] getColumns() {
        return columns;
    }

    public List<Eye> getEyes() {
        return eyes;
    }

    public Client getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setPruebas(List<Eye> pruebas) {
        this.eyes = pruebas;
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        JCalendar1 = new JCalendar();
    }
}