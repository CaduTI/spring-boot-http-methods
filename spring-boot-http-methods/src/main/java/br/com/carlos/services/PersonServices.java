package br.com.carlos.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.carlos.exceptions.ResourceNotFoundException;
import br.com.carlos.models.Person;
import br.com.carlos.repositories.PersonRepository;

@Service
public class PersonServices {
	@Autowired
	PersonRepository repository;
	
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	private List<Person> persons = new ArrayList<>();
	
	
	public Person createPerson(Person person) {
		logger.info("Created.");
		return repository.save(person);
	}
	
	public Person findById(Integer id) {
		
		Person person = new Person();
		for(int i = 0; i<persons.size(); i++) {
			logger.info("posição da pessoa na lista: "+ persons.get(i).getId());
			if(persons.get(i).getId() == id) {
				person.setId(persons.get(i).getId());
				person.setFirstName(persons.get(i).getFirstName());
				person.setLastName(persons.get(i).getLastName());				
			}
		}
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found with this Id."));
	}
	
	
	public Person updatePerson(Person person) {
		logger.info("Updating Person");
		Person entity= repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Person not found with this Id."));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		
		return repository.save(person);
	}

	public void deletePerson(Integer id) {
		logger.info("Deleting person.");
		Person entity= repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Person not found with this Id."));
		repository.delete(entity);
	}
}
