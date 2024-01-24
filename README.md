# SolarCellSimulation
A Java Spring boot application that generates data to predict the energy win and loss of energy in a solar cell. The Data can be inserted into a postgreSQL database.

@GetMapping("/getByTimestamp")
    public ResponseEntity<YourEntity> getByTimestamp(@RequestParam("timestamp") LocalDateTime timestamp) {
        try {
            YourEntity result = yourService.getByTimestamp(timestamp);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
