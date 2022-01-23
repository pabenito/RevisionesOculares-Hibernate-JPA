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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
            //eye.setNif(in.getClienteSeleccionado().getNif());
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
            //eye.setNif(in.getClienteSeleccionado().getNif());
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
        //FillTable(in);
        FillFields(in);
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

    public void FillTable(dosINteraz in){

        DefaultTableModel dtm = new DefaultTableModel();
        String[] columns = in.getColumns();
        for (int i = 0; i < columns.length; i++) {
            dtm.addColumn(columns[i]);
        }

        List<Eye> eyes = in.getEyes();
        Client cl = in.getClienteSeleccionado();

        for (int i = 0; i < eyes.size(); i++) {
            dtm.addRow(new Object[]{eyes.get(i).getId(), cl.getNif(),eyes.get(i).getConsulta(),
                    eyes.get(i).getOdEsfera(), eyes.get(i).getOdCilindro(), eyes.get(i).getOdAdicion(), eyes.get(i).getOdAgudeza(),
                    eyes.get(i).getOiEsfera(), eyes.get(i).getOiCilindro(), eyes.get(i).getOiAdicion(), eyes.get(i).getOiAgudeza()});
        }
        JTable table = in.getTable1();
        table.setModel(dtm);
        in.setTable1(table);
        in.setSeleccionado(null);
    }

    public void FillFields(dosINteraz dIn) {
        JTable table1 = dIn.getTable1();
        int idx = table1.getSelectedRow();
        if(idx == -1 && dIn.getSeleccionado()==null){

            dIn.gettID_ADICION().setText("");
            dIn.gettID_AGUDEZA().setText("");
            dIn.gettID_CILINDRO().setText("");
            dIn.gettID_ESFERA().setText("");
            dIn.gettOD_ADICION().setText("");
            dIn.gettOD_AGUDEZA().setText("");
            dIn.gettOD_CILINDRO().setText("");
            dIn.gettOD_AGUDEZA().setText("");
            dIn.gettOD_ESFERA().setText("");
        }else{
            dIn.setSeleccionado(new Eye());
            Eye sel = dIn.getSeleccionado();


            String sDate = String.valueOf(table1.getValueAt(idx, 1));
            Date date = null;
            try {
                date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }


            sel.setId(Integer.valueOf((Integer) table1.getValueAt(idx, 0)));
            sel.setNif(String.valueOf(table1.getValueAt(idx, 1)));
            sel.setConsulta((java.sql.Date) date);
            sel.setOdEsfera(Double.valueOf((Double) table1.getValueAt(idx, 3)));
            sel.setOdCilindro(Double.valueOf((Double) table1.getValueAt(idx, 4)));
            sel.setOdAdicion(Double.valueOf((Double) table1.getValueAt(idx, 5)));
            sel.setOdAgudeza(Double.valueOf((Double) table1.getValueAt(idx, 6)));
            sel.setOiEsfera(Double.valueOf((Double) table1.getValueAt(idx, 7)));
            sel.setOiCilindro(Double.valueOf((Double) table1.getValueAt(idx, 8)));
            sel.setOiAdicion(Double.valueOf((Double) table1.getValueAt(idx, 9)));
            sel.setOiAgudeza(Double.valueOf((Double) table1.getValueAt(idx, 10)));

            dIn.gettID_ADICION().setText("" + sel.getOiAdicion());
            dIn.gettID_AGUDEZA().setText("" + sel.getOiAgudeza());
            dIn.gettID_CILINDRO().setText("" + sel.getOdCilindro());
            dIn.gettID_ESFERA().setText("" + sel.getOdEsfera());
            dIn.gettOD_ADICION().setText("" + sel.getOdAdicion());
            dIn.gettOD_AGUDEZA().setText("" + sel.getOdAgudeza());
            dIn.gettOD_CILINDRO().setText("" + sel.getOdCilindro());
            dIn.gettOD_ESFERA().setText("" + sel.getOdAgudeza());
            dIn.getJCalendar1().setDate(sel.getConsulta());
        }
    }
}