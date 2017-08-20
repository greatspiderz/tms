package com.github.greatspiderz.db.adapter;

import com.github.greatspiderz.db.domain.Context;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class ContextAdapter implements Adapter<Context> {
    @Override
    public DBObject toDBObject(Context context) {
        return new BasicDBObject("_id", context.getContextId())
                .append("refId", context.getRefId())
                .append("refType", context.getRefType().name())
                .append("name", context.getName())
                .append("value", context.getValue())
                .append("graph_id", context.getGraphId());
    }
}
