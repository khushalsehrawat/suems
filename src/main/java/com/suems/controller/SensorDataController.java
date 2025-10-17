package com.suems.controller;


import com.suems.dto.SensorDataDto;
import com.suems.dto.SummaryDto;
import com.suems.mappers.Mappers;
import com.suems.service.SensorDataService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/sensors")
@CrossOrigin(origins = "*", allowCredentials = "false")
public class SensorDataController {

    private final SensorDataService service;

    public SensorDataController(SensorDataService service) {
        this.service = service;
    }


    @GetMapping("/recent")   // ✅ Add this endpoint (missing earlier)
    public List<SensorDataDto> recentTop100(){
        return service.findRecentTop100().stream().map(Mappers::toDto).toList();
    }

    @GetMapping("/after")
    public List<SensorDataDto> after(
            @RequestParam("ts") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime ts)
            {
                return service.findAfter(ts).stream().map(Mappers::toDto).toList();
    }

    @GetMapping("/summary")
    public SummaryDto summaryDto(@RequestParam(defaultValue = "24") int hours)
    {
        if (hours <= 0) hours = 24;
        return service.summarizeLastHour(hours);
    }

}
