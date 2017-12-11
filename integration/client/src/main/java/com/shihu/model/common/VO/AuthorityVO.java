package com.shihu.model.common.VO;

public class AuthorityVO {
    private Long id;
    private String name;
    private String controller;
    private String method;

    public Long getId() {
        return id;
    }

    public AuthorityVO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AuthorityVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getController() {
        return controller;
    }

    public AuthorityVO setController(String controller) {
        this.controller = controller;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public AuthorityVO setMethod(String method) {
        this.method = method;
        return this;
    }
}
