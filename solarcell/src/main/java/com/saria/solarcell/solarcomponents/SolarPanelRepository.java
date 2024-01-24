package com.saria.solarcell.solarcomponents;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SolarPanelRepository extends JpaRepository<SolarEnergyEntity, Long> {
    
    List<SolarEnergyEntity> findBySolarIrradiance(double solarIrradiance);
    List<SolarEnergyEntity> findByPanelEfficiency(double panelEfficiency);
    List<SolarEnergyEntity> findByPanelArea(double panelArea);
    List<SolarEnergyEntity> findByInverterEfficiency(double inverterEfficiency);
    List<SolarEnergyEntity> findByShadingLossFactor(double shadingLossFactor);
    List<SolarEnergyEntity> findBySoilingLossFactor(double soilingLossFactor);
    List<SolarEnergyEntity> findByMismatchLossFactor(double mismatchLossFactor);
    List<SolarEnergyEntity> findByTimestamp(LocalDateTime timestamp);
    List<SolarEnergyEntity> findByEnergyOutput(double energyOutput);
    List<SolarEnergyEntity> findByNetEnergy(double netEnergy);
}