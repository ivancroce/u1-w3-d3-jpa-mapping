package ivancroce.dao;

import ivancroce.entities.Event;
import ivancroce.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class EventDAO {

    // em is our "contact" with the DB
    private EntityManager em;

    public EventDAO(EntityManager em) {
        this.em = em;
    }

    // save method (create)
    public void save(Event newEvent) {

        // 1. we ask the em to create a new transaction.
        EntityTransaction transaction = em.getTransaction();

        // 2. begin transaction.
        transaction.begin();

        // 3. add newEvent to the Persistence Context with persist (at this stage newEvent is still not saved in table in the DB)
        em.persist(newEvent);

        // 4. commit = write data in the DB
        transaction.commit();
        System.out.println("The event '" + newEvent.getTitle() + "' has been successfully saved in the DB.");
    }

    // findId method
    public Event findById(String eventId) {
        Event found = em.find(Event.class, UUID.fromString(eventId)); // Event.class to go look in the right table
        if (found == null)
            throw new NotFoundException(eventId);
        return found;
    }

    // delete
    public void findByIdAndDelete(String eventId) {

        // Find the event in the DB through the id
        Event found = this.findById(eventId);

        // ask em to create a new transaction
        EntityTransaction transaction = em.getTransaction();

        // begin transaction
        transaction.begin();

        // Remove the event from the Persistence Context with remove (at this stage it's still not removed)
        em.remove(found);

        // commit to remove it from the DB
        transaction.commit();

        System.out.println("Event '" + found.getId() + "' has been successfully deleted.");
    }
}
