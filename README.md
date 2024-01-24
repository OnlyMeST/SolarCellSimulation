# SolarCellSimulation
A Java Spring boot application that generates data to predict the energy win and loss of energy in a solar cell. The Data can be inserted into a postgreSQL database.

List<SolarEnergyEntity> findByIdBetween(Integer startId, Integer endId);

public List<SolarEnergyEntity> getPanelsByIdRange(Integer startId, Integer endId) {
        return solarEnergyRepository.findByIdBetween(startId, endId);
    }

@SuppressWarnings("unchecked")
@GetMapping("/panels/{id}")
public ResponseEntity<?> getPanelById(@PathVariable String id) {
    if (id.contains("-")) {
        String[] range = id.split("-");
        if (range.length == 2) {
            try {
                int startId = Integer.parseInt(range[0]);
                int endId = Integer.parseInt(range[1]);
                List<SolarEnergyEntity> panelsInRange = panelService.getPanelsByIdRange(startId, endId);
                return ResponseEntity.ok(panelsInRange);
            } catch (NumberFormatException e) {
                // Handle parsing error
                return ResponseEntity.badRequest().body("Invalid range format");
            }
        }
    } else {
        try {
            int singleId = Integer.parseInt(id);
            SolarEnergyEntity panel = panelService.getPanelById(singleId);
            if (panel != null) {
                return ResponseEntity.ok(Collections.singletonList(panel));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid ID format");
        }
    }
    return ResponseEntity.badRequest().body("Invalid input format");
}
