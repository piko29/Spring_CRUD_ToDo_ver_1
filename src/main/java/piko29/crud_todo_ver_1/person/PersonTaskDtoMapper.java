package piko29.crud_todo_ver_1.person;

import org.springframework.stereotype.Service;
import piko29.crud_todo_ver_1.task.Task;

@Service
class PersonTaskDtoMapper {
    PersonTaskDto map(Task task){
        PersonTaskDto dto = new PersonTaskDto();
        dto.setId(task.getId());
        dto.setCategory(task.getCategory());
        dto.setTitle(task.getTitle());
        return dto;
    }
}
