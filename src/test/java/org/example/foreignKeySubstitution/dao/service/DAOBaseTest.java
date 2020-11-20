package org.example.foreignKeySubstitution.dao.service;

import org.example.foreignKeySubstitution.FKSApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FKSApplication.class)
@Transactional
public class DAOBaseTest {
}
