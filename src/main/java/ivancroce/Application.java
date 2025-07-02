package ivancroce;

import ivancroce.dao.EventDAO;
import ivancroce.entities.Event;
import ivancroce.entities.EventType;
import ivancroce.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Application {
    // 1. STEP: create the emf to connect the DB
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("events_management_2_pu");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager(); // em manages all the operations with the DB
        EventDAO ed = new EventDAO(em); // instance of our DAO, passing em

        // creating a new event
        Event birthday = new Event("Aldo Baglio's b-day", LocalDate.of(2025, 07, 01), "Big Party!", EventType.PRIVATE, 30);
        Event concert = new Event("Aldo Baglio's Concert", LocalDate.of(2025, 10, 12), "Music concert", EventType.PUBLIC, 100);

        ed.save(birthday); // to comment this one otherwise save method keeps creating the event in the DB
        ed.save(concert);

        try {
            Event aldoFromDB = ed.findById("519d2fc1-c1c5-4bab-9d8a-4e1737253f21");
            System.out.println(aldoFromDB); // findById method

            // ed.findByIdAndDelete(3); to delete

        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        em.close();
        emf.close();
    }
}
