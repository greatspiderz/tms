package com.github.greatspiderz.db.domain;

import lombok.Data;

@Data
public class Context {
    private String contextId;
    private String refId;
    private RefType refType;
    private String name;
    private String value;
    private String graphId;
}
