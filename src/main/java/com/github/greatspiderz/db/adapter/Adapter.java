package com.github.greatspiderz.db.adapter;

import com.github.greatspiderz.exception.TMSException;
import com.mongodb.DBObject;

public interface Adapter<T> {
    DBObject toDBObject(T t) throws TMSException;
}
