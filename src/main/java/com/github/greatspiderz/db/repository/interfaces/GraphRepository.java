package com.github.greatspiderz.db.repository.interfaces;

import com.github.greatspiderz.db.domain.TaskGraph;
import com.github.greatspiderz.exception.TMSException;

public interface GraphRepository {
    void saveGraph(TaskGraph taskGraph) throws TMSException;
}
