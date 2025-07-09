package com._onWheels._onWheels;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface ApiLogRepository extends JpaRepository<ApiLog, Long> {

    List<ApiLog> findByTimestampBetweenAndServiceIn(Instant start, Instant end, List<String> services);
    List<ApiLog> findByTimestampBetween(Instant start, Instant end);
    List<ApiLog> findByServiceIn(List<String> services);
}
