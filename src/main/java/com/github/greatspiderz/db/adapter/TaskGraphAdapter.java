package com.github.greatspiderz.db.adapter;

import com.github.greatspiderz.db.domain.Context;
import com.github.greatspiderz.db.domain.Relation;
import com.github.greatspiderz.db.domain.Task;
import com.github.greatspiderz.db.domain.TaskGraph;
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
}
