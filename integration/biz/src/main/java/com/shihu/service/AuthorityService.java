package com.shihu.service;

import java.util.Set;

public interface AuthorityService {
    public Set<String> getAuthorityStrByUserId(Long userId);
    public Set<String> getAllAuthorityStrSet();
}
