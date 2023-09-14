package piko29.crud_todo_ver_1.task;

import jakarta.persistence.*;
import piko29.crud_todo_ver_1.person.Person;

import java.time.LocalDateTime;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //genereting id on database site
    private Long id;
    private String title;
    private String description;
    private String category;
    private LocalDateTime dateAdded;
    private LocalDateTime dateDeadline;
    private Boolean taskFinished;

    @ManyToOne(optional = false)//adding task without person is forbidden
    @JoinColumn(name = "person_id")//information with foreign key has to be in task table
    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }
    public LocalDateTime getDateDeadline() { return dateDeadline; }

    public void setDateDeadline(LocalDateTime dateDeadline) { this.dateDeadline = dateDeadline; }

    public Boolean getTaskFinished() { return taskFinished; }

    public void setTaskFinished(Boolean taskFinished) { this.taskFinished = taskFinished; }
}
