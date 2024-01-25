# SolarCellSimulation
A Java Spring boot application that generates data to predict the energy win and loss of energy in a solar cell. The Data can be inserted into a postgreSQL database.
import com.saria.solarcell.solarcomponents.IDEndpoint;
import com.saria.solarcell.solarcomponents.SolarEnergyEntity;
import com.saria.solarcell.solarcomponents.SolarPanelService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class IDEndpointTest {
    @Mock
    private SolarPanelService panelService;
    @InjectMocks
    private IDEndpoint idEndpoint;
    @Test
    void testGetPanelById() {
        // Arrange
        String id = "123";
        // Creating a sample SolarEnergyEntity for testing
        SolarEnergyEntity panel = new SolarEnergyEntity(
                100.0,  // solarIrradiance
                0.18,   // panelEfficiency
                20.0,   // panelArea
                0.95,   // inverterEfficiency
                0.02,   // shadingLossFactor
                0.01,   // soilingLossFactor
                0.03,   // mismatchLossFactor
                LocalDateTime.now(), // timestamp
                500.0,  // energyOutput
                480.0   // netEnergy
        );
        panel.setId(123); // Setting a sample ID
        when(panelService.getPanelById(any(Integer.class))).thenReturn(Collections.singletonList(panel));
        // Act
        ResponseEntity<?> responseEntity = idEndpoint.getPanelById(id);
        List<SolarEnergyEntity> panels = (List<SolarEnergyEntity>) responseEntity.getBody();
        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(1, panels.size());
        assertEquals(panel.getId(), panels.get(0).getId());
        assertEquals(panel.getSolarIrradiance(), panels.get(0).getSolarIrradiance());
        // Add assertions for other properties...
    }
}

