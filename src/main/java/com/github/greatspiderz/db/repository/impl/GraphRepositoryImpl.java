package com.github.greatspiderz.db.repository.impl;

import com.github.greatspiderz.db.adapter.TaskGraphAdapter;
import com.github.greatspiderz.db.domain.TaskGraph;
import com.github.greatspiderz.db.repository.interfaces.GraphRepository;
import com.github.greatspiderz.exception.TMSException;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class GraphRepositoryImpl implements GraphRepository {

    private static MongoClient mongoClient = null;
    private static final String dbName = "tms";
    private static final String graphCollection = "graph";
    private static final TaskGraphAdapter graphAdapter = new TaskGraphAdapter();


    private MongoClient getMongoClient() throws TMSException {
        try {
            if (mongoClient == null) {
                mongoClient = new MongoClient();
            }
        } catch (Exception e) {
            throw new TMSException();
        }
        return mongoClient;
    }

    @Override
    public void saveGraph(TaskGraph taskGraph) throws TMSException {
        MongoClient mongoClient = getMongoClient();
        DB database = mongoClient.getDB(dbName);
        database.requestStart();
        DBCollection collection = database.getCollection(graphCollection);
        DBObject graph = graphAdapter.toDBObject(taskGraph);
        collection.insert(graph);
        database.requestDone();
    }
}
