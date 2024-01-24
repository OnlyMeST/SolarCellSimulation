# SolarCellSimulation
A Java Spring boot application that generates data to predict the energy win and loss of energy in a solar cell. The Data can be inserted into a postgreSQL database.

@SuppressWarnings("unchecked")
	@GetMapping("/panels/{id}")
    public List<SolarEnergyEntity> getPanelById(@PathVariable Integer id) {
        List<SolarEnergyEntity> panel = panelService.getPanelById(id);
        if (panel != null) {
        	return panel;
        } else {
        	return (List<SolarEnergyEntity>) ResponseEntity.notFound().build();
        }
    }
