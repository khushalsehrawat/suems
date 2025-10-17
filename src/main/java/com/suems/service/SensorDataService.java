package com.suems.service;


import com.suems.dto.SummaryDto;
import com.suems.model.SensorData;
import com.suems.repository.SensorDataRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
public class SensorDataService {

    private final SensorDataRepository sensorDataRepository;

    public SensorDataService(SensorDataRepository sensorDataRepository) {
        this.sensorDataRepository = sensorDataRepository;
    }

    public List<SensorData> findAll(){
        return sensorDataRepository.findAll();
    }

    public List<SensorData> findRecentTop100(){
        return sensorDataRepository.findRecentTop100();
    }

    public List<SensorData> findAfter(LocalDateTime after){
        return sensorDataRepository.findByTimestampAfterOrderByTimestampAsc(after);
    }

    public SensorData save(SensorData sensorData){
        return sensorDataRepository.save(sensorData);
    }

    public SummaryDto summarizeLastHour(int hours){
        LocalDateTime after = LocalDateTime.now().minusHours(hours);
        List<SensorData> list = findAfter(after);

        SummaryDto sum = new SummaryDto();
        sum.samples = (long) list.size();

        DoubleSummaryStatistics sSolar = list.stream().mapToDouble(SensorData::getSolarPower).summaryStatistics();
        DoubleSummaryStatistics sWind = list.stream().mapToDouble(SensorData::getWindPower).summaryStatistics();
        DoubleSummaryStatistics sGrid = list.stream().mapToDouble(SensorData::getGridUsage).summaryStatistics();
        DoubleSummaryStatistics sCons = list.stream().mapToDouble(SensorData::getTotalConsumption).summaryStatistics();

        return sum;

    }




}
