package com.gabriel.multipledatabaseconnection.controller;

import com.gabriel.logging.LogExecutionDetails;
import com.gabriel.logging.LogExecutionTime;
import com.gabriel.multipledatabaseconnection.model.Person;
import com.gabriel.multipledatabaseconnection.repository.inmemory.PersonRepositoryInMemory;
import com.gabriel.multipledatabaseconnection.repository.persistent.PersonRepositoryPersistent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@EnableTransactionManagement
@RestController
@RequestMapping(value = "/api/person/")
public class MultipleDatabaseController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PersonRepositoryPersistent personRepositoryPersistent;

    @Autowired
    private PersonRepositoryInMemory personRepositoryInMemory;


    @LogExecutionDetails
    @RequestMapping(value = "{idNumber}" , method = RequestMethod.GET)
    @Transactional("inmemoryDatabaseTransactionManager")
    public Person findOneByIdNumber(@PathVariable("idNumber") Long idNumber) {

        Person result = personRepositoryInMemory.findFirstByIdNumber(idNumber);

        return new Person();
    }

    @LogExecutionTime
    @RequestMapping(value = "newperson" , method = RequestMethod.POST, consumes="application/json")
    public Person savePerson(@RequestBody Person person) {

        logger.info("Hi");
        return savePersonPersistent(savePersonInMemory(person));
    }

    @LogExecutionDetails
    @Transactional("persistentDatabaseTransactionManager")
    public Person savePersonPersistent(@RequestBody Person person) {

        return personRepositoryPersistent.save(person);

    }

    @LogExecutionDetails
    @Transactional("inmemoryDatabaseTransactionManager")
    public Person savePersonInMemory(@RequestBody Person person) {

        return personRepositoryInMemory.save(person);

    }

}
