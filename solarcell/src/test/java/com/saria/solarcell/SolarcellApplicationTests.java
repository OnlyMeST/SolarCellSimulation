import com.saria.solarcell.solarcomponents.SolarCellController;
import com.saria.solarcell.solarcomponents.SolarEnergyEntity;
import com.saria.solarcell.solarcomponents.SolarPanelService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SolarCellControllerTest {

    @Mock
    private SolarPanelService panelService;

    @InjectMocks
    private SolarCellController controller;

    @Test
    void testGetPanelById() {
        // Arrange
        String testPanelId = "1";
        SolarEnergyEntity testPanelEntity = new SolarEnergyEntity();

        // Mock the behavior of the panelService
        when(panelService.getPanelById(anyInt())).thenReturn(Collections.singletonList(testPanelEntity));

        // Act
        ResponseEntity<?> responseEntity = controller.getPanelById(testPanelId);

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());

        List<SolarEnergyEntity> responsePanels = (List<SolarEnergyEntity>) responseEntity.getBody();
        assertEquals(1, responsePanels.size());
        assertEquals(testPanelEntity, responsePanels.get(0));
    }

    @Test
    void testGetPanelByIdRange() {
        // Arrange
        String testPanelIdRange = "1-3";
        SolarEnergyEntity panel1 = new SolarEnergyEntity();
        SolarEnergyEntity panel2 = new SolarEnergyEntity();
        SolarEnergyEntity panel3 = new SolarEnergyEntity();

        when(panelService.getPanelsByIdRange(anyInt(), anyInt())).thenReturn(Arrays.asList(panel1, panel2, panel3));

        // Act
        ResponseEntity<?> responseEntity = controller.getPanelById(testPanelIdRange);

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());

        List<SolarEnergyEntity> responsePanels = (List<SolarEnergyEntity>) responseEntity.getBody();
        assertEquals(3, responsePanels.size());
        assertEquals(panel1, responsePanels.get(0));
        assertEquals(panel2, responsePanels.get(1));
        assertEquals(panel3, responsePanels.get(2));
    }
}
