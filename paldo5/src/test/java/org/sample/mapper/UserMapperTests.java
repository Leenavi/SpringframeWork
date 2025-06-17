package org.sample.mapper;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.domain.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
@Transactional
public class UserMapperTests {

    @Autowired
    private UserMapper userMapper;
/*
    @Test
    public void testInsert() {
        UserVO vo = UserVO.builder()
                .nickname("testname")
                .pwd("1234")
                .email("test@test.test")
                .build();
        userMapper.insertUser(vo);
        log.info("Inserted user id: " + vo);
    }

    @Test
    public void testRead() {
		log.info(userMapper.readUser(1L));
    }

    @Test
    public void testUpdate() {
        UserVO vo = UserVO.builder()
                .userid(1L)
                .nickname("updatedName")
                .pwd("5678")
                .email("updated@test.test")
                .build();
		userMapper.updateUser(vo);
		log.info(vo);
    }
*/
    @Test
    public void testDelete() {
        int result = userMapper.deleteUser(3L);
        log.info("Delete result: " + result);

    }
}
