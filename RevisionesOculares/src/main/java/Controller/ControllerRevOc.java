package Controller;

import BDEntities.Client;
import View.*;

import javax.persistence.*;
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

            Client client = new Client();
            client.setNif(revOc.gettNIF().getText());
            client.setNombre(revOc.gettNombre().getText());
            client.setApellidos(revOc.gettApellidos().getText());
            client.setEdad(revOc.getComboBox1().getSelectedIndex());

            if(!entityManager.contains(client)){
                entityManager.persist(client);

            }
            List<Client> clients = (List<Client>) entityManager.createQuery("from Client").getResultList();
            revOc.setClients(clients);

            onClean(revOc);
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

            Client client = revOc.getSeleccionado();
            entityManager.remove(entityManager.merge(client));

            List<Client> clients = (List<Client>) entityManager.createQuery("from Client").getResultList();
            revOc.setClients(clients);

            onClean(revOc);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public void onClean(RevisionOcular revOc) {
        revOc.setSeleccionado(null);
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

    public void FillFields(RevisionOcular revOc) {
        //int idx = table1.rowAtPoint(e.getPoint());
        JTable table1 = revOc.getTable1();
        int idx = table1.getSelectedRow();
        if(idx == -1 && revOc.getSeleccionado()==null){

            revOc.gettNIF().setText("");
            revOc.gettNombre().setText("");
            revOc.gettApellidos().setText("");
            revOc.getComboBox1().setSelectedIndex(-1);     //no se si funciona
        }else{
            revOc.setSeleccionado(new Client());
            Client sel = revOc.getSeleccionado();
            sel.setNif(String.valueOf(table1.getValueAt(idx, 0)));
            sel.setNombre(String.valueOf(table1.getValueAt(idx, 1)));
            sel.setApellidos(String.valueOf(table1.getValueAt(idx, 2)));
            sel.setEdad(Integer.valueOf((Integer) table1.getValueAt(idx, 3)));

            revOc.gettNIF().setText(sel.getNif());
            revOc.gettNombre().setText(sel.getNombre());
            revOc.gettApellidos().setText(sel.getApellidos());
            revOc.getComboBox1().setSelectedIndex(sel.getEdad());
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