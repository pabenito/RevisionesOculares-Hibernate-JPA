import BDEntities.*;
import View.RevisionOcular;

import javax.persistence.*;
import javax.swing.*;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            List<Client> clients = (List<Client>) entityManager.createQuery("from Client").getResultList();
            //List<Client> clients = new ArrayList<>();

            SwingUtilities.invokeLater(() -> {
                RevisionOcular frame = new RevisionOcular(clients);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            });
            Client client = new Client();
            client.setNif("1");
            client.setNombre("Pepe");
            client.setApellidos("Perez");
            client.setEdad(24);

            // Persisting client
            entityManager.persist(client);

            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
