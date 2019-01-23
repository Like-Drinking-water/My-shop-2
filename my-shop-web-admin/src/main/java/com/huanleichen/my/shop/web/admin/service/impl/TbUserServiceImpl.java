package com.huanleichen.my.shop.web.admin.service.impl;

import com.huanleichen.my.shop.commons.dto.BaseResult;
import com.huanleichen.my.shop.commons.utils.RegexpUtils;
import com.huanleichen.my.shop.domain.TbUser;
import com.huanleichen.my.shop.web.admin.dao.TbUserDao;
import com.huanleichen.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@Service
public class TbUserServiceImpl implements TbUserService {
    Logger logger = LoggerFactory.getLogger(TbUserServiceImpl.class);

    @Autowired
    private TbUserDao tbUserDao;

    @Override
    public List<TbUser> selectAll() {
        return tbUserDao.selectAll();
    }

    @Override
    public void delete(Long id) {
        tbUserDao.delete(id);
    }

    @Override
    public TbUser getTbUserById(Long id) {
        return tbUserDao.getTbUserById(id);
    }

    @Override
    public void update(TbUser tbUser) {
        tbUserDao.update(tbUser);
    }

    @Override
    public List<TbUser> selectByName(String username) {
        return tbUserDao.selectByName(username);
    }

    @Override
    public TbUser login(String email, String password, boolean remember) {
        TbUser tbUser = tbUserDao.selectByEmail(email);

        if (tbUser != null) {
            tbUser.setRemember(remember);
            //将明文密码转换成密文
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
            //再与数据库中的密码进行比较
            //如果不匹配则将 tbUser 指向 null
            if (!md5Password.equals(tbUser.getPassword())) {
                tbUser = null;
            }
        }

        return tbUser;
    }

    @Override
    public BaseResult save(TbUser tbUser) {
        BaseResult baseResult = check(tbUser);

        //如果用户的信息格式填写正确
        if (baseResult.getStatus() == BaseResult.SUCCESS_STATUS) {
            tbUser.setUpdated(new Date());
            //如果 tbUser 中没有保存 id 信息，则说明要新增用户
            if (tbUser.getId() == null) {
                tbUser.setCreated(new Date());
                tbUserDao.insert(tbUser);
                baseResult.setMessage("添加成功");
            } else {
                tbUserDao.update(tbUser);
                baseResult.setMessage("修改成功");
            }
        }

        return baseResult;
    }

    @Override
    public List<TbUser> search(TbUser tbUser) {
        return tbUserDao.search(tbUser);
    }

    /**
     * 检查提交的用户信息是否符合规范
     * @param tbUser 提交的用户信息
     * @return 操作的结果集
     */
    private BaseResult check(TbUser tbUser) {
        BaseResult baseResult = null;

        if (StringUtils.isBlank(tbUser.getEmail())) {
            baseResult = BaseResult.failResult("邮箱不能为空");
        }

        else if (!RegexpUtils.checkEmail(tbUser.getEmail())) {
            baseResult = BaseResult.failResult("邮箱格式不正确");
        }

        else if (StringUtils.isBlank(tbUser.getPassword())) {
            baseResult = BaseResult.failResult("密码不能为空");
        }

        else if (StringUtils.isBlank(tbUser.getUsername())) {
            baseResult = BaseResult.failResult("用户名不能为空");
        }

        else if (StringUtils.isBlank(tbUser.getPhone())) {
            baseResult = BaseResult.failResult("手机不能为空");
        }

        else if (!RegexpUtils.checkPhone(tbUser.getPhone())) {
            baseResult = BaseResult.failResult("手机号格式不正确");
        } else {
            baseResult = BaseResult.successResult();
        }

        return baseResult;
    }
}
