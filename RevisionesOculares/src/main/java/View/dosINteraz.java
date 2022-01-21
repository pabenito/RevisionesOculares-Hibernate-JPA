package View;

import BDEntities.Client;
import BDEntities.Eye;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import static java.lang.System.exit;

public class dosINteraz {
    private Eye seleccionado;
    private Client clienteSeleccionado;

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

    public dosINteraz() {
        seleccionado = null;

        // al crearse el constructor es como el LOAD en C#
        // cargamos la tabla conectándonos a la bbdd

        //los action listener los vamos a especificar en las clases controller
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

                exit(-1);
            }
        });
        bBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // falta conectar con la bbdd


                    //   mibd.borrar(seleccionado)
                    table1.clearSelection();
                    //cargamos la bd de nuevo
                    seleccionado = null;
                    mostrarSeleccionado();


                } catch (Exception ex) {
                    System.err.println("ERROR " + ex.getMessage()); // esto hay que cambiarlo parar que salga una pantallita
                }
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("dosINteraz");
        frame.setContentPane(new dosINteraz().panel2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}