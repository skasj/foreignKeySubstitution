package org.example.foreignKeySubstitution.mapper.baseMapper;

import org.example.foreignKeySubstitution.constants.ExamRequestType;
import org.example.foreignKeySubstitution.mapper.MapperBaseTest;
import org.example.foreignKeySubstitution.mapper.baseMapper.ExamMapper;
import org.example.foreignKeySubstitution.modal.entity.Exam;
import org.example.foreignKeySubstitution.modal.entity.ExamRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;
import java.util.Arrays;

@Rollback
public class ExamMapperTest extends MapperBaseTest {

    @Resource
    private ExamMapper examMapper;

    private Exam record;

    @Before
    public void setUp() {
        ExamRequest examRequest1 = new ExamRequest(1, ExamRequestType.CHOICE_QUESTION.getTag());
        ExamRequest examRequest2 = new ExamRequest(2, ExamRequestType.COMPLETION.getTag());
        record = new Exam("1", Arrays.asList(examRequest1,examRequest2));
    }

    @Test
    public void insert() {
        examMapper.insert(record);
    }

    @Test
    public void findById() {
        examMapper.insert(record);
        Exam examRecord = examMapper.findById(record.getId());
        Assert.assertEquals(record,examRecord);
    }
}