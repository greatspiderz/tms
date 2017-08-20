package com.github.greatspiderz.db.domain;

import lombok.Data;

@Data
public class Task {
    private String taskId;
    private String type;
    private TaskStatus status;
    private Boolean composite;
    private String useCaseNodeId;
    private String graphId;
}
