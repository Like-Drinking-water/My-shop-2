package com.huanleichen.my.shop.web.admin.service.impl;

import com.huanleichen.my.shop.commons.dto.BaseResult;
import com.huanleichen.my.shop.commons.dto.PageInfo;
import com.huanleichen.my.shop.commons.validator.BeanValidator;
import com.huanleichen.my.shop.domain.TbUser;
import com.huanleichen.my.shop.web.admin.dao.TbUserDao;
import com.huanleichen.my.shop.web.admin.service.TbUserService;
import com.huanleichen.my.shop.web.admin.service.abstracts.AbstractBaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbUserServiceImpl extends AbstractBaseServiceImpl<TbUser, TbUserDao> implements TbUserService {

    @Override
    public TbUser login(String email, String password, boolean remember) {
        TbUser tbUser = dao.selectByEmail(email);

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
        BaseResult baseResult = null;
        String erroString = BeanValidator.validator(tbUser);
        String checkError = check(tbUser);

        if (StringUtils.isBlank(erroString) && !StringUtils.isBlank(checkError)) {
            erroString = "数据验证失败：<br />" + checkError;
        }
        else if (!StringUtils.isBlank(erroString) && !StringUtils.isBlank(checkError)) {
            erroString += checkError;
        }
        //错误信息，则将错误信息放在 BaseResult 中
        if (!StringUtils.isBlank(erroString)) {
            baseResult = BaseResult.failResult(erroString);
        }
        //没有错误信息
        else {
            baseResult = BaseResult.successResult();
        }

        return super.save(tbUser, baseResult);
    }



    @Override
    public boolean isEmailExist(String email, TbUser tbUser) {
        //如果 tbUser 的 id 为空
        //则表示在添加用户，则新增用户的邮箱不能与之前任一用户的邮箱相同
        if (tbUser.getId() == null) {
            return dao.selectByEmail(email) != null;
        }
        //表示修改用户信息时
        else {
            //如果邮箱不变的化，则直接返回 false
            if (email.equals(dao.getById(tbUser.getId()).getEmail())) {
                return false;
            }

            else {
                return dao.selectByEmail(email) != null;
            }
        }

    }

    /**
     * 检查提交的用户信息是否符合规范
     * @param tbUser 提交的用户信息
     * @return 操作的结果集
     */
    private String check(TbUser tbUser) {
        StringBuilder sb = new StringBuilder();

        if (isEmailExist(tbUser.getEmail(), tbUser)) {
            sb.append("⭐ 邮箱已存在<br />");
        }
        if (tbUser.getId() == null) {
            if(StringUtils.isBlank(tbUser.getPassword())) {
                sb.append("⭐ 密码不能为空,且密码的长度应该在 6 - 30 之间<br />");
            } else if (tbUser.getPassword().length() < 6 || tbUser.getPassword().length() > 30) {
                sb.append("⭐ 密码的长度应该在 6 - 30 之间<br />");
            }
        } else {
            if(!StringUtils.isBlank(tbUser.getPassword())) {
                if (tbUser.getPassword().length() < 6 || tbUser.getPassword().length() > 30) {
                    sb.append("⭐ 密码的长度应该在 6 - 30 之间<br />");
                }
            }
        }

        return sb.toString();
    }

}
