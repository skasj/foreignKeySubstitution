package org.example.foreignKeySubstitution.mapper.baseMapper;

import org.example.foreignKeySubstitution.dao.service.DAOBaseTest;
import org.example.foreignKeySubstitution.mapper.MapperBaseTest;
import org.example.foreignKeySubstitution.modal.entity.ClassScheduleCard;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ClassScheduleCardMapperTest extends DAOBaseTest {

    private ClassScheduleCard scheduleCard1;
    private ClassScheduleCard scheduleCard2;

    @Resource
    private ClassScheduleCardMapper classScheduleCardMapper;

    @Before
    public void init() {
        scheduleCard1 = new ClassScheduleCard(1, new Date(), 3, 0, -1);
        scheduleCard2 = new ClassScheduleCard(2, new Date(0), 3, 0, -1);
    }

    @Test
    public void insert() {
        classScheduleCardMapper.insert(scheduleCard1);
        classScheduleCardMapper.insert(scheduleCard2);
        List<ClassScheduleCard> fromDBList = classScheduleCardMapper.selectByIdList(
                Arrays.asList(scheduleCard2.getId(),scheduleCard1.getId()));
        Assert.assertEquals(2,fromDBList.size());
    }

    @Test
    public void deleteByIdList() {
        classScheduleCardMapper.insert(scheduleCard1);
        classScheduleCardMapper.insert(scheduleCard2);
        List<Object> idList = Arrays.asList(scheduleCard2.getId(), scheduleCard1.getId());
        List<ClassScheduleCard> fromDBList1 = classScheduleCardMapper.selectByIdList(idList);
        Assert.assertEquals(2,fromDBList1.size());
        classScheduleCardMapper.deleteByIdList(idList);
        List<ClassScheduleCard> fromDBList2 = classScheduleCardMapper.selectByIdList(idList);
        Assert.assertEquals(0,fromDBList2.size());
    }
}