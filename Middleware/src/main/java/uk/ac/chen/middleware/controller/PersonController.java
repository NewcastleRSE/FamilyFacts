package uk.ac.chen.middleware.controller;

import org.springframework.web.bind.annotation.*;
import uk.ac.chen.middleware.entity.PersonEntity;
import uk.ac.chen.middleware.entity.results.JsonResult;
import uk.ac.chen.middleware.entity.results.Result;
import uk.ac.chen.middleware.entity.results.ResultCode;
import uk.ac.chen.middleware.entity.vo.PersonVO;
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


    @GetMapping("search")
    public JsonResult<PersonVO> getPersonByFullName(@RequestParam("first_name") String firstName,
                                                    @RequestParam("last_name") String lastName) {
        PersonEntity personEntity = personService.getPersonByFullName(firstName, lastName);
        PersonVO personVo = personService.getPersonVOById(personEntity.getPersonId());
        if (personVo.getPersonId() == null) {
            return Result.fail(ResultCode.PERSON_NOT_FOUND);
        } else {
            return Result.success(personVo);
        }
    }

    @GetMapping("search/{person_id}")
    public JsonResult<PersonVO> getPersonById(@PathVariable("person_id") Integer personId) {
        PersonVO personVO = personService.getPersonVOById(personId);
        if (personVO.getPersonId() == null) {
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

    @GetMapping("delete/{person_id}")
    public JsonResult deletePersonById(@PathVariable("person_id") Integer personId) {
        if (personService.deletePersonById(personId) > 0) {
            return Result.success();
        } else {
            return Result.fail();
        }
    }
}
