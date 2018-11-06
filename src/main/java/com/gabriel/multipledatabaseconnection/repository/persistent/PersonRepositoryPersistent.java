package com.gabriel.multipledatabaseconnection.repository.persistent;

import com.gabriel.multipledatabaseconnection.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PersonRepositoryPersistent extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {

    Person findFirstByIdNumber(Long idNumber);
}
