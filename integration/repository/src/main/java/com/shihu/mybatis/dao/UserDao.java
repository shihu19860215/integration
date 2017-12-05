package com.shihu.mybatis.dao;

import com.shihu.model.common.User;
import com.shihu.model.common.VO.UserVO;

public interface UserDao {
    public UserVO getUserVOByUP(User user);
}
