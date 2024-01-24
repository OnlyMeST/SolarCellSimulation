# SolarCellSimulation
A Java Spring boot application that generates data to predict the energy win and loss of energy in a solar cell. The Data can be inserted into a postgreSQL database.
@Transactional(readOnly = true)
    public List<SolarEnergyEntity> getPanelsByTimestampRange(Date startTS, Date endTS) {
        return solarEnergyRepository.findByTimestampBetween(startTS, endTS);
    }
    
@GetMapping("/getByTimestamp/{timestamp}")
    public ResponseEntity<?> getByTimestamp(@PathVariable LocalDateTime timestamp) {
        try {
            // Modify this line to use getPanelsByTimestampRange method
            List<SolarEnergyEntity> panels = panelService.getPanelsByTimestampRange(
                    Date.from(timestamp.atZone(ZoneId.systemDefault()).toInstant()),
                    Date.from(timestamp.plusMinutes(1).atZone(ZoneId.systemDefault()).toInstant()) 
            );
            if (panels != null && !panels.isEmpty()) {
                return ResponseEntity.ok(panels);
            } else {
                return ResponseEntity.badRequest().body("No panels found for the given timestamp range");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid input format or an error occurred");
        }
    }
