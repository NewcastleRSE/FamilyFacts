package uk.ac.chen.middleware.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import uk.ac.chen.middleware.entity.PersonEntity;
import uk.ac.chen.middleware.entity.vo.PersonVO;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Qiuyu
 */

@SpringBootTest
public class PersonServiceTests {

    private static final Logger logger = LoggerFactory.getLogger(PersonServiceTests.class);

    @Resource
    private PersonService personService;

    @Test
    void testAddPerson() {
        String firstName = "test";
        String lastName = "sister1";
        Integer sex = 0;
        Integer birthYear = 1920;
        Integer deathYear = 2010;
        String address = "Newcastle";
        personService.addPerson(firstName, lastName, sex, birthYear, deathYear, address);
    }

    @Test
    void testUpdateSpouse() {
        Integer personId = 135;
        Integer spouseId = 136;
        int id = personService.updateSpouseOfPerson(personId, spouseId);
        Assertions.assertEquals(spouseId, id);
    }

    @Test
    void testUpdateFather() {
        Integer personId = 140;
        Integer fatherId = 137;
        int id = personService.updateFatherOfPerson(personId, fatherId);
        Assertions.assertTrue(id >= 0);
    }

    @Test
    void testUpdateMother() {
        Integer personId = 140;
        Integer motherId = 138;
        int id = personService.updateMotherOfPerson(personId, motherId);
        Assertions.assertTrue(id >= 0);
    }

    @Test
    void testAddSpouse() {
        Integer personId = 135;
        String firstName = "test";
        String lastName = "spouse";
        Integer sex = 1;
        Integer birthYear = 1920;
        Integer deathYear = 2010;
        String address = "Newcastle";
        personService.addSpouse(personId, firstName, lastName, sex, birthYear, deathYear, address);
    }

    @Test
    void testAddMother() {
        Integer personId = 135;
        String firstName = "test";
        String lastName = "mother";
        Integer sex = 1;
        Integer birthYear = 1890;
        Integer deathYear = 1970;
        String address = "Newcastle";
        personService.addMother(personId, firstName, lastName, sex, birthYear, deathYear, address);
    }

    @Test
    void testAddFather() {
        Integer personId = 135;
        String firstName = "test";
        String lastName = "father";
        Integer sex = 0;
        Integer birthYear = 1890;
        Integer deathYear = 1970;
        String address = "Newcastle";
        personService.addFather(personId, firstName, lastName, sex, birthYear, deathYear, address);
    }

    @Test
    void testUpdatePerson() {
        Integer personId = 136;
        String firstName = "test2";
        String lastName = "spouse";
        String sex = "female";
        Integer birthYear = 1920;
        Integer deathYear = 2010;
        String address = "Newcastle";
        PersonVO personVO = new PersonVO(personId, firstName, lastName, sex, birthYear, deathYear, address);
        personService.updatePerson(personVO);
    }

    @Test
    void testSearchPerson() {
        String firstName = "test2";
        String lastName = "spouse";
        List<PersonEntity> persons = personService.getPersonByFullName(firstName, lastName);
        logger.info("persons: {}", persons);
    }

    @Test
    void testSearchPersonById() {
        int personId = 136;
        PersonVO personVO = personService.getPersonVOById(personId);
        logger.info("personVo: {}", personVO);
    }

    @Test
    void testListPersons() {
        List<PersonEntity> persons = personService.listPersons();
        for (PersonEntity person : persons) {
            logger.info("person: {}", person);
            PersonVO personVO = personService.getPersonVOById(person.getPersonId());
            logger.info("personVo: {}", personVO);
        }
    }

    @Test
    void testDeletePerson() {
        int res = personService.deletePersonById(138);
        personService.deletePersonById(137);
        personService.deletePersonById(136);
        personService.deletePersonById(135);
        logger.info("delete person : {}", res);
    }

}
