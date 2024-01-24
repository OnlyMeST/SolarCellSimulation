package com.saria.solarcell.solarcomponents;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "solar_cell")
public class SolarEnergyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double solarIrradiance;
    private double panelEfficiency;
    private double panelArea;
    private double inverterEfficiency;
    private double shadingLossFactor;
    private double soilingLossFactor;
    private double mismatchLossFactor;

    private LocalDateTime timestamp;
    private double energyOutput;
    private double netEnergy;

    // Constructors
    public SolarEnergyEntity() {
        // Default constructor for JSON serialization
    }

    public SolarEnergyEntity(
            double solarIrradiance, double panelEfficiency, double panelArea,
            double inverterEfficiency, double shadingLossFactor, double soilingLossFactor,
            double mismatchLossFactor, LocalDateTime timestamp, double energyOutput, double netEnergy) {
        this.solarIrradiance = solarIrradiance;
        this.panelEfficiency = panelEfficiency;
        this.panelArea = panelArea;
        this.inverterEfficiency = inverterEfficiency;
        this.shadingLossFactor = shadingLossFactor;
        this.soilingLossFactor = soilingLossFactor;
        this.mismatchLossFactor = mismatchLossFactor;
        this.timestamp = timestamp;
        this.energyOutput = energyOutput;
        this.netEnergy = netEnergy;
    }

    // Getters and Setters for SolarEnergyParameters
    public double getSolarIrradiance() {
        return solarIrradiance;
    }

    public void setSolarIrradiance(double solarIrradiance) {
        this.solarIrradiance = solarIrradiance;
    }

    public double getPanelEfficiency() {
        return panelEfficiency;
    }

    public void setPanelEfficiency(double panelEfficiency) {
        this.panelEfficiency = panelEfficiency;
    }

    public double getPanelArea() {
        return panelArea;
    }

    public void setPanelArea(double panelArea) {
        this.panelArea = panelArea;
    }

    public double getInverterEfficiency() {
        return inverterEfficiency;
    }

    public void setInverterEfficiency(double inverterEfficiency) {
        this.inverterEfficiency = inverterEfficiency;
    }

    public double getShadingLossFactor() {
        return shadingLossFactor;
    }

    public void setShadingLossFactor(double shadingLossFactor) {
        this.shadingLossFactor = shadingLossFactor;
    }

    public double getSoilingLossFactor() {
        return soilingLossFactor;
    }

    public void setSoilingLossFactor(double soilingLossFactor) {
        this.soilingLossFactor = soilingLossFactor;
    }

    public double getMismatchLossFactor() {
        return mismatchLossFactor;
    }

    public void setMismatchLossFactor(double mismatchLossFactor) {
        this.mismatchLossFactor = mismatchLossFactor;
    }

    // Getters and Setters for SolarEnergyResults
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public double getEnergyOutput() {
        return energyOutput;
    }

    public void setEnergyOutput(double energyOutput) {
        this.energyOutput = energyOutput;
    }

    public double getNetEnergy() {
        return netEnergy;
    }

    public void setNetEnergy(double netEnergy) {
        this.netEnergy = netEnergy;
    }
}