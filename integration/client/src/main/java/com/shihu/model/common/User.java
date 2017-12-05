package com.shihu.model.common;

import com.shihu.model.common.VO.UserVO;

import java.sql.Date;
import java.util.Set;

public class User {
    private Long id;
    private String username;
    private String password;
    private Set<String> authorityUrls;
    private Date registerDate;

    public User(){
    }
    public User(UserVO userVO){
        this.id=userVO.getId();
        this.username=userVO.getUsername();
        this.password=userVO.getPassword();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Set<String> getAuthorityUrls() {
        return authorityUrls;
    }

    public void setAuthorityUrls(Set<String> authorityUrls) {
        this.authorityUrls = authorityUrls;
    }
}
