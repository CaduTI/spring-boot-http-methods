package br.com.carlos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.carlos.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>{}
