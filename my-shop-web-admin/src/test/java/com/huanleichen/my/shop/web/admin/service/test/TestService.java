package com.huanleichen.my.shop.web.admin.service.test;

import com.huanleichen.my.shop.domain.TbUser;
import com.huanleichen.my.shop.web.admin.service.TbUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml", "classpath:spring-context-druid.xml", "classpath:spring-context-mybatis.xml"})
public class TestService {
    @Autowired
    private TbUserService tbUserService;

    @Test
    public void testSelectAll() {
        List<TbUser> list = tbUserService.selectAll();

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }

    @Test
    public void testInsert() {
        TbUser tbUser = new TbUser("good", "1234545", "134254853", "787845@huanleichen.com", new Date(), new Date());

        tbUserService.insert(tbUser);
    }

    @Test
    public void testDelete() {
        tbUserService.delete(37);
    }

    @Test
    public void testGetTbUserById() {
        System.out.println(tbUserService.getTbUserById(36));
    }

    @Test
    public void testUpdate() {
        TbUser user = tbUserService.getTbUserById(36);

        user.setUsername("chen");
        user.setUpdated(new Date());

        tbUserService.update(user);
    }

    @Test
    public void testSelectByName() {
        List<TbUser> tbUsers = tbUserService.selectByName("uni");

        for (int i = 0; i < tbUsers.size(); i++) {
            System.out.println(tbUsers.get(i));
        }

    }
}
