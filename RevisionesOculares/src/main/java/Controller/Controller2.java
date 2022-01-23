package Controller;
import BDEntities.Client;
import BDEntities.Eye;
import View.*;


import javax.persistence.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Controller2 {

    public void onAdd(dosINteraz in){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            Eye eye = new Eye();
            eye.setId(in.getSeleccionado().getId()+1);
            Random r = new Random();

            eye.setId(r.nextInt(100));
            eye.setNif(in.getClienteSeleccionado().getNif());

            Date date = in.getJCalendar1().getDate();
            java.sql.Date date1 = SQLDate(date);
            eye.setConsulta(date1);

            eye.setOdEsfera(Double.parseDouble(in.gettOD_ESFERA().getText()));
            eye.setOdCilindro(Double.parseDouble(in.gettOD_CILINDRO().getText()));
            eye.setOdAdicion(Double.parseDouble(in.gettOD_ADICION().getText()));
            eye.setOdAgudeza(Double.parseDouble(in.gettOD_AGUDEZA().getText()));

            eye.setOiEsfera(Double.parseDouble(in.gettID_ESFERA().getText()));
            eye.setOiCilindro(Double.parseDouble(in.gettID_CILINDRO().getText()));
            eye.setOiAdicion(Double.parseDouble(in.gettID_ADICION().getText()));
            eye.setOiAgudeza(Double.parseDouble(in.gettID_AGUDEZA().getText()));


            entityManager.persist(eye);

            List<Eye> eyes = (List<Eye>) entityManager.createQuery("SELECT e from Eye e WHERE e.nif=" +
                    in.getClienteSeleccionado().getNif()).getResultList();
            in.setEyes(eyes);

            onClean(in);
            transaction.commit();
        } catch (PersistenceException e){
            onClean(in);
        } finally {
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

            Eye eye = entityManager.find(Eye.class, in.getSeleccionado().getId());
            //Eye eye = new Eye();
            //eye.setId(in.getSeleccionado().getId());
            //eye.setNif(in.getClienteSeleccionado().getNif());

            Date date = in.getJCalendar1().getDate();
            java.sql.Date date1 = SQLDate(date);
            eye.setConsulta(date1);

            eye.setOdEsfera(Double.parseDouble(in.gettOD_ESFERA().getText()));
            eye.setOdCilindro(Double.parseDouble(in.gettOD_CILINDRO().getText()));
            eye.setOdAdicion(Double.parseDouble(in.gettOD_ADICION().getText()));
            eye.setOdAgudeza(Double.parseDouble(in.gettOD_AGUDEZA().getText()));
            eye.setOiEsfera(Double.parseDouble(in.gettID_ESFERA().getText()));
            eye.setOiCilindro(Double.parseDouble(in.gettID_CILINDRO().getText()));
            eye.setOiAdicion(Double.parseDouble(in.gettID_ADICION().getText()));
            eye.setOiAgudeza(Double.parseDouble(in.gettID_AGUDEZA().getText()));

            in.getSeleccionado().setConsulta(date1);
            in.getSeleccionado().setOdEsfera(eye.getOdEsfera());
            in.getSeleccionado().setOdCilindro(eye.getOdCilindro());
            in.getSeleccionado().setOdAdicion(eye.getOdAdicion());
            in.getSeleccionado().setOdAgudeza(eye.getOdAgudeza());
            in.getSeleccionado().setOiEsfera(eye.getOiEsfera());
            in.getSeleccionado().setOiCilindro(eye.getOiCilindro());
            in.getSeleccionado().setOiAdicion(eye.getOiAdicion());
            in.getSeleccionado().setOiAgudeza(eye.getOiAgudeza());


            entityManager.merge(eye);

            List<Eye> eyes = (List<Eye>) entityManager.createQuery("SELECT e from Eye e WHERE e.nif=" +
                    in.getClienteSeleccionado().getNif()).getResultList();
            in.setEyes(eyes);

            onClean(in);
            transaction.commit();
        } catch (PersistenceException e){
            onClean(in);
        } finally {
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

            List<Eye> eyes = (List<Eye>) entityManager.createQuery("SELECT e from Eye e WHERE e.nif=" +
                                in.getClienteSeleccionado().getNif()).getResultList();
            in.setEyes(eyes);

            onClean(in);
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

    public void onClean(dosINteraz in) {
        in.setSeleccionado(null);
        FillTable(in);
        FillFields(in);
    }

    public void onExit(dosINteraz in) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            List<Client> clients = (List<Client>) entityManager.createQuery("from Client").getResultList();

            in.dispose();

            SwingUtilities.invokeLater(() -> {
                RevisionOcular frame = new RevisionOcular(clients);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            });

            transaction.commit();
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

            dIn.getJCalendar1().setDate(new Date());
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


            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");;
            String sDate = String.valueOf(table1.getValueAt(idx, 2));
            Date date = null;
            try {
                date = simpleDateFormat.parse(sDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            java.sql.Date date1 = SQLDate(date);

            sel.setId(Integer.valueOf((Integer) table1.getValueAt(idx, 0)));
            sel.setNif(String.valueOf(table1.getValueAt(idx, 1)));
            sel.setConsulta(date1);
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
            dIn.gettID_CILINDRO().setText("" + sel.getOiCilindro());
            dIn.gettID_ESFERA().setText("" + sel.getOiEsfera());
            dIn.gettOD_ADICION().setText("" + sel.getOdAdicion());
            dIn.gettOD_AGUDEZA().setText("" + sel.getOdAgudeza());
            dIn.gettOD_CILINDRO().setText("" + sel.getOdCilindro());
            dIn.gettOD_ESFERA().setText("" + sel.getOdEsfera());
            dIn.getJCalendar1().setDate(sel.getConsulta());
        }
    }

    private java.sql.Date SQLDate(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = simpleDateFormat.format(date);
        java.sql.Date date1 = java.sql.Date.valueOf(formattedDate);
        return date1;
    }
}