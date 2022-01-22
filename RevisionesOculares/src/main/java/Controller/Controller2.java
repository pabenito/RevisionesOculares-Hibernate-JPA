package Controller;
import BDEntities.Client;
import BDEntities.Eye;
import View.*;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class Controller2 {

    public void onAdd(dosINteraz in){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(in.getSeleccionado());
            entityManager.persist(in.getSeleccionado());

            Eye eye = new Eye();
            eye.setId(in.getSeleccionado().getId());
            eye.setNif(in.getClienteSeleccionado().getNif());
            //eye.setConsulta();
            eye.setOdEsfera(Double.parseDouble(in.gettOD_ESFERA().getText()));
            eye.setOdCilindro(Double.parseDouble(in.gettOD_CILINDRO().getText()));
            eye.setOdAdicion(Double.parseDouble(in.gettOD_ADICION().getText()));
            eye.setOdAgudeza(Double.parseDouble(in.gettOD_AGUDEZA().getText()));

            eye.setOiEsfera(Double.parseDouble(in.gettID_ESFERA().getText()));
            eye.setOiCilindro(Double.parseDouble(in.gettID_CILINDRO().getText()));
            eye.setOiAdicion(Double.parseDouble(in.gettID_ADICION().getText()));
            eye.setOiAgudeza(Double.parseDouble(in.gettID_AGUDEZA().getText()));

            entityManager.persist(eye);
            onClean(in);
        }finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }

    }

    public void onMod(dosINteraz in) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Eye eye = new Eye();
            eye.setId(in.getSeleccionado().getId());
            eye.setNif(in.getClienteSeleccionado().getNif());
            //eye.setConsulta();
            eye.setOdEsfera(Double.parseDouble(in.gettOD_ESFERA().getText()));
            eye.setOdCilindro(Double.parseDouble(in.gettOD_CILINDRO().getText()));
            eye.setOdAdicion(Double.parseDouble(in.gettOD_ADICION().getText()));
            eye.setOdAgudeza(Double.parseDouble(in.gettOD_AGUDEZA().getText()));

            eye.setOiEsfera(Double.parseDouble(in.gettID_ESFERA().getText()));
            eye.setOiCilindro(Double.parseDouble(in.gettID_CILINDRO().getText()));
            eye.setOiAdicion(Double.parseDouble(in.gettID_ADICION().getText()));
            eye.setOiAgudeza(Double.parseDouble(in.gettID_AGUDEZA().getText()));

            entityManager.merge(eye);




            onClean(in);
        }finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }


    public void onDel(dosINteraz in) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Eye eye = in.getSeleccionado();
            entityManager.remove(entityManager.merge(eye));

            onClean(in);


        }finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public void onClean(dosINteraz in) {
        in.setSeleccionado(null);
        mostrarSeleccionado(null,in);
    }

    public void onExit(dosINteraz in) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            List<Client> clients = (List<Client>) entityManager.createQuery("from Client").getResultList();

            SwingUtilities.invokeLater(() -> {
                RevisionOcular frame = new RevisionOcular(clients);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            });

        } finally {
        if (transaction.isActive()) {
            transaction.rollback();
        }
        entityManager.close();
        entityManagerFactory.close();
        }
    }



    public void mostrarSeleccionado(Eye seleccionado, dosINteraz in) {
        if (seleccionado == null) {
            FillTable(in.getColumn(),in.getEyes(),in.getTable1(),in.getClienteSeleccionado());

            in.getTable1().clearSelection();

            in.gettID_ADICION().setText("");
            in.gettID_AGUDEZA().setText("");
            in.gettID_CILINDRO().setText("");
            in.gettID_ESFERA().setText("");
            in.gettOD_ADICION().setText("");
            in.gettOD_AGUDEZA().setText("");
            in.gettOD_CILINDRO().setText("");
            in.gettOD_AGUDEZA().setText("");
            in.gettOD_ESFERA().setText("");


        } else {


            in.gettID_ADICION().setText("" + seleccionado.getOiAdicion());
            in.gettID_AGUDEZA().setText("" + seleccionado.getOiAgudeza());
            in.gettID_CILINDRO().setText("" + seleccionado.getOdCilindro());
            in.gettID_ESFERA().setText("" + seleccionado.getOdEsfera());
            in.gettOD_ADICION().setText("" + seleccionado.getOdAdicion());
            in.gettOD_AGUDEZA().setText("" + seleccionado.getOdAgudeza());
            in.gettOD_CILINDRO().setText("" + seleccionado.getOdCilindro());
            in.gettOD_ESFERA().setText("" + seleccionado.getOdAgudeza());
        }
    }
    public void FillTable(String[] column, List <Eye> eyes, JTable table1, Client cl){

        DefaultTableModel dtm = new DefaultTableModel();
        for (int i = 0; i < column.length; i++) {
            dtm.addColumn(column[i]);
        }

        for (int i = 0; i < eyes.size(); i++) {
            dtm.addRow(new Object[]{eyes.get(i).getId(), cl.getNif(),eyes.get(i).getConsulta(),
                    eyes.get(i).getOdEsfera(), eyes.get(i).getOdCilindro(), eyes.get(i).getOdAdicion(), eyes.get(i).getOdAgudeza(),
                    eyes.get(i).getOiEsfera(), eyes.get(i).getOiCilindro(), eyes.get(i).getOiAdicion(), eyes.get(i).getOiAgudeza()});
        }
        table1.setModel(dtm);
    }


    public void showCliente(Client cs, JTextField j) {
        j.setText(cs.toString());
    }
}
