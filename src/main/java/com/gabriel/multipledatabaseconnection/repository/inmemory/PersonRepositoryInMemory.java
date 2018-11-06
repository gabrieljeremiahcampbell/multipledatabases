package com.gabriel.multipledatabaseconnection.repository.inmemory;

import com.gabriel.multipledatabaseconnection.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PersonRepositoryInMemory extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {

    Person findFirstByIdNumber(Long idNumber);
}
