package com.suems.repository;

import com.suems.model.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface SensorDataRepository extends JpaRepository<SensorData, Long> {

    List<SensorData> findByTimestampAfterOrderByTimestampAsc(LocalDateTime after);


    @Query("select s from SensorData s order by s.timestamp desc limit 100")
    List<SensorData> findRecentTop100(); // works on Hibernate 6+ with limit syntax

}
