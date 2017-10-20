package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.EventHistory;
import org.springframework.data.repository.CrudRepository;


public interface EventHistoryRepository extends CrudRepository<EventHistory, Integer> {


}
