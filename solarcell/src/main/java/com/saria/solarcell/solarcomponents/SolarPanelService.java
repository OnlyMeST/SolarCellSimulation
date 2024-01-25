package com.saria.solarcell.solarcomponents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SolarPanelService {

    @Autowired
    private SolarPanelRepository solarEnergyRepository;

    @Transactional
    public SolarEnergyEntity savePanel(SolarEnergyEntity input) {
        return solarEnergyRepository.save(input);
    }

    @Transactional(readOnly = true)
    public List<SolarEnergyEntity> getAllPanels() {
        return solarEnergyRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<SolarEnergyEntity> getPanelsBySolarIrradiance(double solarIrradiance) {
        return solarEnergyRepository.findBySolarIrradiance(solarIrradiance);
    }

    @Transactional(readOnly = true)
    public List<SolarEnergyEntity> getPanelsByPanelEfficiency(double panelEfficiency) {
        return solarEnergyRepository.findByPanelEfficiency(panelEfficiency);
    }

    @Transactional(readOnly = true)
    public List<SolarEnergyEntity> getPanelsByPanelArea(double panelArea) {
        return solarEnergyRepository.findByPanelArea(panelArea);
    }

    @Transactional(readOnly = true)
    public List<SolarEnergyEntity> getPanelsByInverterEfficiency(double inverterEfficiency) {
        return solarEnergyRepository.findByInverterEfficiency(inverterEfficiency);
    }

    @Transactional(readOnly = true)
    public List<SolarEnergyEntity> getPanelsByShadingLossFactor(double shadingLossFactor) {
        return solarEnergyRepository.findByShadingLossFactor(shadingLossFactor);
    }

    @Transactional(readOnly = true)
    public List<SolarEnergyEntity> getPanelsBySoilingLossFactor(double soilingLossFactor) {
        return solarEnergyRepository.findBySoilingLossFactor(soilingLossFactor);
    }

    @Transactional(readOnly = true)
    public List<SolarEnergyEntity> getPanelsByMismatchLossFactor(double mismatchLossFactor) {
        return solarEnergyRepository.findByMismatchLossFactor(mismatchLossFactor);
    }

    @Transactional(readOnly = true)
    public List<SolarEnergyEntity> getPanelsByTimestamp(LocalDateTime timestamp) {
        return solarEnergyRepository.findByTimestamp(timestamp);
    }

    @Transactional(readOnly = true)
    public List<SolarEnergyEntity> getPanelsByEnergyOutput(double energyOutput) {
        return solarEnergyRepository.findByEnergyOutput(energyOutput);
    }

    @Transactional(readOnly = true)
    public List<SolarEnergyEntity> getPanelsByNetEnergy(double netEnergy) {
        return solarEnergyRepository.findByNetEnergy(netEnergy);
    }
    
    @Transactional(readOnly = true)
    public List<SolarEnergyEntity> getPanelById(Integer id){
    	return solarEnergyRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public List<SolarEnergyEntity> getPanelsByIdRange(Integer startId, Integer endId) {
        return solarEnergyRepository.findByIdBetween(startId, endId);
    }
    
    @Transactional(readOnly = true)
    public List<SolarEnergyEntity> getPanelsByTimestampBefore(LocalDateTime timestamp) {
        return solarEnergyRepository.findByTimestampLessThanEqual(timestamp);
    }

}
