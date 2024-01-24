# SolarCellSimulation
A Java Spring boot application that generates data to predict the energy win and loss of energy in a solar cell. The Data can be inserted into a postgreSQL database.

http://localhost:8080/solar-energy/panels/timeRange?startTS=2024-01-24T11:36:42&endTS=2024-01-24T11:36:43

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

Argument [Wed Jan 24 11:36:42 CET 2024] of type [java.util.Date] did not match parameter type [java.time.LocalDateTime (n/a)]
