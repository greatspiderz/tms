package com.github.greatspiderz.db.adapter;

import com.github.greatspiderz.db.domain.*;
import com.github.greatspiderz.exception.TMSException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.util.ArrayList;
import java.util.List;

public class TaskGraphAdapter implements Adapter<TaskGraph> {

    private final TaskAdapter taskAdapter = new TaskAdapter();
    private final RelationAdapter relationAdapter = new RelationAdapter();
    private final ContextAdapter contextAdapter = new ContextAdapter();

    @Override
    public DBObject toDBObject(TaskGraph taskGraph) throws TMSException {
        if (taskGraph == null) {
            throw new TMSException();
        }

        DBObject dbObject = new BasicDBObject("_id", taskGraph.getGraphId());
        dbObject.put("external_reference_id", taskGraph.getExternalReferenceId());
        dbObject.put("status", taskGraph.getStatus().name());

        List<DBObject> taskObjects = new ArrayList<>();
        if (taskGraph.getTasks() != null) {
            for (Task task : taskGraph.getTasks()) {
                taskObjects.add(taskAdapter.toDBObject(task));
            }
        }
        dbObject.put("tasks", taskObjects);

        List<DBObject> relationObjects = new ArrayList<>();
        if (taskGraph.getRelations() != null) {
            for (Relation relation : taskGraph.getRelations()) {
                relationObjects.add(relationAdapter.toDBObject(relation));
            }
        }
        dbObject.put("relations", relationObjects);

        List<DBObject> contextObjects = new ArrayList<>();
        if (taskGraph.getContexts() != null) {
            for (Context context : taskGraph.getContexts()) {
                contextObjects.add(contextAdapter.toDBObject(context));
            }
        }
        dbObject.put("contexts", contextObjects);

        return dbObject;
    }

    @Override
    public TaskGraph fromDBObject(DBObject dbObject) throws TMSException {
        TaskGraph taskGraph = new TaskGraph();
        taskGraph.setGraphId(String.valueOf(dbObject.get("_id")));
        taskGraph.setExternalReferenceId(String.valueOf(dbObject.get("external_reference_id")));
        taskGraph.setStatus(TaskGraphStatus.valueOf(String.valueOf(dbObject.get("status"))));

        List<DBObject> tasks = (List<DBObject>) dbObject.get("tasks");
        List<Task> taskList = new ArrayList<>();
        for(DBObject task : tasks) {
            taskList.add(taskAdapter.fromDBObject(task));
        }
        taskGraph.setTasks(taskList);

        List<DBObject> relations = (List<DBObject>) dbObject.get("relations");
        List<Relation> relationList = new ArrayList<>();
        for(DBObject relation : relations) {
            relationList.add(relationAdapter.fromDBObject(relation));
        }
        taskGraph.setRelations(relationList);

        List<DBObject> contexts = (List<DBObject>) dbObject.get("contexts");
        List<Context> contextList = new ArrayList<>();
        for(DBObject context : contexts) {
            contextList.add(contextAdapter.fromDBObject(context));
        }
        taskGraph.setContexts(contextList);
        return taskGraph;
    }
}
