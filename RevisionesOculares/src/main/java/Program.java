import BDEntities.*;
import Interfaces.RevisionOcular;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.*;

public class Program {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            // Creating a client
            SwingUtilities.invokeLater(() -> {
                RevisionOcular frame = new RevisionOcular();
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
