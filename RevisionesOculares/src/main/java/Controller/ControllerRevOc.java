package Controller;

import BDEntities.Client;
import View.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.util.List;

public class ControllerRevOc {
    public void onAdd(RevisionOcular revOc) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            List<Client> clients = (List<Client>) entityManager.createQuery("from Client").getResultList();

            //entityManager.persist(seleccionado);

            //FillTable(columns, clients, table);
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public void onMod(RevisionOcular revOc) {
    }

    public void onDel(RevisionOcular revOc) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            Client cl = revOc.getSeleccionado();
            entityManager.remove(cl);

            revOc.setSeleccionado(null);

            List<Client> clients = (List<Client>) entityManager.createQuery("from Client").getResultList();

            FillTable(revOc);
            FillFields(revOc);
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public void onClean(RevisionOcular revOc) {
        FillTable(revOc);
        FillFields(revOc);
    }

    public void onExit(RevisionOcular revOc) {
        revOc.dispose();
    }

    public void onRevisiones(RevisionOcular revOc) {

        //obtener el cliente seleccionado en revisionOcular

        if(revOc.getSeleccionado()!=null){
            revOc.dispose();

            dosINteraz rp = new dosINteraz();
            rp.pack();
            rp.setLocationRelativeTo(null);
            rp.setVisible(true);
        }
    }

    public void FillTable(RevisionOcular revOc){
        //DefaultTableModel dtm = new DefaultTableModel(column, 0);
        DefaultTableModel dtm = new DefaultTableModel();
        String[] columns = revOc.getColumns();

        for (int i = 0; i < columns.length; i++) {
            dtm.addColumn(columns[i]);
        }

        List<Client> clients = revOc.getClients();

        for (int i = 0; i < clients.size(); i++) {
            dtm.addRow(new Object[]{clients.get(i).getNif(), clients.get(i).getNombre(), clients.get(i).getApellidos(), clients.get(i).getEdad()});
        }
        JTable table = revOc.getTable1();
        table.setModel(dtm);
        revOc.setTable1(table);
        revOc.setSeleccionado(null);
    }

    public void FillFields(RevisionOcular a) {
        //int idx = table1.rowAtPoint(e.getPoint());
        JTable table1 = a.getTable1();
        int idx = table1.getSelectedRow();
        if(idx == -1 && a.getSeleccionado()==null){

            a.gettNIF().setText("");
            a.gettNombre().setText("");
            a.gettApellidos().setText("");
            a.getComboBox1().setSelectedIndex(-1);     //no se si funciona
        }else{
            a.setSeleccionado(new Client());
            Client sel = a.getSeleccionado();
            sel.setNif(String.valueOf(table1.getValueAt(idx, 0)));
            sel.setNombre(String.valueOf(table1.getValueAt(idx, 1)));
            sel.setApellidos(String.valueOf(table1.getValueAt(idx, 2)));
            //seleccionado.setEdad();

            a.gettNIF().setText(sel.getNif());
            a.gettNombre().setText(sel.getNombre());
            a.gettApellidos().setText(sel.getApellidos());
            //comboBox1.setSelectedIndex();
            //no se cuantas edades deberia almacenar el comboBox
        }
    }
}


/*
        public void FillFields(JTable table1, Client sel, JTextField tNIF,
                           JTextField tNombre, JTextField tApellidos, JComboBox comboBox1){
        //int idx = table1.rowAtPoint(e.getPoint());
        int idx = table1.getSelectedRow();
        if(idx == -1 && sel==null){

            tNIF.setText("");
            tNombre.setText("");
            tApellidos.setText("");
            comboBox1.setSelectedIndex(-1);     //no se si funciona
        }else{
            sel = new Client();
            sel.setNif(String.valueOf(table1.getValueAt(idx, 0)));
            sel.setNombre(String.valueOf(table1.getValueAt(idx, 1)));
            sel.setApellidos(String.valueOf(table1.getValueAt(idx, 2)));
            //seleccionado.setEdad();

            tNIF.setText(sel.getNif());
            tNombre.setText(sel.getNombre());
            tApellidos.setText(sel.getApellidos());
            //comboBox1.setSelectedIndex();
            //no se cuantas edades deberia almacenar el comboBox
        }

    }
     */