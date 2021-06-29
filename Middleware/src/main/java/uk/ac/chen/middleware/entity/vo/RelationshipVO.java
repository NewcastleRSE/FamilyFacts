package uk.ac.chen.middleware.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author: Qiuyu
 */
@Data
public class RelationshipVO {
    PersonVO person;
    PersonVO father;
    PersonVO mother;
    PersonVO spouse;
    List<PersonVO> brothersAndSisters;
    List<PersonVO> children;
}
