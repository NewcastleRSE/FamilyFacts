package uk.ac.chen.middleware.service.impl;

import org.springframework.stereotype.Service;
import uk.ac.chen.middleware.entity.FamilyEntity;
import uk.ac.chen.middleware.entity.PersonEntity;
import uk.ac.chen.middleware.entity.vo.FamilyDto;
import uk.ac.chen.middleware.entity.vo.FamilyVO;
import uk.ac.chen.middleware.entity.vo.PersonVO;
import uk.ac.chen.middleware.mapper.FamilyMapper;
import uk.ac.chen.middleware.mapper.PersonMapper;
import uk.ac.chen.middleware.service.FamilyService;
import uk.ac.chen.middleware.service.PersonService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Qiuyu
 */
@Service("FamilyService")
public class FamilyServiceImpl implements FamilyService {

    @Resource
    private PersonService personService;

    @Resource
    private FamilyMapper familyMapper;

    @Resource
    private PersonMapper personMapper;

    @Override
    public FamilyVO getFamilyTreeByPersonId(Integer personId) {
        FamilyVO root = getFamilyNode(personId);
        dfs(root);
        return root;
    }

    /**
     * Use depth-first search to recursively find the person's parents
     * @param root
     */
    private void dfs(FamilyVO root) {
        if (root == null || root.getPersonId() == null) {
            return;
        }
        PersonEntity personEntity = personService.getPersonById(root.getPersonId());
        FamilyEntity familyEntity = getFamilyByFamilyId(personEntity.getParentId());
        if (familyEntity == null) {
            return;
        }
        if (familyEntity.getFatherId() != null) {
            root.setFather(getFamilyNode(familyEntity.getFatherId()));
            dfs(root.getFather());
        }
        if (familyEntity.getMotherId() != null) {
            root.setMother(getFamilyNode(familyEntity.getMotherId()));
            dfs(root.getMother());
        }
    }

    private FamilyVO getFamilyNode(Integer personId) {
        PersonVO personVO = personService.getPersonVOById(personId);
        if (personVO == null || personVO.getPersonId() == null) {
            return null;
        }
        FamilyVO node = new FamilyVO();
        node.setPersonId(personId);
        node.setPersonVO(personVO);
        return node;
    }

    @Override
    public FamilyEntity getFamilyByFamilyId(Integer familyId) {
        return familyMapper.selectById(familyId);
    }

    @Override
    public FamilyDto getWholeFamilyByPersonId(Integer personId) {
        FamilyDto familyDto = new FamilyDto();
        PersonEntity personEntity = personService.getPersonById(personId);
        if (personEntity == null || personEntity.getPersonId() == null) {
            return null;
        }

        PersonVO person = personService.getPersonVOById(personId);
        familyDto.setPerson(person);
        FamilyEntity familyEntity;
        if (personEntity.getParentId() != null) {
            Integer fatherId = null;
            Integer motherId = null;
            // parents
            familyEntity = getFamilyByFamilyId(personEntity.getParentId());
            if (familyEntity != null && familyEntity.getFatherId() != null) {
                fatherId = familyEntity.getFatherId();
                FamilyVO father = getFamilyTreeByPersonId(fatherId);
                familyDto.setFather(father);
            }
            if (familyEntity != null && familyEntity.getMotherId() != null) {
                motherId = familyEntity.getMotherId();
                FamilyVO mother = getFamilyTreeByPersonId(motherId);
                familyDto.setMother(mother);
            }

            // no parents, no bothers and sisters
            if (motherId == null && fatherId == null) {
                return familyDto;
            }

            // bothers and sisters
            //List<PersonVO> children = getBrothersAndSisters(personId);
            //familyDto.setChildren(children);
        }
        // get children
        List<PersonVO> children = new ArrayList<>();
        List<PersonEntity> personEntities = personMapper.selectList(null);
        for (PersonEntity p : personEntities) {
            if (p.getParentId() == null) {
                continue;
            }
            FamilyEntity f = getFamilyByFamilyId(p.getParentId());
            if (f == null) {
                continue;
            }
            if ((f.getFatherId() != null && f.getFatherId().equals(personId)) ||
                    (f.getMotherId() != null && f.getMotherId().equals(personId))) {
                children.add(personService.getPersonVOById(p.getPersonId()));
            }
        }
        if (children.size() > 0) {
            familyDto.setChildren(children);
        }
        return familyDto;
    }

    @Override
    public List<PersonVO> getBrothersAndSisters(Integer personId) {
        PersonEntity personEntity = personService.getPersonById(personId);
        if (personEntity == null || personEntity.getPersonId() == null
                || personEntity.getParentId() == null) {
            return null;
        }
        FamilyEntity familyEntity = getFamilyByFamilyId(personEntity.getParentId());
        if (familyEntity == null) {
            return null;
        }
        Integer fatherId = familyEntity.getFatherId();
        Integer motherId = familyEntity.getMotherId();
        if (motherId == null && fatherId == null) {
            return null;
        }
        List<PersonVO> children = new ArrayList<>();
        List<PersonEntity> persons = personService.listPersons();
        if (persons != null && persons.size() > 0) {
            for (PersonEntity p : persons) {
                if (p.getPersonId() == null || p.getParentId() == null ||
                        p.getPersonId().equals(personId)) {
                    continue;
                }
                FamilyEntity f = getFamilyByFamilyId(p.getParentId());
                if (f == null || f.getFamilyId() == null) {
                    continue;
                }
                boolean motherIdEqNull = f.getMotherId() == null && motherId == null;
                boolean fatherIdEqNull = f.getFatherId() == null && fatherId == null;
                boolean motherIdEq = f.getMotherId() != null && f.getMotherId() > 0
                        && f.getMotherId().equals(motherId);
                boolean fatherIdEq = f.getFatherId() != null && f.getFatherId() > 0
                        && f.getFatherId().equals(fatherId);
                if ((motherIdEqNull && fatherIdEq) || (fatherIdEqNull && motherIdEq)
                        || (motherIdEq && fatherIdEq)) {
                    PersonVO personVO = personService.getPersonVOById(p.getPersonId());
                    children.add(personVO);
                }
            }
        }
        return children.size() > 0 ? children : null;
    }
}
