package piko29.crud_todo_ver_1.person;

import org.springframework.stereotype.Service;

@Service
class PersonDtoMapper {
    PersonDto map(Person person) {//convert entity to dto, to get the data
        PersonDto dto = new PersonDto();
        dto.setId(person.getId());
        dto.setName(person.getName());
        dto.setPosition(person.getPosition());
        dto.setTelephone(person.getTelephone());
        dto.setEmail(person.getEmail());
        return dto;
    }

    Person map(PersonDto dto) {//convert dto to entity, to save the data
        Person person = new Person();
        person.setId(dto.getId());
        person.setName(dto.getName());
        person.setPosition(dto.getPosition());
        person.setTelephone(dto.getTelephone());
        person.setEmail(dto.getEmail());
        return person;
    }
}
