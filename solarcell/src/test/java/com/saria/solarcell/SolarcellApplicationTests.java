import com.saria.solarcell.solarcomponents.SolarCellController;
import com.saria.solarcell.solarcomponents.SolarEnergyEntity;
import com.saria.solarcell.solarcomponents.SolarPanelService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

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
        int testPanelId = 1;
        SolarEnergyEntity testPanelEntity = new SolarEnergyEntity();
        testPanelEntity.setId(testPanelId);

        // Mock the behavior of the panelService
        when(panelService.getPanelById(testPanelId)).thenReturn(Collections.singletonList(testPanelEntity));

        // Act
        ResponseEntity<?> responseEntity = controller.getPanelById(String.valueOf(testPanelId));

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());

        List<SolarEnergyEntity> responsePanels = (List<SolarEnergyEntity>) responseEntity.getBody();
        assertEquals(1, responsePanels.size());
        assertEquals(testPanelId, responsePanels.get(0).getId());
    }
}
