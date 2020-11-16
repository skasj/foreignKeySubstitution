package org.example.foreignKeySubstitution.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@Slf4j
@MybatisTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MapperBaseTest {
}