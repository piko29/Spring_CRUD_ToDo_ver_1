package piko29.crud_todo_ver_1.task;

import java.time.LocalDateTime;

class TaskDto {
    private Long id;
    private String title;
    private String description;
    private String category;
    private LocalDateTime dateAdded;
    private LocalDateTime dateDeadline;
    private Boolean taskFinished;
    private Long personId;
    private String personName;
    private String personPosition;

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

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonPosition() {
        return personPosition;
    }

    public void setPersonPosition(String personPosition) {
        this.personPosition = personPosition;
    }

    public LocalDateTime getDateDeadline() { return dateDeadline; }

    public void setDateDeadline(LocalDateTime dateDeadline) { this.dateDeadline = dateDeadline; }

    public Boolean getTaskFinished() { return taskFinished; }

    public void setTaskFinished(Boolean taskFinished) { this.taskFinished = taskFinished; }
}
