package com.github.greatspiderz.db.repository.impl;

import com.github.greatspiderz.db.adapter.TaskGraphAdapter;
import com.github.greatspiderz.db.domain.TaskGraph;
import com.github.greatspiderz.db.repository.interfaces.GraphRepository;
import com.github.greatspiderz.exception.TMSException;
import com.mongodb.*;

import java.net.UnknownHostException;

public class GraphRepositoryImpl implements GraphRepository {

    private static MongoClient mongoClient = null;
    private static final String dbName = "tms";
    private static final String graphCollection = "graph";
    private static final TaskGraphAdapter graphAdapter = new TaskGraphAdapter();

    static {
        try {
            mongoClient = new MongoClient();
        } catch (UnknownHostException e) {
            System.out.println("Error in connecting Mongo DB" + e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void saveGraph(TaskGraph taskGraph) throws TMSException {
        DB database = mongoClient.getDB(dbName);
        database.requestStart();
        DBCollection collection = database.getCollection(graphCollection);
        DBObject graph = graphAdapter.toDBObject(taskGraph);
        collection.insert(graph);
        database.requestDone();
    }

    @Override
    public TaskGraph fetchGraph(String graphId) throws TMSException {
        DB database = mongoClient.getDB(dbName);
        database.requestStart();
        DBCollection collection = database.getCollection(graphCollection);
        DBObject dbObject = new BasicDBObject("_id", graphId);
        DBCursor cursor = collection.find(dbObject);
        DBObject graph = null;
        while (cursor.hasNext()) {
            graph = cursor.next();
        }
        return graphAdapter.fromDBObject(graph);
    }
}
