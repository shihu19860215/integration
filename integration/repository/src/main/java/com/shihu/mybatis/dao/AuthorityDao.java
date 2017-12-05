package com.shihu.mybatis.dao;

import com.shihu.model.common.Authority;

import java.util.List;
import java.util.Set;

public interface AuthorityDao {
    public Set<String> getAuthoritysByUserId(Long id);
}
