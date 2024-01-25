import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SolarCellController.class)
class SolarCellControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getPanelById_shouldReturnPanelWithNetEnergyEquals3() throws Exception {
        // Given
        int id = 1; // Replace with the desired ID for testing

        // When
        mockMvc.perform(get("/solar-energy/panels/id/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonResultMatches(Collections.singletonList(/* Expected SolarEnergyEntity */)))
                .andExpect(jsonPath("$[0].netEnergy").value(3.0)); // Add this assertion

        // Then
        // Add additional assertions as needed
    }

    // Add more tests for other controller methods as needed

    private ResultMatcher jsonResultMatches(Object expectedObject) throws Exception {
        return content().json(objectMapper.writeValueAsString(expectedObject));
    }
}
