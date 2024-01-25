package com.saria.solarcell.solarcomponents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/solar-energy")
public class SolarCellController {

    private final SolarEnergyCalculatorService calculatorService;
    private final SolarPanelService panelService;

    @Autowired
    public SolarCellController(SolarEnergyCalculatorService calculatorService, SolarPanelService panelService) {
        this.calculatorService = calculatorService;
        this.panelService = panelService;
    }

    /**First Post
    
    Calculates energyOutput and netEnergy
    saves the data into the Database
    
    **/
    
    @PostMapping("/calculate")
    public List<SolarEnergyEntity> calculateSolarEnergy(@RequestBody SolarEnergyEntity input) {
        List<SolarEnergyEntity> resultsList = new ArrayList<>();

       
        SolarEnergyEntity randomInput = generateRandomInput();
        SolarEnergyEntity result = calculatorService.calculateSolarEnergy(randomInput);
        resultsList.add(result);
        // Save the input panel after calculations
        panelService.savePanel(result);

        // logging statements
        System.out.println("Saved Panel: " + result);
        System.out.println("All Panels: " + panelService.getAllPanels());

        return resultsList;
    }

    
    /**Endpoints
    
    General endpoints to GET and POST all panels currently in Backend
    
    **/
    
    @GetMapping("/panels")
    public List<SolarEnergyEntity> getAllPanels() {
        return panelService.getAllPanels();
    }

    @PostMapping("/panels")
    public SolarEnergyEntity savePanel(@RequestBody SolarEnergyEntity solarPanel) {
        return panelService.savePanel(solarPanel);
    }
    
    /**
    Specific endpoints
    
    For GET methods regarding ID, ID in range,
    Timestamps and timestamps in range
    
    **/
    
    // GET by ID
    @GetMapping("/panels/id/{id}")
    public ResponseEntity<?> getPanelById(@PathVariable String id) {
        if (id.contains("-")) {
            String[] range = id.split("-");
            if (range.length == 2) {
                try {
                    int startId = Integer.parseInt(range[0]);
                    int endId = Integer.parseInt(range[1]);
                    List<SolarEnergyEntity> panelsInRange = panelService.getPanelsByIdRange(startId, endId);
                    return ResponseEntity.ok(panelsInRange);
                } catch (NumberFormatException e) {
                	e.printStackTrace();
                    return ResponseEntity.badRequest().body("Invalid range format");
                }
            }
        } else {
            try {
                int singleId = Integer.parseInt(id);
                List<SolarEnergyEntity> panel = panelService.getPanelById(singleId);
                if (panel != null) {
                    return ResponseEntity.ok(Collections.singletonList(panel));
                } else {
                    return ResponseEntity.notFound().build();
                }
            } catch (NumberFormatException e) {
            	e.printStackTrace();
                return ResponseEntity.badRequest().body("Invalid ID format");
            }
        }
        return ResponseEntity.badRequest().body("Invalid input format");
    }
    
    
    //GET by Timestamp

    @GetMapping("/panels/time/{timestamp}")
    public ResponseEntity<?> getByTimestamp(@PathVariable LocalDateTime timestamp) {
        try {
        	List<SolarEnergyEntity> panel = panelService.getPanelsByTimestamp(timestamp);
            if (panel != null) {
                return ResponseEntity.ok(Collections.singletonList(panel));
            } else {
                return ResponseEntity.badRequest().body("Invalid ID format");
            }
        } catch (Exception e) {
        	e.printStackTrace();
            return ResponseEntity.badRequest().body("Invalid input format");
        }
    }

    //G
    @GetMapping("/panels/beforeTime/{timestamp}")
    public ResponseEntity<?> getByTimestampRange(@PathVariable LocalDateTime timestamp) {
        try {
            List<SolarEnergyEntity> panels = panelService.getPanelsByTimestampBefore(timestamp);
            if (panels != null && !panels.isEmpty()) {
                return ResponseEntity.ok(panels);
            } else {
                return ResponseEntity.badRequest().body("No panels found for the given timestamp range");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("An error occurred. Check the logs for more details.");
        }
    }




    private SolarEnergyEntity generateRandomInput() {
        SolarEnergyEntity randomInput = new SolarEnergyEntity();
        randomInput.setSolarIrradiance(RandomParameterGenerator.generateRandomSolarIrradiance());
        randomInput.setPanelEfficiency(RandomParameterGenerator.generateRandomPanelEfficiency());
        randomInput.setPanelArea(RandomParameterGenerator.generateRandomPanelArea());
        randomInput.setShadingLossFactor(RandomParameterGenerator.generateRandomLossFactor());
        randomInput.setSoilingLossFactor(RandomParameterGenerator.generateRandomSoilingLossFactor());
        randomInput.setMismatchLossFactor(RandomParameterGenerator.generateRandomMismatchLossFactor());
        randomInput.setInverterEfficiency(RandomParameterGenerator.generateRandomInverterEfficiency());
        return randomInput;
    }
}
