package com.github.greatspiderz.db.adapter;

import com.github.greatspiderz.db.domain.Context;
import com.github.greatspiderz.db.domain.RefType;
import com.github.greatspiderz.db.domain.Relation;
import com.github.greatspiderz.db.domain.RelationType;
import com.github.greatspiderz.exception.TMSException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class ContextAdapter implements Adapter<Context> {
    @Override
    public DBObject toDBObject(Context context) {
        return new BasicDBObject("_id", context.getContextId())
                .append("ref_id", context.getRefId())
                .append("ref_type", context.getRefType().name())
                .append("name", context.getName())
                .append("value", context.getValue())
                .append("graph_id", context.getGraphId());
    }

    @Override
    public Context fromDBObject(DBObject dbObject) throws TMSException {
        Context context = new Context();
        context.setContextId(String.valueOf(dbObject.get("_id")));
        context.setRefId(String.valueOf(dbObject.get("ref_id")));
        context.setRefType(RefType.valueOf(String.valueOf(dbObject.get("ref_type"))));
        context.setName(String.valueOf(dbObject.get("name")));
        context.setValue(String.valueOf(dbObject.get("value")));
        context.setGraphId(String.valueOf(dbObject.get("graph_id")));
        return context;
    }
}
