package uk.ac.chen.middleware.service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import uk.ac.chen.middleware.entity.vo.FamilyDto;
import uk.ac.chen.middleware.entity.vo.FamilyVO;

import javax.annotation.Resource;

/**
 * @author: Qiuyu
 */
@SpringBootTest
public class FamilyServiceTests {

    private static final Logger logger = LoggerFactory.getLogger(FamilyServiceTests.class);

    @Resource
    private FamilyService familyService;

    @Test
    void testGetFamilyTree() {
        Integer personId = 135;
        FamilyVO familyVO = familyService.getFamilyTreeByPersonId(personId);
        logger.info("Family Tree: {}", familyVO);
    }

    @Test
    void testGetWholeFamily() {
        Integer personId = 135;
        FamilyDto familyDto = familyService.getWholeFamilyByPersonId(personId);
        logger.info("Family: {}", familyDto);
    }
}
