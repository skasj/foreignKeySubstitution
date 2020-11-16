package org.example.foreignKeySubstitution.mapper.baseMapper;

import org.example.foreignKeySubstitution.mapper.MapperBaseTest;
import org.example.foreignKeySubstitution.modal.entity.Class;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;

@Rollback
public class ClassMapperTest extends MapperBaseTest {

    @Resource
    private ClassMapper classMapper;

    private Class aClass;

    @Before
    public void init(){
        aClass = new Class(4,1,1);
    }

    @Test
    public void insert() {
        Assert.assertEquals(1,classMapper.insert(aClass));
    }

//    @Test
//    public void selectByPrimaryKey() {
//        Assert.assertEquals(aClass,classMapper.selectByPrimaryKey(aClass.getId()));
//    }
}