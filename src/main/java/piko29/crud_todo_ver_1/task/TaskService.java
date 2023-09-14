package piko29.crud_todo_ver_1.task;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
class TaskService {
    private TaskRepository taskRepository;
    private TaskDtoMapper taskDtoMapper;

    public TaskService(TaskRepository taskRepository, TaskDtoMapper taskDtoMapper) {
        this.taskRepository = taskRepository;
        this.taskDtoMapper = taskDtoMapper;
    }
    Optional<TaskDto> getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(taskDtoMapper::map);
    }
    TaskDto saveTask(TaskDto taskDto){
        Task taskToSave = taskDtoMapper.map(taskDto);
        taskToSave.setDateAdded(LocalDateTime.now());//pobieranie czasu
        Task savedTask = taskRepository.save(taskToSave);
        return taskDtoMapper.map(savedTask);
    }
    void updateTask(TaskDto taskDto){//update z merge patch aktualizacja
        Task task = taskDtoMapper.map(taskDto);
        taskRepository.save(task);
    }
    void deleteTask(Long id){
        taskRepository.deleteById(id);
    }
}
