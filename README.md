# SolarCellSimulation
A Java Spring boot application that generates data to predict the energy win and loss of energy in a solar cell. The Data can be inserted into a postgreSQL database.

@RestController
@RequestMapping("/solar-energy")
public class SolarCellController {
    private final SolarEnergyCalculatorService calculatorService;
    private final SolarPanelService panelService;
    private final IDEndpoint idEndpoint;
    private final TimestampEndpoint timestampEndpoint;
    @Autowired
    public SolarCellController(
            SolarEnergyCalculatorService calculatorService,
            SolarPanelService panelService,
            IDEndpoint idEndpoint,
            TimestampEndpoint timestampEndpoint) {
        this.calculatorService = calculatorService;
        this.panelService = panelService;
        this.idEndpoint = idEndpoint;
        this.timestampEndpoint = timestampEndpoint;
    }
    @Autowired
    public void initializeEndpoints() {
    }
    @GetMapping("/panels/id/{id}")
    public ResponseEntity<?> getPanelById(@PathVariable String id) {
        return idEndpoint.getPanelById(id);
    }
    @GetMapping("/panels/time/{timestamp}")
    public ResponseEntity<?> getByTimestamp(@PathVariable LocalDateTime timestamp) {
        return timestampEndpoint.getByTimestamp(timestamp);
    }
    @GetMapping("/panels/beforeTime/{timestamp}")
    public ResponseEntity<?> getByTimestampRange(@PathVariable LocalDateTime timestamp) {
        return timestampEndpoint.getByTimestampRange(timestamp);
    }
}



Argument [Wed Jan 24 11:36:42 CET 2024] of type [java.util.Date] did not match parameter type [java.time.LocalDateTime (n/a)]
