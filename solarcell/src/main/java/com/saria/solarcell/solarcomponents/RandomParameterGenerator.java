package com.saria.solarcell.solarcomponents;
import java.util.Random;

public class RandomParameterGenerator {

    private static final Random random = new Random();

    public static double generateRandomSolarIrradiance() {
        return SolarEnergyConstants.MIN_SOLAR_IRRADIANCE + (SolarEnergyConstants.MAX_SOLAR_IRRADIANCE - SolarEnergyConstants.MIN_SOLAR_IRRADIANCE) * random.nextDouble();
    }

    public static double generateRandomPanelEfficiency() {
        return SolarEnergyConstants.MIN_PANEL_EFFICIENCY + (SolarEnergyConstants.MAX_PANEL_EFFICIENCY - SolarEnergyConstants.MIN_PANEL_EFFICIENCY) * random.nextDouble();
    }

    public static double generateRandomPanelArea() {
        return SolarEnergyConstants.MIN_PANEL_AREA + (SolarEnergyConstants.MAX_PANEL_AREA - SolarEnergyConstants.MIN_PANEL_AREA) * random.nextDouble();
    }

    public static double generateRandomLossFactor() {
        return SolarEnergyConstants.MIN_SHADING_LOSS_FACTOR + (SolarEnergyConstants.MAX_SHADING_LOSS_FACTOR - SolarEnergyConstants.MIN_SHADING_LOSS_FACTOR) * random.nextDouble();
    }

    public static double generateRandomSoilingLossFactor() {
        return SolarEnergyConstants.MIN_SOILING_LOSS_FACTOR + (SolarEnergyConstants.MAX_SOILING_LOSS_FACTOR - SolarEnergyConstants.MIN_SOILING_LOSS_FACTOR) * random.nextDouble();
    }

    public static double generateRandomMismatchLossFactor() {
        return SolarEnergyConstants.MIN_MISMATCH_LOSS_FACTOR + (SolarEnergyConstants.MAX_MISMATCH_LOSS_FACTOR - SolarEnergyConstants.MIN_MISMATCH_LOSS_FACTOR) * random.nextDouble();
    }

    public static double generateRandomInverterEfficiency() {
        return SolarEnergyConstants.MIN_INVERTER_EFFICIENCY + (SolarEnergyConstants.MAX_INVERTER_EFFICIENCY - SolarEnergyConstants.MIN_INVERTER_EFFICIENCY) * random.nextDouble();
    }
}