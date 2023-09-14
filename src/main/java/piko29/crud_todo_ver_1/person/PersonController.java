package piko29.crud_todo_ver_1.person;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
//endpoints
@RestController
@RequestMapping("/persons")// "/persons" before controller not to repeat everytime
class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{id}")//reading
    ResponseEntity<PersonDto> getPersonById(@PathVariable Long id) {
        return personService.getPersonById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/{id}/tasks")  //reading
    ResponseEntity<List<PersonTaskDto>> getPersonTasks(@PathVariable Long id){
        if (personService.getPersonById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(personService.getTasksByPersonId(id));
    }
    @PostMapping//saving
    ResponseEntity<PersonDto> savePerson(@RequestBody PersonDto person) {
        PersonDto savedPerson = personService.savePerson(person);
        URI savedPersonUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")//creating person with address of next id
                .buildAndExpand(savedPerson.getId())
                .toUri();
        return ResponseEntity.created(savedPersonUri).body(savedPerson);//answer 201 created
    }
    @PutMapping("/{id}")// <?> any type of data
    ResponseEntity<?> replacePerson(@PathVariable Long id, @RequestBody PersonDto person){
        return personService.replacePerson(id, person)
                .map(p -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());// answer 404
    }
    @DeleteMapping("/{id}")//deleting
    ResponseEntity<?> deletePerson(@PathVariable Long id){
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

}
