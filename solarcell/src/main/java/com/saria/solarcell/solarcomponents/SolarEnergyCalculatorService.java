package com.saria.solarcell.solarcomponents;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class SolarEnergyCalculatorService {

public SolarEnergyEntity calculateSolarEnergy(SolarEnergyEntity input) {
    LocalDateTime timestamp = LocalDateTime.now();
    double energyOutput = calculateEnergyOutput(
            input.getSolarIrradiance(),
            input.getPanelEfficiency(),
            input.getPanelArea(),
            input.getShadingLossFactor(),
            input.getSoilingLossFactor(),
            input.getMismatchLossFactor()
    );
    double netEnergy = calculateNetEnergy(energyOutput, input.getInverterEfficiency());
    return new SolarEnergyEntity(
            input.getSolarIrradiance(),
            input.getPanelEfficiency(),
            input.getPanelArea(),
            input.getInverterEfficiency(),
            input.getShadingLossFactor(),
            input.getSoilingLossFactor(),
            input.getMismatchLossFactor(),
            timestamp,
            energyOutput,
            netEnergy
    );
}


    private double calculateEnergyOutput(double solarIrradiance, double panelEfficiency,
                                         double panelArea, double shadingLossFactor,
                                         double soilingLossFactor, double mismatchLossFactor) {
        double totalLossFactor = 1 - shadingLossFactor - soilingLossFactor - mismatchLossFactor;
        return solarIrradiance * panelEfficiency * panelArea * totalLossFactor;
    }

    private double calculateNetEnergy(double energyOutput, double inverterEfficiency) {
        return energyOutput * inverterEfficiency;
    }
} 