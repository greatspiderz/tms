package com.github.greatspiderz.db.adapter;

import com.github.greatspiderz.db.domain.Relation;
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
}
