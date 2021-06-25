package uk.ac.chen.middleware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import uk.ac.chen.middleware.entity.NameEntity;
import uk.ac.chen.middleware.mapper.NameMapper;
import uk.ac.chen.middleware.service.NameService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Qiuyu
 */
@Service("NameService")
public class NameServiceImpl implements NameService {

    @Resource
    private NameMapper nameMapper;

    @Override
    public List<NameEntity> getNameListByFullName(String firstName, String lastName) {
        QueryWrapper<NameEntity> queryWrapper = new QueryWrapper<>();
        boolean firstNameNotNull = firstName != null && !"".equals(firstName);
        boolean lastNameNotNull = lastName != null && !"".equals(lastName);
        if (firstNameNotNull && lastNameNotNull) {
            queryWrapper.eq("Surname", lastName).eq("Given", firstName);
        } else if (!firstNameNotNull && !lastNameNotNull) {
            return nameMapper.selectList(null);
        } else if (lastNameNotNull){
            queryWrapper.eq("Surname", lastName);
        } else {
            queryWrapper.eq("Given", firstName);
        }
        return nameMapper.selectList(queryWrapper);
    }

    @Override
    public NameEntity getNameEntityByPersonId(Integer personId) {
        QueryWrapper<NameEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("OwnerID", personId);
        return nameMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean addName(NameEntity nameEntity) {
        return nameMapper.insert(nameEntity) > 0;
    }

}
