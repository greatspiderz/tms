package com.github.greatspiderz.db.domain;

import lombok.Data;

import java.util.List;

@Data
public class TaskGraph {
    private String graphId;
    private String externalReferenceId;
    private TaskGraphStatus status;
    private List<Task> tasks;
    private List<Relation> relations;
    private List<Context> contexts;
    private List<TaskActorMapping> taskActorMappings;
    private List<TaskSubjectMapping> taskSubjectMappings;
}
