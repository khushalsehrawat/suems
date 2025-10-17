package com.suems.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;

@Entity
@Table(indexes = {
        @Index(name="idx_sensordata_ts", columnList = "timestamp")
})
public class SensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp = LocalDateTime.now();

    @PositiveOrZero
    private double solarPower;

    @PositiveOrZero
    private double windPower;

    @PositiveOrZero
    private double gridUsage;

    @PositiveOrZero
    private double totalConsumption;

    public SensorData(){}

    public SensorData(Long id, LocalDateTime timestamp, double solarPower, double windPower, double gridUsage, double totalConsumption) {
        this.id = id;
        this.timestamp = timestamp;
        this.solarPower = solarPower;
        this.windPower = windPower;
        this.gridUsage = gridUsage;
        this.totalConsumption = totalConsumption;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @PositiveOrZero
    public double getSolarPower() {
        return solarPower;
    }

    public void setSolarPower(@PositiveOrZero double solarPower) {
        this.solarPower = solarPower;
    }

    @PositiveOrZero
    public double getWindPower() {
        return windPower;
    }

    public void setWindPower(@PositiveOrZero double windPower) {
        this.windPower = windPower;
    }

    @PositiveOrZero
    public double getGridUsage() {
        return gridUsage;
    }

    public void setGridUsage(@PositiveOrZero double gridUsage) {
        this.gridUsage = gridUsage;
    }

    @PositiveOrZero
    public double getTotalConsumption() {
        return totalConsumption;
    }

    public void setTotalConsumption(@PositiveOrZero double totalConsumption) {
        this.totalConsumption = totalConsumption;
    }
}
