package com.gabriel.multipledatabaseconnection.controller;



import com.gabriel.multipledatabaseconnection.model.Person;
import com.gabriel.multipledatabaseconnection.repository.inmemory.PersonRepositoryInMemory;
import com.gabriel.multipledatabaseconnection.repository.persistent.PersonRepositoryPersistent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@EnableTransactionManagement
@RestController
@RequestMapping(value = "/api/person/")
public class MultipleDatabaseController {

    @Autowired
    private PersonRepositoryPersistent personRepositoryPersistent;

    @Autowired
    private PersonRepositoryInMemory personRepositoryInMemory;



    @RequestMapping(value = "{idNumber}" , method = RequestMethod.GET)
    @Transactional("inmemoryDatabaseTransactionManager")
    public Person findOneByIdNumber(@PathVariable("idNumber") Long idNumber) {


        Person result = personRepositoryInMemory.findFirstByIdNumber(idNumber);


        return new Person();
    }

    @RequestMapping(value = "newperson" , method = RequestMethod.POST, consumes="application/json")
    public Person savePerson(@RequestBody Person person) {

        return savePersonPersistent(savePersonInMemory(person));
    }

    @Transactional("persistentDatabaseTransactionManager")
    public Person savePersonPersistent(@RequestBody Person person) {

        return personRepositoryPersistent.save(person);

    }

    @Transactional("inmemoryDatabaseTransactionManager")
    public Person savePersonInMemory(@RequestBody Person person) {

        return personRepositoryInMemory.save(person);

    }

}
