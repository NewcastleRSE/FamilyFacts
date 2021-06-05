package uk.ac.chen.middleware.service.impl;

import org.springframework.stereotype.Service;
import uk.ac.chen.middleware.entity.AddressEntity;
import uk.ac.chen.middleware.entity.AddressLinkEntity;
import uk.ac.chen.middleware.entity.NameEntity;
import uk.ac.chen.middleware.entity.PersonEntity;
import uk.ac.chen.middleware.entity.vo.PersonVO;
import uk.ac.chen.middleware.mapper.AddressLinkMapper;
import uk.ac.chen.middleware.mapper.AddressMapper;
import uk.ac.chen.middleware.mapper.NameMapper;
import uk.ac.chen.middleware.mapper.PersonMapper;
import uk.ac.chen.middleware.service.NameService;
import uk.ac.chen.middleware.service.PersonService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Qiuyu
 */
@Service("PersonService")
public class PersonServiceImpl implements PersonService {

    @Resource
    private PersonMapper personMapper;

    @Resource
    private NameService nameService;

    @Resource
    private NameMapper nameMapper;

    @Resource
    private AddressMapper addressMapper;

    @Resource
    private AddressLinkMapper addressLinkMapper;

    @Override
    public int addPerson(String firstName, String lastName, Integer sex,
                         Integer birthYear, Integer deathYear, String address) {

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setName(address);
        addressMapper.insert(addressEntity);

        PersonEntity personEntity = new PersonEntity();
        personEntity.setSex(sex);
        personEntity.setLiving(addressEntity.getAddressId());
        personMapper.insert(personEntity);
        int personId = personEntity.getPersonId();

        AddressLinkEntity addressLinkEntity = new AddressLinkEntity();
        addressLinkEntity.setAddressId(addressEntity.getAddressId());
        addressLinkEntity.setOwnerId(personId);
        addressLinkMapper.insert(addressLinkEntity);

        nameService.addName(new NameEntity(firstName, lastName, personId, birthYear, deathYear));

        return personId;
    }

    @Override
    public PersonEntity getPersonByFullName(String firstName, String lastName) {
        NameEntity nameEntity = nameService.getNameEntityByFullName(firstName, lastName);
        return getPersonById(nameEntity.getOwnerId());
    }

    @Override
    public PersonEntity getPersonById(Integer personId) {
        return personMapper.selectById(personId);
    }

    @Override
    public PersonVO getPersonVOById(Integer personId) {
        PersonEntity person = getPersonById(personId);
        NameEntity fullName = nameService.getNameEntityByPersonId(personId);
        String sex = person.getSex() == 0 ? "female" : "male";
        AddressEntity addressEntity = addressMapper.selectById(person.getLiving());
        String address = addressEntity == null ? " " : addressEntity.getName();
        if (fullName == null) {
            return new PersonVO(personId, " ", " ", sex, 0, 0, address);
        }
        Integer birth = fullName.getBirthYear() == null ? 0 : fullName.getBirthYear();
        Integer death = fullName.getDeathYear() == null ? 0 : fullName.getDeathYear();
        PersonVO personVo = new PersonVO(personId, fullName.getGiven(), fullName.getSurname(), sex,
                birth, death, address);
        return personVo;
    }

    @Override
    public List<PersonEntity> listPersons() {
        return personMapper.selectList(null);
    }

    @Override
    public int deletePersonById(Integer personId) {
        PersonEntity personEntity = getPersonById(personId);
        NameEntity nameEntity = nameService.getNameEntityByPersonId(personId);
        if (nameEntity.getNameId() != null) {
            nameMapper.deleteById(nameEntity.getNameId());
        }
        if (personEntity.getLiving() != null) {
            addressMapper.deleteById(personEntity.getLiving());
        }
        return personMapper.deleteById(personId);
    }
}
