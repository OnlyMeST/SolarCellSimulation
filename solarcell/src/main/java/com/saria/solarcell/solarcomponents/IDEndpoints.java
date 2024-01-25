package com.saria.solarcell.solarcomponents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/solar-energy/panels")
public class IDEndpoint {

    private final SolarPanelService panelService;

    @Autowired
    public IDEndpoint(SolarPanelService panelService) {
        this.panelService = panelService;
    }

    // GET by ID
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getPanelById(@PathVariable String id) {
        // Your existing logic for getting panels by ID
        // ...
    }

    // ... (other ID-related endpoints)
}
