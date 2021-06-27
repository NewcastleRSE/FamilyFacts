package uk.ac.chen.middleware.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author: Qiuyu
 */
@Data
public class FamilyDto {
    FamilyVO father;
    FamilyVO mother;
    PersonVO person;
    List<PersonVO> children;
}
