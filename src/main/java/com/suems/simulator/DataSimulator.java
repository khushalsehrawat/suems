package com.suems.simulator;


import com.suems.model.SensorData;
import com.suems.service.SensorDataService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class DataSimulator {

    private final SensorDataService sensorDataService;
    private final Random random = new Random();

    public DataSimulator(SensorDataService sensorDataService) {
        this.sensorDataService = sensorDataService;
    }

    @Value("${suems.simulator.enabled}")
    private boolean enabled;

    // Generate synthetic data every 10 seconds
    @Scheduled(fixedRateString = "${suems.simulator.interval-ms:10000}")
    public void tick() {
        if (!enabled) return;

        SensorData s = new SensorData();
        double solar = clamp(0, 12, 2 + random.nextDouble() * 10);
        double wind  = clamp(0, 8,  1 + random.nextDouble() * 6);
        double grid  = clamp(0, 10, 1 + random.nextDouble() * 8);

        // consumption is what was actually used
        double consumption = clamp(0, 20, solar + wind + grid - random.nextDouble() * 3);

        s.setSolarPower(solar);
        s.setWindPower(wind);
        s.setGridUsage(grid);
        s.setTotalConsumption(consumption);

        sensorDataService.save(s);
    }

    private double clamp(double min, double max, double val) {
        return Math.max(min, Math.min(max, val));
    }

}
