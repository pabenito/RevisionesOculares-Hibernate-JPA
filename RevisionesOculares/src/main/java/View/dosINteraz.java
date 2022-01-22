package View;

import BDEntities.Client;
import BDEntities.Eye;

import javax.persistence.EntityManager;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

import static java.lang.System.exit;

public class dosINteraz {
    private Eye seleccionado;
    private Client clienteSeleccionado;
    private Collection<Eye> pruebas;


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

    public dosINteraz(Client c) {
        seleccionado = null;
        pruebas = new ArrayList<>();

        pruebas = c.getTeyesByNif(); // ahora tengo todas las pruebas de este cliente
        String[] column = {"ID", "NIF", "CONSULTA", "OD_ESFERA", "OD_CILINDRO", "OD_ADICION", "OD_AGUDEZA", "OI_ESFERA", "OI_CILINDRO", "OI_ADICION", "OI_AGUDEZA"};
        // tenemos que rellenar la tabla
        FillTable(column);

        /*

         JFrame frame = new JFrame("PRUEBAS");
        frame.setContentPane(new dosINteraz().panel2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

         */


        // al crearse el constructor es como el LOAD en C#
        // cargamos la tabla conectándonos a la bbdd


        bLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionado = null;
                mostrarSeleccionado();
            }
        });
        bAñadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    // falta conectar con la bbdd

                    Eye prueba = new Eye();
                    prueba.setOdAdicion(seleccionado.getOdAdicion());
                    prueba.setOdAgudeza(seleccionado.getOdAgudeza());
                    prueba.setOdCilindro(seleccionado.getOdCilindro());
                    prueba.setOdEsfera(seleccionado.getOdEsfera());

                    prueba.setOiAdicion(seleccionado.getOiAdicion());
                    prueba.setOiAgudeza(seleccionado.getOiAgudeza());
                    prueba.setOiCilindro(seleccionado.getOiCilindro());
                    prueba.setOiEsfera(seleccionado.getOiEsfera());

                    // miBd. .inssert(prueba)


                    table1.clearSelection();
                    //cargamos la bd de nuevo


                    seleccionado = null;
                    mostrarSeleccionado();


                } catch (Exception ex) {
                    System.err.println("ERROR " + ex.getMessage()); // esto hay que cambiarlo parar que salga una pantallita
                }
            }
        });

        bBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {


                    EntityManager.remove(seleccionado);
                    seleccionado = null;
                    table1.clearSelection();
                    mostrarSeleccionado();


                } catch (Exception ex) {
                    System.err.println("ERROR " + ex.getMessage()); // esto hay que cambiarlo parar que salga una pantallita
                }
            }
        });
        bSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }


    public void mostrarSeleccionado() {
        if (seleccionado == null) {
            // podriarecargar el cread

            table1.clearSelection();

            tID_ADICION.setText("");
            tID_AGUDEZA.setText("");
            tID_CILINDRO.setText("");
            tID_ESFERA.setText("");
            tOD_ADICION.setText("");
            tOD_AGUDEZA.setText("");
            tOD_CILINDRO.setText("");
            tOD_AGUDEZA.setText("");
            tOD_ESFERA.setText("");


        } else {


            tID_ADICION.setText("" + seleccionado.getOiAdicion());
            tID_AGUDEZA.setText("" + seleccionado.getOiAgudeza());
            tID_CILINDRO.setText("" + seleccionado.getOdCilindro());
            tID_ESFERA.setText("" + seleccionado.getOdEsfera());
            tOD_ADICION.setText("" + seleccionado.getOdAdicion());
            tOD_AGUDEZA.setText("" + seleccionado.getOdAgudeza());
            tOD_CILINDRO.setText("" + seleccionado.getOdCilindro());
            tOD_AGUDEZA.setText("" + seleccionado.getOdAgudeza());
        }
    }

    private void FillTable(String[] column) {

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

        Iterator iter = pruebas.iterator();
        // para poder pasarlo a a addRow los parametros necesito un iterador para manejar la coleccion
        for (int i = 0; i < pruebas.size(); i++) {

            Eye prueba = (Eye) iter.next();
            dtm.addRow(new Object[]{prueba.getId(), prueba.getNif(), prueba.getConsulta(), prueba.getOdEsfera(), prueba.getOdCilindro(), prueba.getOdAdicion(), prueba.getOiEsfera(), prueba.getOiCilindro(), prueba.getOiAdicion(), prueba.getOiAgudeza()});
        }
        table1.setModel(dtm);
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("dosINteraz");
        Client c ;
        c = new Client();
        c.setApellidos("ajj");
        frame.setContentPane(new dosINteraz(c).panel2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}