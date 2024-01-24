# SolarCellSimulation
A Java Spring boot application that generates data to predict the energy win and loss of energy in a solar cell. The Data can be inserted into a postgreSQL database.

@SuppressWarnings("unchecked")
@GetMapping("/panels/by-timestamp/{timestamp}")
public ResponseEntity<?> getPanelsByTimestamp(@PathVariable String timestamp) {
    if (timestamp.contains("-")) {
        String[] range = timestamp.split("-");
        if (range.length == 2) {
            try {
                LocalDateTime startTimestamp = LocalDateTime.parse(range[0] + "T00:00");
                LocalDateTime endTimestamp = LocalDateTime.parse(range[1] + "T23:59");
                List<SolarEnergyEntity> panelsInRange = panelService.getPanelsByTimestampRange(startTimestamp, endTimestamp);
                return ResponseEntity.ok(panelsInRange);
            } catch (DateTimeParseException e) {
                // Handle parsing error
                return ResponseEntity.badRequest().body("Invalid timestamp range format");
            }
        }
    } else {
        try {
            // Assuming partial timestamp (year, month, day)
            LocalDateTime partialTimestamp = LocalDateTime.parse(timestamp + "T00:00");
            List<SolarEnergyEntity> panelsByPartialTimestamp = panelService.getPanelsByPartialTimestamp(partialTimestamp);
            return ResponseEntity.ok(panelsByPartialTimestamp);
        } catch (DateTimeParseException e) {
            // Handle parsing error
            return ResponseEntity.badRequest().body("Invalid timestamp format");
        }
    }

    // Handle invalid input format
    return ResponseEntity.badRequest().body("Invalid input format");
}
