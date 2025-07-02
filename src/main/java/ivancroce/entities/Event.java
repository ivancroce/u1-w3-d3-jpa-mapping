package ivancroce.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity // Tells to JPA that this class must be mapped to a specific table
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // same as serial/bigserial (value that auto-increment)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "event_date", nullable = false)
    private LocalDate eventDate;

    @Column(name = "description")
    private String description;

    @Column(name = "event_type")
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Column(name = "max_num_participants")
    private int maxNumParticipants;

    // Constructor
    // JPA needs an empty constructor to create objects for us when we read from the DB
    public Event() {
    }

    public Event(String title, LocalDate eventDate, String description, EventType eventType, int maxNumParticipants) {
        this.title = title;
        this.eventDate = eventDate;
        this.description = description;
        this.eventType = eventType;
        this.maxNumParticipants = maxNumParticipants;
    }

    public long getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public int getMaxNumParticipants() {
        return maxNumParticipants;
    }

    public void setMaxNumParticipants(int maxNumParticipants) {
        this.maxNumParticipants = maxNumParticipants;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", eventDate=" + eventDate +
                ", description='" + description + '\'' +
                ", eventType=" + eventType +
                ", maxNumParticipants=" + maxNumParticipants +
                '}';
    }
}
