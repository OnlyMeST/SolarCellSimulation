package com.saria.solarcell.solarcomponents;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolarPanelRepository extends JpaRepository<SolarEnergyEntity, Long> {
	List<SolarEnergyEntity> findById(Integer id);
	List<SolarEnergyEntity> findByIdBetween(Integer startId, Integer endId);
    List<SolarEnergyEntity> findBySolarIrradiance(double solarIrradiance);
    List<SolarEnergyEntity> findByPanelEfficiency(double panelEfficiency);
    List<SolarEnergyEntity> findByPanelArea(double panelArea);
    List<SolarEnergyEntity> findByInverterEfficiency(double inverterEfficiency);
    List<SolarEnergyEntity> findByShadingLossFactor(double shadingLossFactor);
    List<SolarEnergyEntity> findBySoilingLossFactor(double soilingLossFactor);
    List<SolarEnergyEntity> findByMismatchLossFactor(double mismatchLossFactor);
    List<SolarEnergyEntity> findByTimestamp(LocalDateTime timestamp);
    List<SolarEnergyEntity> findByTimestampLessThanEqual(LocalDateTime timestamp);
    List<SolarEnergyEntity> findByEnergyOutput(double energyOutput);
    List<SolarEnergyEntity> findByNetEnergy(double netEnergy);
}
