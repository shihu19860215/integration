package com.shihu.service.impl;

import com.shihu.model.common.Authority;
import com.shihu.model.common.User;
import com.shihu.model.common.VO.UserVO;
import com.shihu.mybatis.dao.AuthorityDao;
import com.shihu.mybatis.dao.UserDao;
import com.shihu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private AuthorityDao authorityDao;

    public User getUserByUP(User user) {
        User result;
        UserVO userVO=userDao.getUserVOByUP(user);
        if(null==userVO){
            result=null;
        }else {
            Set<String> authoritys=authorityDao.getAuthoritysByUserId(userVO.getId());
            result=new User(userVO);
            result.setAuthorityUrls(authoritys);
        }
        return result;
    }

}
