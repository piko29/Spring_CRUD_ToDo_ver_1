package piko29.crud_todo_ver_1.task;

import org.springframework.stereotype.Service;
import piko29.crud_todo_ver_1.person.Person;
import piko29.crud_todo_ver_1.person.PersonRepository;


@Service
class TaskDtoMapper {
    //to handle setPersonId inject PersonRepository object
    private final PersonRepository personRepository;

    TaskDtoMapper(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    TaskDto map(Task task) {
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setCategory(task.getCategory());
        dto.setDateAdded(task.getDateAdded());
        dto.setDateDeadline(task.getDateDeadline());
        dto.setTaskFinished(task.getTaskFinished());
        dto.setPersonId(task.getPerson().getId());
        dto.setPersonName(task.getPerson().getName());
        dto.setPersonPosition(task.getPerson().getPosition());
        return dto;
    }
    Task map(TaskDto dto) {
        Task task = new Task();
        task.setId(dto.getId());
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setCategory(dto.getCategory());
        task.setDateAdded(dto.getDateAdded());
        task.setDateDeadline(dto.getDateDeadline());
        task.setTaskFinished(dto.getTaskFinished());
        Person person = personRepository.findById(dto.getPersonId()).orElseThrow();//if not exist throw server exception
        task.setPerson(person);//assigning task to person
        return task;
    }
}

