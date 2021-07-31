package uk.ac.chen.middleware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.stereotype.Service;
import uk.ac.chen.middleware.entity.*;
import uk.ac.chen.middleware.entity.vo.PersonVO;
import uk.ac.chen.middleware.entity.vo.RelationshipVO;
import uk.ac.chen.middleware.mapper.*;
import uk.ac.chen.middleware.service.FamilyService;
import uk.ac.chen.middleware.service.NameService;
import uk.ac.chen.middleware.service.PersonService;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Resource
    private FamilyService familyService;

    @Resource
    private FamilyMapper familyMapper;

    @Override
    public int addPerson(String firstName, String lastName, Integer sex,
                         Integer birthYear, Integer deathYear, String address) {

        AddressEntity addressEntity = new AddressEntity();
        if (address != null && !"".equals(address)) {
            addressEntity.setName(address);
            addressMapper.insert(addressEntity);
        }

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
    public int addSpouse(Integer personId, String firstName, String lastName, Integer sex,
                         Integer birthYear, Integer deathYear, String address) {
        int spouseId = addPerson(firstName, lastName, sex,birthYear, deathYear, address);
        return updateSpouseOfPerson(personId, spouseId);
    }

    @Override
    public int addFather(Integer personId, String firstName, String lastName, Integer sex,
                         Integer birthYear, Integer deathYear, String address) {
        int fatherId = addPerson(firstName, lastName, sex, birthYear, deathYear, address);
        return updateFatherOfPerson(personId, fatherId);
    }

    @Override
    public int addMother(Integer personId, String firstName, String lastName, Integer sex,
                         Integer birthYear, Integer deathYear, String address) {
        int motherId = addPerson(firstName, lastName, sex,birthYear, deathYear, address);
        return updateMotherOfPerson(personId, motherId);
    }



    @Override
    public List<PersonEntity> getPersonByFullName(String firstName, String lastName) {
        List<PersonEntity> persons = new ArrayList<>();
        List<NameEntity> names = nameService.getNameListByFullName(firstName, lastName);
        for (NameEntity name : names) {
            if (name == null || name.getOwnerId() == null) {
                continue;
            } else {
                persons.add(getPersonById(name.getOwnerId()));
            }
        }

        return persons;
    }

    @Override
    public PersonEntity getPersonById(Integer personId) {
        return personMapper.selectById(personId);
    }

    @Override
    public PersonVO getPersonVOById(Integer personId) {
        PersonVO personVo = setPersonVOById(personId);
        PersonEntity person = getPersonById(personId);
        if (personVo != null && person != null && person.getSpouseId() != null) {
            PersonVO spouse = setPersonVOById(person.getSpouseId());
            personVo.setSpouse(spouse);
        }
        return personVo;
    }

    private PersonVO setPersonVOById(Integer personId) {
        PersonEntity person = getPersonById(personId);
        if (person == null) {
            return null;
        }
        NameEntity fullName = nameService.getNameEntityByPersonId(personId);
        String sex = person.getSex() == 0 ? "male" : "female";
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
        if (nameEntity != null && nameEntity.getNameId() != null) {
            nameMapper.deleteById(nameEntity.getNameId());
        }
        if (personEntity != null && personEntity.getLiving() != null) {
            addressMapper.deleteById(personEntity.getLiving());
        }

        if (personEntity != null && personEntity.getSpouseId() != null) {
            PersonEntity spouse = getPersonById(personEntity.getSpouseId());
            if (spouse != null) {
                spouse.setSpouseId(null);
                personMapper.updateById(spouse);
            }
        }

        if (personEntity != null && personEntity.getParentId() != null) {
            familyMapper.deleteById(personEntity.getParentId());
        }

        QueryWrapper<FamilyEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("FatherID", personId);
        List<FamilyEntity> families = familyMapper.selectList(queryWrapper);
        if (families.size() > 0) {
            for (FamilyEntity family : families) {
                family.setFatherId(null);
                familyMapper.updateById(family);
            }
        }

        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("MotherID", personId);
        families = familyMapper.selectList(queryWrapper);
        if (families.size() > 0) {
            for (FamilyEntity family : families) {
                family.setMotherId(null);
                familyMapper.updateById(family);
            }
        }

        return personMapper.deleteById(personId);
    }

    @Override
    public int updatePerson(PersonVO personVO) {
        if (personVO.getPersonId() == null) {
            return -1;
        }
        // person
        PersonEntity personEntity = getPersonById(personVO.getPersonId());
        if (personEntity == null || personEntity.getPersonId() == null
                || !personEntity.getPersonId().equals(personVO.getPersonId())) {
            return -1;
        }
        String sex = personVO.getSex();
        int sexCode = "male".equals(sex) ? 0 : 1;
        personEntity.setSex(sexCode);
        personMapper.updateById(personEntity);

        // name
        NameEntity nameEntity = nameService.getNameEntityByPersonId(personVO.getPersonId());
        if (nameEntity == null) {
            nameEntity = new NameEntity(personVO.getFirstName(), personVO.getLastName(),
                    personVO.getPersonId(), personVO.getBirth(), personVO.getDeath());
            nameMapper.insert(nameEntity);
        } else {
            nameEntity.setGiven(personVO.getFirstName());
            nameEntity.setSurname(personVO.getLastName());
            nameEntity.setBirthYear(personVO.getBirth());
            nameEntity.setDeathYear(personVO.getDeath());
            nameMapper.updateById(nameEntity);
        }

        // address
        Integer living = personEntity.getLiving();
        AddressEntity addressEntity = addressMapper.selectById(living);
        if (addressEntity == null) {
            addressEntity = new AddressEntity();
            addressEntity.setName(personVO.getAddress());
            addressMapper.insert(addressEntity);
            personEntity.setLiving(addressEntity.getAddressId());
            personMapper.updateById(personEntity);

            AddressLinkEntity addressLinkEntity = new AddressLinkEntity();
            addressLinkEntity.setAddressId(addressEntity.getAddressId());
            addressLinkEntity.setOwnerId(personVO.getPersonId());
            addressLinkMapper.insert(addressLinkEntity);
        } else {
            addressEntity.setName(personVO.getAddress());
            addressMapper.updateById(addressEntity);
        }
        return personVO.getPersonId();
    }

    @Override
    public int updateFatherOfPerson(Integer personId, Integer fatherId) {
        PersonEntity personEntity = getPersonById(personId);
        if (personEntity == null || !personEntity.getPersonId().equals(personId)) {
            return -1;
        }

        FamilyEntity familyEntity;
        if (personEntity.getParentId() == null || personEntity.getParentId() <= 0) {
            familyEntity = new FamilyEntity();
            familyEntity.setFatherId(fatherId);
            familyMapper.insert(familyEntity);

            personEntity.setParentId(familyEntity.getFamilyId());
            personMapper.updateById(personEntity);
        } else {
            familyEntity = familyService.getFamilyByFamilyId(personEntity.getParentId());
            familyEntity.setFatherId(fatherId);
            familyMapper.updateById(familyEntity);
        }
        return familyEntity.getFamilyId();
    }

    @Override
    public int updateMotherOfPerson(Integer personId, Integer motherId) {
        PersonEntity personEntity = getPersonById(personId);
        if (personEntity == null || !personEntity.getPersonId().equals(personId)) {
            return -1;
        }

        FamilyEntity familyEntity;
        if (personEntity.getParentId() == null || personEntity.getParentId() <= 0) {
            familyEntity = new FamilyEntity();
            familyEntity.setMotherId(motherId);
            familyMapper.insert(familyEntity);

            personEntity.setParentId(familyEntity.getFamilyId());
            personMapper.updateById(personEntity);
        } else {
            familyEntity = familyService.getFamilyByFamilyId(personEntity.getParentId());
            familyEntity.setMotherId(motherId);
            familyMapper.updateById(familyEntity);
        }
        return familyEntity.getFamilyId();
    }

    @Override
    public int updateSpouseOfPerson(Integer personId, Integer spouseId) {
        PersonEntity personEntity = getPersonById(personId);
        if (personEntity == null || !personEntity.getPersonId().equals(personId)) {
            return -1;
        }
        personEntity.setSpouseId(spouseId);
        personMapper.updateById(personEntity);

        PersonEntity spouseEntity = getPersonById(spouseId);
        spouseEntity.setSpouseId(personId);
        personMapper.updateById(spouseEntity);
        return spouseId;
    }

    @Override
    public RelationshipVO getAllRelatedPersons(Integer personId) {
        RelationshipVO relationship = new RelationshipVO();
        PersonEntity personEntity = getPersonById(personId);
        if (personEntity == null || personEntity.getPersonId() == null) {
            return null;
        }
        //himself/herself
        PersonVO person = getPersonVOById(personId);
        relationship.setPerson(person);

        //get parent
        if (personEntity.getParentId() != null && personEntity.getParentId() > 0) {
            FamilyEntity familyEntity = familyService.getFamilyByFamilyId(personEntity.getParentId());
            if (familyEntity != null) {
                if (familyEntity.getFatherId() != null) {
                    PersonVO father = getPersonVOById(familyEntity.getFatherId());
                    relationship.setFather(father);
                }
                if (familyEntity.getMotherId() != null) {
                    PersonVO mother = getPersonVOById(familyEntity.getMotherId());
                    relationship.setMother(mother);
                }
            }
        }

        //get spouse
        if (personEntity.getSpouseId() != null ) {
            PersonVO spouse = getPersonVOById(personEntity.getSpouseId());
            if (spouse != null) {
                relationship.setSpouse(spouse);
            }
        }

        // get brothers and sisters
        List<PersonVO> broAndSis = familyService.getBrothersAndSisters(personId);
        relationship.setBrothersAndSisters(broAndSis);

        // get children
        List<PersonVO> children = new ArrayList<>();
        List<PersonEntity> personEntities = personMapper.selectList(null);
        for (PersonEntity p : personEntities) {
            if (p.getParentId() == null) {
                continue;
            }
            FamilyEntity f = familyService.getFamilyByFamilyId(p.getParentId());
            if (f == null) {
                continue;
            }
            if ((f.getFatherId() != null && f.getFatherId().equals(personId)) ||
                    (f.getMotherId() != null && f.getMotherId().equals(personId))) {
                children.add(getPersonVOById(p.getPersonId()));
            }
        }
        if (children.size() > 0) {
            relationship.setChildren(children);
        }
        return relationship;
    }

    @Override
    public int unlinkOfFather(Integer personId, Integer fatherId) {
        PersonEntity person = getPersonById(personId);
        if (person == null || !person.getPersonId().equals(personId)) {
            return -1;
        }
        if (person.getParentId() == null || person.getParentId() <= 0) {
            return personId;
        }
        FamilyEntity f = familyService.getFamilyByFamilyId(person.getParentId());
        if (f != null && f.getFatherId() != null && f.getFatherId().equals(fatherId)) {
            UpdateWrapper<FamilyEntity> uw = new UpdateWrapper<>();
            uw.set("FatherID", null);
            uw.eq("FamilyID", f.getFamilyId());
            familyMapper.update(f, uw);
            if (f.getMotherId() == null) {
                familyMapper.deleteById(f.getFamilyId());
                UpdateWrapper<PersonEntity> uwP = new UpdateWrapper<>();
                uwP.set("ParentID", null);
                uwP.eq("PersonID", personId);
                personMapper.update(person, uwP);
            }
        }
        return personId;
    }

    @Override
    public int unlinkOfMother(Integer personId, Integer motherId) {
        PersonEntity person = getPersonById(personId);
        if (person == null || !person.getPersonId().equals(personId)) {
            return -1;
        }
        if (person.getParentId() == null || person.getParentId() <= 0) {
            return personId;
        }
        FamilyEntity f = familyService.getFamilyByFamilyId(person.getParentId());
        if (f != null && f.getMotherId() != null && f.getMotherId().equals(motherId)) {
            UpdateWrapper<FamilyEntity> uw = new UpdateWrapper<>();
            uw.set("MotherID", null);
            uw.eq("FamilyID", f.getFamilyId());
            familyMapper.update(f, uw);
            if (f.getFatherId() == null) {
                familyMapper.deleteById(f.getFamilyId());
                UpdateWrapper<PersonEntity> uwP = new UpdateWrapper<>();
                uwP.set("ParentID", null);
                uwP.eq("PersonID", personId);
                personMapper.update(person, uwP);
            }
        }
        return personId;
    }

    @Override
    public int unlinkOfSpouse(Integer personId, Integer spouseId) {
        PersonEntity person = getPersonById(personId);
        if (person == null || !person.getPersonId().equals(personId)) {
            return -1;
        }
        if (person.getSpouseId() != null && person.getSpouseId().equals(spouseId)) {
            UpdateWrapper<PersonEntity> uw = new UpdateWrapper<>();
            uw.set("SpouseID", null);
            uw.eq("PersonID", personId);
            personMapper.update(person, uw);
            PersonEntity spouse = getPersonById(spouseId);
            if (spouse != null) {
                UpdateWrapper<PersonEntity> uwSpouse = new UpdateWrapper<>();
                uwSpouse.set("SpouseID", null);
                uwSpouse.eq("PersonID", spouse.getPersonId());
                personMapper.update(person, uwSpouse);
            }
        }
        return personId;
    }
}
