package piko29.crud_todo_ver_1.task;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/tasks")
class TaskController {
    private final TaskService taskService;
    private final ObjectMapper objectMapper;//object used in applypatch

    public TaskController(TaskService taskService, ObjectMapper objectMapper) {
        this.taskService = taskService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/{id}")//using ResponseEntity instead of Optional not to get code 200 ok with null
    ResponseEntity<TaskDto> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<TaskDto> saveTask(@RequestBody TaskDto taskDto){
        TaskDto savedTask = taskService.saveTask(taskDto);
        URI savedTaskUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedTask.getId())
                .toUri();
        return ResponseEntity.created(savedTaskUri).body(savedTask);

    }

    @PatchMapping("/{id}")
    ResponseEntity<?> updateTask(@PathVariable Long id,@RequestBody JsonMergePatch patch){
        try {//if task does not exist throw exception
            TaskDto task = taskService.getTaskById(id).orElseThrow();//downloading entity from database
            TaskDto taskPatched = applyPatch(task, patch); //adding patch, updating
            taskService.updateTask(taskPatched);//saving in database
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.noContent().build();//answer with code 204
    }
    private TaskDto applyPatch(TaskDto task, JsonMergePatch patch) throws JsonPatchException, JsonProcessingException {
        JsonNode taskNode = objectMapper.valueToTree(task);//valueToTree changes dto to json
        JsonNode taskPatchedNode = patch.apply(taskNode);
        return objectMapper.treeToValue(taskPatchedNode, TaskDto.class);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

}
