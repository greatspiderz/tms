package com.github.greatspiderz.db.adapter;

import com.github.greatspiderz.db.domain.Relation;
import com.github.greatspiderz.db.domain.RelationType;
import com.github.greatspiderz.db.domain.Task;
import com.github.greatspiderz.db.domain.TaskStatus;
import com.github.greatspiderz.exception.TMSException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class RelationAdapter implements Adapter<Relation> {
    @Override
    public DBObject toDBObject(Relation relation) {
        return new BasicDBObject("_id", relation.getRelationId())
                .append("type", relation.getType().name())
                .append("source", relation.getSource())
                .append("destination", relation.getDestination())
                .append("graph_id", relation.getGraphId());
    }

    @Override
    public Relation fromDBObject(DBObject dbObject) throws TMSException {
        Relation relation = new Relation();
        relation.setRelationId(String.valueOf(dbObject.get("_id")));
        relation.setType(RelationType.valueOf(String.valueOf(dbObject.get("type"))));
        relation.setSource(String.valueOf(dbObject.get("source")));
        relation.setDestination(String.valueOf(dbObject.get("destination")));
        relation.setGraphId(String.valueOf(dbObject.get("graph_id")));
        return relation;
    }
}
