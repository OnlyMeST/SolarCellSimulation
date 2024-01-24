# SolarCellSimulation
A Java Spring boot application that generates data to predict the energy win and loss of energy in a solar cell. The Data can be inserted into a postgreSQL database.
Transactional(readOnly = true)
    public List<SolarEnergyEntity> getPanelsByPartialTimestamp(LocalDateTime partialTimestamp) {
        // Format the LocalDateTime to a string representation
        String formattedPartialTimestamp = partialTimestamp.toString();
        return solarEnergyRepository.findByTimestampContaining(formattedPartialTimestamp);
    }
