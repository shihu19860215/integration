package com.shihu.mybatis.dao;

import com.shihu.model.common.VO.AuthorityVO;

import java.util.List;
import java.util.Set;

public interface AuthorityDao {
    public Set<String> getAuthorityStrByUserId(Long id);
    public Set<String> getAuthorityStrAll();
}
