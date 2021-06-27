package uk.ac.chen.middleware.service;

import uk.ac.chen.middleware.entity.FamilyEntity;
import uk.ac.chen.middleware.entity.vo.FamilyDto;
import uk.ac.chen.middleware.entity.vo.FamilyVO;

/**
 * @author: Qiuyu
 */
public interface FamilyService {
    /**
     * Get family tree
     * @param personId
     * @return FamilyVO
     */
    FamilyVO getFamilyTreeByPersonId(Integer personId);

    /**
     * Get family by id
     * @param familyId
     * @return
     */
    FamilyEntity getFamilyByFamilyId(Integer familyId);

    /**
     * Get the whole family by person id
     * @param personId a member of the family
     * @return
     */
    FamilyDto getWholeFamilyByPersonId(Integer personId);
}
