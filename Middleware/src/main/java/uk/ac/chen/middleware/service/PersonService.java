package uk.ac.chen.middleware.service;

import uk.ac.chen.middleware.entity.PersonEntity;
import uk.ac.chen.middleware.entity.vo.PersonVO;

import java.util.List;

/**
 * @author: Qiuyu
 */
public interface PersonService {

    /**
     * Add a person
     * @param firstName first name
     * @param lastName last name
     * @param sex
     * @param birthYear
     * @param deathYear
     * @param address
     * @return person id
     */
    int addPerson(String firstName, String lastName, Integer sex,
                  Integer birthYear, Integer deathYear, String address);

    /**
     * Get person by full name
     * @param firstName first name
     * @param lastName last name
     * @return person entity
     */
    PersonEntity getPersonByFullName(String firstName, String lastName);

    /**
     * Get person by id
     * @param personId id
     * @return person entity
     */
    PersonEntity getPersonById(Integer personId);

    /**
     * Get personVo by id
     * @param personId id
     * @return PersonVO
     */
    PersonVO getPersonVOById(Integer personId);

    /**
     * List All persons
     * @return
     */
    List<PersonEntity> listPersons();

    /**
     * Delete person by id
     * @param personId
     * @return
     */
    int deletePersonById(Integer personId);
}
