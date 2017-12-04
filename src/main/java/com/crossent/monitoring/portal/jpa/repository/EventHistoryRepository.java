package com.crossent.monitoring.portal.jpa.repository;

import com.crossent.monitoring.portal.jpa.domain.EventHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface EventHistoryRepository extends CrudRepository<EventHistory, Integer> {
    public Page<EventHistory> findAllByMonGroupIdAndResourceIdAndResourceTypeInOrderByUpdateDttmDescIdDesc(Pageable pageable, Integer monGroupId, Integer resourceId, List<String> resourceTypes);
    public Page<EventHistory> findAllByMonGroupIdAndResourceIdAndResourceTypeInAndStateCodeCodeInOrderByUpdateDttmDescIdDesc(Pageable pageable, Integer monGroupId, Integer resourceId, List<String> resourceTypes, List<String> stateCodes);

    public Page<EventHistory> findAllByMonGroupIdAndResourceIdAndResourceTypeInAndStateCodeCodeInAndUpdateDttmGreaterThanEqualAndUpdateDttmLessThanEqualOrderByUpdateDttmDescIdDesc(Pageable pageable, Integer monGroupId, Integer resourceId, List<String> resourceTypes, List<String> stateCodes, String startDttm, String endDttm);
    public Page<EventHistory> findAllByMonGroupIdAndResourceIdAndResourceTypeInAndStateCodeCodeOrderByUpdateDttmDescIdDesc(Pageable pageable, Integer monGroupId, Integer resourceId, List<String> resourceTypes, String stateCode);



}
