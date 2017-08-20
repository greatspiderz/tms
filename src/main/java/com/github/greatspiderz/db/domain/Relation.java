package com.github.greatspiderz.db.domain;

import lombok.Data;

@Data
public class Relation {
    private String relationId;
    private RelationType type;
    private String source;
    private String destination;
    private String graphId;
}
