package com.huanleichen.my.shop.web.admin.service.impl;

import com.huanleichen.my.shop.commons.dto.BaseResult;
import com.huanleichen.my.shop.commons.dto.PageInfo;
import com.huanleichen.my.shop.commons.validator.BeanValidator;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void deleteMulti(String[] ids) {
        tbUserDao.deleteMulti(ids);
    }

    @Override
    public PageInfo<TbUser> getPage(int start, int length, int draw, TbUser tbUser) {
        int count = tbUserDao.count(tbUser);
        Map<String, Object> map = new HashMap<>();

        map.put("start", start);
        map.put("length", length);
        map.put("draw", draw);
        map.put("tbUser", tbUser);
        List<TbUser> data = tbUserDao.getPage(map);

        PageInfo<TbUser> page = new PageInfo<>();
        page.setDraw(draw);
        page.setRecordsFiltered(count);
        page.setRecordsTotal(count);
        page.setData(data);

        return page;
    }


    @Override
    public boolean isEmailExist(String email, TbUser tbUser) {
        //如果 tbUser 的 id 为空
        //则表示在添加用户，则新增用户的邮箱不能与之前任一用户的邮箱相同
        if (tbUser.getId() == null) {
            return tbUserDao.selectByEmail(email) != null;
        }
        //表示修改用户信息时
        else {
            //如果邮箱不变的化，则直接返回 false
            if (email.equals(tbUserDao.getTbUserById(tbUser.getId()).getEmail())) {
                return false;
            }

            else {
                return tbUserDao.selectByEmail(email) != null;
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
