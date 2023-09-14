package piko29.crud_todo_ver_1.person;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
class PersonService {
    private PersonRepository personRepository;
    private PersonDtoMapper personDtoMapper;
    private PersonTaskDtoMapper personTaskDtoMapper;

    public PersonService(PersonRepository personRepository, PersonDtoMapper personDtoMapper,
                         PersonTaskDtoMapper personTaskDtoMapper) {
        this.personRepository = personRepository;
        this.personDtoMapper = personDtoMapper;
        this.personTaskDtoMapper = personTaskDtoMapper;
    }
    Optional<PersonDto> getPersonById(Long id) {
        return personRepository.findById(id).map(personDtoMapper::map);
    }
    List<PersonTaskDto> getTasksByPersonId(Long personId) {
        return personRepository.findById(personId)
                .map(Person::getTaskList)
                .orElse(Collections.emptyList())//if there is no Company make empty list
                .stream()
                .map(personTaskDtoMapper::map)
                .toList();
    }

    PersonDto savePerson(PersonDto personDto) {
        Person person = personDtoMapper.map(personDto);//convert dto to entity
        Person savedPerson = personRepository.save(person);//saving entity in database
        return personDtoMapper.map(savedPerson);//returning entity to dto
    }

    Optional<PersonDto> replacePerson(Long personId, PersonDto personDto) {
        if(!personRepository.existsById(personId)) {
            return Optional.empty();//if does not exists
        }
        personDto.setId(personId);//setting id
        Person personToUpdate = personDtoMapper.map(personDto);//convert dto to entity
        Person updatedPerson = personRepository.save(personToUpdate);//saving updated entity
        return Optional.of(personDtoMapper.map(updatedPerson));
    }
    void deletePerson(Long id){
        personRepository.deleteById(id);
    }

}
