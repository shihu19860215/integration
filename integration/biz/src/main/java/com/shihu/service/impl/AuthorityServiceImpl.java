package com.shihu.service.impl;

import com.shihu.mybatis.dao.AuthorityDao;
import com.shihu.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class AuthorityServiceImpl implements AuthorityService{
    @Autowired
    private AuthorityDao authorityDao;
    private Set<String> set;

    public Set<String> getAuthorityStrByUserId(Long userId) {
        return authorityDao.getAuthorityStrByUserId(userId);
    }

    public Set<String> getAllAuthorityStrSet() {
        if(null==set){
            set=authorityDao.getAuthorityStrAll();
        }
        return set;
    }
}
