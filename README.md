# SolarCellSimulation
A Java Spring boot application that generates data to predict the energy win and loss of energy in a solar cell. The Data can be inserted into a postgreSQL database.
import com.saria.solarcell.solarcomponents.SolarEnergyEntity;
import com.saria.solarcell.solarcomponents.SolarPanelService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

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
        SolarEnergyEntity panel = new SolarEnergyEntity();
        panel.setId(123);
        panel.setSolarIrradiance(100);
        // Set other properties...
        when(panelService.getPanelById(any(Integer.class))).thenReturn(Collections.singletonList(panel));
        // Act
        ResponseEntity<?> responseEntity = idEndpoint.getPanelById(id);
        List<SolarEnergyEntity> panels = (List<SolarEnergyEntity>) responseEntity.getBody();
        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(1, panels.size());
        assertEquals(panel.getId(), panels.get(0).getId());
        // Assert other properties...
    }
}
