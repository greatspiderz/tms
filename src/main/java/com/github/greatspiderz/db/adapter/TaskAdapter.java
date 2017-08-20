package com.github.greatspiderz.db.adapter;

import com.github.greatspiderz.db.domain.Task;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class TaskAdapter implements Adapter<Task> {
    @Override
    public DBObject toDBObject(Task task) {
        return new BasicDBObject("_id", task.getTaskId())
                .append("type", task.getType())
                .append("status", task.getStatus().name())
                .append("composite", task.getComposite())
                .append("use_case_node_id", task.getUseCaseNodeId())
                .append("graph_id", task.getGraphId());
    }
}
