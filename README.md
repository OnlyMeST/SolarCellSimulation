# SolarCellSimulation
A Java Spring boot application that generates data to predict the energy win and loss of energy in a solar cell. The Data can be inserted into a postgreSQL database.
@Transactional(readOnly = true)
    public List<SolarEnergyEntity> getPanelsByTimestampRange(Date startTS, Date endTS) {
        return solarEnergyRepository.findByTimestampBetween(startTS, endTS);
    }
    
public ResponseEntity<?> getByTimestamp(@PathVariable LocalDateTime timestamp) {
        try {
        	List<SolarEnergyEntity> panel = panelService.getPanelsByTimestamp(timestamp);
            if (panel != null) {
                return ResponseEntity.ok(Collections.singletonList(panel));
            } else {
                return ResponseEntity.badRequest().body("Invalid ID format");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid input format");
        }
    }
