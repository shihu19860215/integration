package com.shihu.redis;

import com.shihu.model.common.VO.CustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class CustomerRedis {
    @Autowired
    private RedisTemplate<String,CustomerVO> redisTemplate;
    public CustomerVO getCustomerVO(Long id){
        return redisTemplate.opsForValue().get(RedisFinal.CUSTOMER_BEAN+id);
    }

    public void putCustomerVO(Long id,CustomerVO customerVO){
        redisTemplate.opsForValue().set(RedisFinal.CUSTOMER_BEAN+id,customerVO);
    }

}
