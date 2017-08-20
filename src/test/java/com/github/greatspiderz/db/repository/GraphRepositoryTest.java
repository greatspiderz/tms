package com.github.greatspiderz.db.repository;

import com.github.greatspiderz.db.domain.*;
import com.github.greatspiderz.db.repository.impl.GraphRepositoryImpl;
import com.github.greatspiderz.db.repository.interfaces.GraphRepository;
import com.github.greatspiderz.exception.TMSException;
import org.junit.Test;

import java.util.*;

public class GraphRepositoryTest {
    @Test
    public void testGraphInsert() {
        GraphRepository graphRepository = new GraphRepositoryImpl();
        List<String> graphIds = new ArrayList<>();

        for (int i=0;i<100;++i) {
            TaskGraph taskGraph = getTaskGraph();
            graphIds.add(taskGraph.getGraphId());
            Long startTime = System.currentTimeMillis();
            try {
                graphRepository.saveGraph(taskGraph);
            } catch (TMSException e) {
                e.printStackTrace();
            }
            Long endTime = System.currentTimeMillis();
            System.out.println("Save Time : " + (endTime-startTime));
        }

        for (String graphId : graphIds) {
            try {
                Long startTime = System.currentTimeMillis();
                TaskGraph taskGraph = graphRepository.fetchGraph(graphId);
                Long endTime = System.currentTimeMillis();
                System.out.println("Fetch Time : " + (endTime-startTime));
                System.out.println(taskGraph);
            } catch (TMSException e) {
                e.printStackTrace();
            }
        }

    }

    private TaskGraph getTaskGraph() {

        TaskGraph taskGraph = new TaskGraph();
        String graphId = UUID.randomUUID().toString();
        taskGraph.setGraphId(graphId);
        taskGraph.setExternalReferenceId(UUID.randomUUID().toString());
        taskGraph.setStatus(TaskGraphStatus.PENDING);

        Task task1 = new Task();
        String taskId1 = UUID.randomUUID().toString();
        task1.setTaskId(taskId1);
        task1.setComposite(false);
        task1.setGraphId(graphId);
        task1.setStatus(TaskStatus.NEW);
        task1.setType("PICK");
        task1.setUseCaseNodeId("1");

        Task task2 = new Task();
        String taskId2 = UUID.randomUUID().toString();
        task2.setTaskId(taskId2);
        task2.setComposite(false);
        task2.setGraphId(graphId);
        task2.setStatus(TaskStatus.NEW);
        task2.setType("DELIVERY");
        task2.setUseCaseNodeId("2");

        taskGraph.setTasks(Arrays.asList(task1, task2));

        Relation relation = new Relation();
        relation.setRelationId(UUID.randomUUID().toString());
        relation.setSource(taskId1);
        relation.setDestination(taskId2);
        relation.setGraphId(graphId);
        relation.setType(RelationType.EDGE);

        taskGraph.setRelations(Collections.singletonList(relation));

        Context context = new Context();
        context.setContextId(UUID.randomUUID().toString());
        context.setGraphId(graphId);
        context.setRefId(graphId);
        context.setRefType(RefType.TASKGRAPH);
        context.setName("key1");
        context.setValue("val1");

        taskGraph.setContexts(Collections.singletonList(context));
        return taskGraph;
    }
}
