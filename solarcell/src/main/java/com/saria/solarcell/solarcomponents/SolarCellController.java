package com.saria.solarcell.solarcomponents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("/panels")
    public List<SolarEnergyEntity> getAllPanels() {
        return panelService.getAllPanels();
    }

    @PostMapping("/panels")
    public SolarEnergyEntity savePanel(@RequestBody SolarEnergyEntity solarPanel) {
        return panelService.savePanel(solarPanel);
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