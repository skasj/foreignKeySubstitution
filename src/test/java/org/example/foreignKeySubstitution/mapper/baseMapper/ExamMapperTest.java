package org.example.foreignKeySubstitution.mapper.baseMapper;

import org.example.foreignKeySubstitution.mapper.MapperBaseTest;
import org.example.foreignKeySubstitution.modal.entity.Exam;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;

@Rollback
public class ExamMapperTest extends MapperBaseTest {

    @Resource
    private ExamMapper examMapper;

    private Exam record;

    @Before
    public void setUp() {
        record = new Exam("1","{}");

    }

    @Test
    public void insert() {
        examMapper.insert(record);
    }
}