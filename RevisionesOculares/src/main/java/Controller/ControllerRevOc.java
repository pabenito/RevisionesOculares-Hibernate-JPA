package Controller;

import BDEntities.Client;
import View.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ControllerRevOc {
    public void onAdd(String[] columns, JTable table, String NIF, String Nombre, String Apellidos, int selectedItem) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            List<Client> clients = (List<Client>) entityManager.createQuery("from Client").getResultList();

            entityManager.persist(seleccionado);

            FillTable(columns, clients, table);
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public void onMod() {
    }

    public void onDel(String[] columns, JTable table) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            Client cl = entityManager.find(Client.class, "1");
            entityManager.remove(cl);

            List<Client> clients = (List<Client>) entityManager.createQuery("from Client").getResultList();

            FillTable(columns, clients, table);
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public void onClean() {
    }

    public void onExit() {
    }

    public void onRevisiones(RevisionOcular revisionOcular) {

        //obtener el cliente seleccionado en revisionOcular
        revisionOcular.dispose();

        dosINteraz rp = new dosINteraz();
        rp.pack();
        rp.setLocationRelativeTo(null);
        rp.setVisible(true);
    }

    private void FillTable(String[] column, List<Client> clients, JTable table){
        //DefaultTableModel dtm = new DefaultTableModel(column, 0);
        DefaultTableModel dtm = new DefaultTableModel();
        for (int i = 0; i < column.length; i++) {
            dtm.addColumn(column[i]);
        }

        for (int i = 0; i < clients.size(); i++) {
            dtm.addRow(new Object[]{clients.get(i).getNif(), clients.get(i).getNombre(), clients.get(i).getApellidos(), clients.get(i).getEdad()});
        }
        table.setModel(dtm);
    }
}
