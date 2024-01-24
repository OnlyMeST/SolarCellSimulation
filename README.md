# SolarCellSimulation
A Java Spring boot application that generates data to predict the energy win and loss of energy in a solar cell. The Data can be inserted into a postgreSQL database.

List<SolarEnergyEntity> findByTimestampContaining(LocalDateTime partialTimestamp);

@Transactional(readOnly = true)
    public List<SolarEnergyEntity> getPanelsByPartialTimestamp(LocalDateTime partialTimestamp) {
        return solarEnergyRepository.findByTimestampContaining(partialTimestamp);
    }
