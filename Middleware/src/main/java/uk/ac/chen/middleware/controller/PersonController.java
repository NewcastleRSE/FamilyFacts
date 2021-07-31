package uk.ac.chen.middleware.controller;

import org.springframework.web.bind.annotation.*;
import uk.ac.chen.middleware.entity.PersonEntity;
import uk.ac.chen.middleware.entity.results.JsonResult;
import uk.ac.chen.middleware.entity.results.Result;
import uk.ac.chen.middleware.entity.results.ResultCode;
import uk.ac.chen.middleware.entity.vo.PersonVO;
import uk.ac.chen.middleware.entity.vo.RelationshipVO;
import uk.ac.chen.middleware.service.PersonService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Qiuyu
 */
@RestController
@RequestMapping("person/")
public class PersonController {

    @Resource
    private PersonService personService;

    @PostMapping("create")
    public JsonResult addPerson(@RequestParam("first_name") String firstName,
                                @RequestParam("last_name") String lastName,
                                @RequestParam("sex") String sex,
                                @RequestParam("birth") Integer birth,
                                @RequestParam("death") Integer death,
                                @RequestParam("address") String address) {
        int sexCode = "male".equals(sex) ? 0 : 1;
        personService.addPerson(firstName, lastName, sexCode, birth, death, address);
        return Result.success();
    }

    @PostMapping("create/spouse")
    public JsonResult addSpouse(@RequestParam("person_id") Integer personId,
                                @RequestParam("first_name") String firstName,
                                @RequestParam("last_name") String lastName,
                                @RequestParam("sex") String sex,
                                @RequestParam("birth") Integer birth,
                                @RequestParam("death") Integer death,
                                @RequestParam("address") String address) {
        int sexCode = "male".equals(sex) ? 0 : 1;
        int id = personService.addSpouse(personId, firstName, lastName, sexCode, birth, death, address);
        return id > 0 ? Result.success() : Result.fail(ResultCode.PERSON_NOT_FOUND);
    }

    @PostMapping("create/father")
    public JsonResult addFather(@RequestParam("person_id") Integer personId,
                                @RequestParam("first_name") String firstName,
                                @RequestParam("last_name") String lastName,
                                @RequestParam("sex") String sex,
                                @RequestParam("birth") Integer birth,
                                @RequestParam("death") Integer death,
                                @RequestParam("address") String address) {
        int sexCode = "male".equals(sex) ? 0 : 1;
        int id = personService.addFather(personId, firstName, lastName, sexCode, birth, death, address);
        return id > 0 ? Result.success() : Result.fail(ResultCode.PERSON_NOT_FOUND);
    }

    @PostMapping("create/mother")
    public JsonResult addMother(@RequestParam("person_id") Integer personId,
                                @RequestParam("first_name") String firstName,
                                @RequestParam("last_name") String lastName,
                                @RequestParam("sex") String sex,
                                @RequestParam("birth") Integer birth,
                                @RequestParam("death") Integer death,
                                @RequestParam("address") String address) {
        int sexCode = "male".equals(sex) ? 0 : 1;
        int id = personService.addMother(personId, firstName, lastName, sexCode, birth, death, address);
        return id > 0 ? Result.success() : Result.fail(ResultCode.PERSON_NOT_FOUND);
    }

    @PostMapping("update")
    public JsonResult updatePerson(@RequestParam("person_id") Integer personId,
                                   @RequestParam("first_name") String firstName,
                                   @RequestParam("last_name") String lastName,
                                   @RequestParam("sex") String sex,
                                   @RequestParam("birth") String birth,
                                   @RequestParam("death") String death,
                                   @RequestParam("address") String address) {
        Integer birthDay = ("".equals(birth) || "null".equals(birth) || birth == null) ? 0
                : Integer.parseInt(birth);
        Integer deathDay = ("".equals(death) || "null".equals(death) || death == null) ? 0
                : Integer.parseInt(death);
        PersonVO person = new PersonVO(personId, firstName, lastName, sex, birthDay, deathDay, address);
        int id = personService.updatePerson(person);
        return id == personId ? Result.success() : Result.fail(ResultCode.PERSON_NOT_FOUND);
    }

    @PostMapping("update/father")
    public JsonResult updateFatherOfPerson(@RequestParam("person_id") Integer personId,
                                           @RequestParam("father_id") Integer fatherId) {
        int id = personService.updateFatherOfPerson(personId, fatherId);
        return id > 0 ? Result.success() : Result.fail(ResultCode.PERSON_NOT_FOUND);
    }

    @PostMapping("update/mother")
    public JsonResult updateMotherOfPerson(@RequestParam("person_id") Integer personId,
                                           @RequestParam("mother_id") Integer fatherId) {
        int id = personService.updateMotherOfPerson(personId, fatherId);
        return id > 0 ? Result.success() : Result.fail(ResultCode.PERSON_NOT_FOUND);
    }

    @PostMapping("update/spouse")
    public JsonResult updateSpouseOfPerson(@RequestParam("person_id") Integer personId,
                                           @RequestParam("spouse_id") Integer fatherId) {
        int id = personService.updateSpouseOfPerson(personId, fatherId);
        return id > 0 ? Result.success() : Result.fail(ResultCode.PERSON_NOT_FOUND);
    }


    @GetMapping("search")
    public JsonResult<List<PersonVO>> getPersonByFullName(@RequestParam("first_name") String firstName,
                                                          @RequestParam("last_name") String lastName) {
        List<PersonEntity> persons = personService.getPersonByFullName(firstName, lastName);
        List<PersonVO> personVos = new ArrayList<>();
        for (PersonEntity person : persons) {
            personVos.add(personService.getPersonVOById(person.getPersonId()));
        }
        if (personVos.size() == 0) {
            return Result.fail(ResultCode.PERSON_NOT_FOUND);
        } else {
            return Result.success(personVos);
        }
    }

    @GetMapping("search/{person_id}")
    public JsonResult<PersonVO> getPersonById(@PathVariable("person_id") Integer personId) {
        PersonVO personVO = personService.getPersonVOById(personId);
        if (personVO == null || personVO.getPersonId() == null) {
            return Result.fail(ResultCode.PERSON_NOT_FOUND);
        } else {
            return Result.success(personVO);
        }
    }

    @GetMapping("list")
    public JsonResult<List<PersonVO>> listPersons() {
        List<PersonEntity> persons = personService.listPersons();
        List<PersonVO> personVos = new ArrayList<>();
        for (PersonEntity person : persons) {
            PersonVO personVO = personService.getPersonVOById(person.getPersonId());
            personVos.add(personVO);
        }
        return Result.success(personVos);
    }

    @PostMapping("delete/{person_id}")
    public JsonResult deletePersonById(@PathVariable("person_id") Integer personId) {
        if (personService.deletePersonById(personId) > 0) {
            return Result.success();
        } else {
            return Result.fail();
        }
    }

    @GetMapping("relationship/{person_id}")
    public JsonResult<RelationshipVO> getAllRelatedPersons(@PathVariable("person_id") Integer personId) {
        if (personId == null) {
            return Result.fail(ResultCode.PARAM_NOT_VALID);
        }
        RelationshipVO relationship = personService.getAllRelatedPersons(personId);
        if (relationship == null || relationship.getPerson() == null
                || relationship.getPerson().getPersonId() == null) {
            return Result.fail(ResultCode.PERSON_NOT_FOUND);
        } else {
            return Result.success(relationship);
        }
    }

    @PostMapping("unlink/father")
    public JsonResult unlinkOfFather(@RequestParam("person_id") Integer personId,
                                     @RequestParam("father_id") Integer fatherId) {
        int id = personService.unlinkOfFather(personId, fatherId);
        return id > 0 ? Result.success() : Result.fail(ResultCode.PERSON_NOT_FOUND);
    }

    @PostMapping("unlink/mother")
    public JsonResult unlinkOfMother(@RequestParam("person_id") Integer personId,
                                     @RequestParam("mother_id") Integer motherId) {
        int id = personService.unlinkOfMother(personId, motherId);
        return id > 0 ? Result.success() : Result.fail(ResultCode.PERSON_NOT_FOUND);
    }

    @PostMapping("unlink/spouse")
    public JsonResult unlinkOfSpouse(@RequestParam("person_id") Integer personId,
                                     @RequestParam("spouse_id") Integer spouseId) {
        int id = personService.unlinkOfSpouse(personId, spouseId);
        return id > 0 ? Result.success() : Result.fail(ResultCode.PERSON_NOT_FOUND);
    }
}
