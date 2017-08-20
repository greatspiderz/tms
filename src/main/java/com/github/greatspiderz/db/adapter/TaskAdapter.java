package com.github.greatspiderz.db.adapter;

import com.github.greatspiderz.db.domain.Task;
import com.github.greatspiderz.db.domain.TaskStatus;
import com.github.greatspiderz.exception.TMSException;
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

    @Override
    public Task fromDBObject(DBObject dbObject) throws TMSException {
        Task task = new Task();
        task.setTaskId(String.valueOf(dbObject.get("_id")));
        task.setType(String.valueOf(dbObject.get("type")));
        task.setStatus(TaskStatus.valueOf(String.valueOf(dbObject.get("status"))));
        task.setComposite((Boolean) dbObject.get("composite"));
        task.setUseCaseNodeId(String.valueOf(dbObject.get("use_case_node_id")));
        task.setGraphId(String.valueOf(dbObject.get("graph_id")));
        return task;
    }
}
