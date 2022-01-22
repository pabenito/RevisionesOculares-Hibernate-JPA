package View;

import BDEntities.Client;
import Controller.ControllerRevOc;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class RevisionOcular extends JFrame{
    private JPanel panel1;
    private JTable table1;
    private JTextField tNIF;
    private JTextField tNombre;
    private JTextField tApellidos;
    private JComboBox comboBox1;
    private JButton revisionesButton;
    private JButton añadirButton;
    private JButton borrarButton;
    private JButton salirButton;
    private JButton actualizarButton;
    private JButton limpiarButton;

    private String[] columns;
    private List<Client> clients;
    private Client seleccionado;

    public RevisionOcular(List<Client> cls){
        clients = cls;
        //seleccionado = new Client();
        seleccionado = null;
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        columns = new String[]{"NIF", "NOMBRE", "APELLIDOS", "EDAD"};

        ControllerRevOc controller = new ControllerRevOc();
        controller.FillTable(this);
        añadirButton.addActionListener(e -> controller.onAdd(this));
        actualizarButton.addActionListener(e -> controller.onMod(this));
        borrarButton.addActionListener(e -> controller.onDel(this));
        limpiarButton.addActionListener(e -> controller.onClean(this));
        salirButton.addActionListener(e -> controller.onExit(this));
        revisionesButton.addActionListener(e -> controller.onRevisiones(this));

        RevisionOcular a = this;
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //controller.FillFields(table1, seleccionado, tNIF, tNombre, tApellidos, comboBox1);
                controller.FillFields(a);
            }
        });

    }

    public JTable getTable1() {
        return table1;
    }

    public void setTable1(JTable table1) {
        this.table1 = table1;
    }

    public JTextField gettNIF() {
        return tNIF;
    }

    public void settNIF(JTextField tNIF) {
        this.tNIF = tNIF;
    }

    public JTextField gettNombre() {
        return tNombre;
    }

    public void settNombre(JTextField tNombre) {
        this.tNombre = tNombre;
    }

    public JTextField gettApellidos() {
        return tApellidos;
    }

    public void settApellidos(JTextField tApellidos) {
        this.tApellidos = tApellidos;
    }

    public JComboBox getComboBox1() {
        return comboBox1;
    }

    public void setComboBox1(JComboBox comboBox1) {
        this.comboBox1 = comboBox1;
    }

    public Client getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Client seleccionado) {
        this.seleccionado = seleccionado;
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    //me imagino que para que se actualice la interfazen tiempo real,
    // habrá que hacerlo con action listener u otra cosa
}

