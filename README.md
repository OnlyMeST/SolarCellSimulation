# SolarCellSimulation
A Java Spring boot application that generates data to predict the energy win and loss of energy in a solar cell. The Data can be inserted into a postgreSQL database.
http://localhost:8080/api/panels/getByTimestampRange?startTS=2022-01-01T00:00:00&endTS=2022-01-02T00:00:00

@GetMapping("panels/timeRange")
public ResponseEntity<?> getByTimestampRange(
        @RequestParam("startTS") LocalDateTime startTS,
        @RequestParam("endTS") LocalDateTime endTS) {
    try {
        List<SolarEnergyEntity> panels = panelService.getPanelsByTimestampRange(
                Date.from(startTS.atZone(ZoneId.systemDefault()).toInstant()),
                Date.from(endTS.atZone(ZoneId.systemDefault()).toInstant())
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
