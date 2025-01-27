package com.saria.solarcell.solarcomponents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/solar-energy/panels")
public class TimestampEndpoint {

    private final SolarPanelService panelService;

    @Autowired
    public TimestampEndpoint(SolarPanelService panelService) {
        this.panelService = panelService;
    }

    // GET by Timestamp
    @GetMapping("/time/{timestamp}")
    public ResponseEntity<?> getByTimestamp(@PathVariable LocalDateTime timestamp) {
    }

    // GET by Timestamp Range
    @GetMapping("/beforeTime/{timestamp}")
    public ResponseEntity<?> getByTimestampRange(@PathVariable LocalDateTime timestamp) {
    }

}
